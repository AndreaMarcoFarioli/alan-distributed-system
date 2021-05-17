package bombe2.distributed;

import bombe2.core.data.EventObject;
import bombe2.core.data.ReturnableObject;
import bombe2.core.definitions.Propagator;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;

public class DBComUnit extends UnicastRemoteObject implements InterComUnit, AutoCloseable {
    private final Propagator propagator;
    private static final String
            connection = "jdbc:mysql://localhost/bombe",
            createNode = "INSERT INTO node (host, port) VALUES (?, ?)";
    private final int port = Main.port;
    private int id;
    private final Thread shutdownThread = new Thread(() -> {
        try {
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    });
    public DBComUnit(Propagator propagator) throws RemoteException {
        super();
        this.propagator = propagator;
        createNode();
        Runtime.getRuntime().addShutdownHook(shutdownThread);
    }

    private void createNode(){
        try(Connection connection = getConnection()){
            connection.setAutoCommit(false);
            try(PreparedStatement ps = connection.prepareStatement(createNode, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, InetAddress.getLocalHost().getHostAddress());
                ps.setInt(2, port);
                int row = ps.executeUpdate();
                if (row == 1) {
                    try (ResultSet resultSet = ps.getGeneratedKeys()) {
                        if (resultSet.next())
                            id = resultSet.getInt(1);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        connection.rollback();
                    }
                }
            }catch (SQLException e){
                connection.rollback();
                throw new SQLException(e);
            }
            connection.commit();
        }catch (SQLException | UnknownHostException e){
            e.printStackTrace();
        }
    }

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connection, "root", "");
    }

    @Override
    public ReturnableObject<?> call(EventObject eventObject) throws ReflectiveOperationException, RemoteException {
        return propagator.propagate(eventObject);
    }


    @Override
    public ReturnableObject<?> sendOver(EventObject eventObject) throws RemoteException, ReflectiveOperationException {
        return getNode().call(eventObject);
    }

    private RemoteNode getNode() {
        String selectForUpdate = "SELECT * FROM node ORDER BY score ASC LIMIT 1 FOR UPDATE",
                update = "UPDATE node SET score = score + 1 WHERE id = ?";
        RemoteNode remoteNode = null;
        try(Connection connection = getConnection()){
            connection.setAutoCommit(false);
            String host = "";
            int port = 0;
            try(Statement statement = connection.createStatement();
                PreparedStatement ps = connection.prepareStatement(update)){
                ResultSet resultSet = statement.executeQuery(selectForUpdate);
                if (resultSet.next()){
                    int id = resultSet.getInt("id");
                    host = resultSet.getString("host");
                    port = resultSet.getInt("port");
                    ps.setInt(1, id);
                    int n = ps.executeUpdate();
                    if (n != 1){
                        throw new SQLException("Not updated");
                    }
                }
            }catch (SQLException e){
                connection.rollback();
                throw new SQLException(e);
            }
            connection.commit();
            Registry registry = LocateRegistry.getRegistry(host, port);
            remoteNode = (RemoteNode) registry.lookup("com");
        }catch (SQLException | NotBoundException | RemoteException e){
            e.printStackTrace();
        }

        return remoteNode;
    }

    @Override
    public void close() throws Exception {
        //Runtime.getRuntime().removeShutdownHook(shutdownThread);
        try(Connection connection = getConnection()){
            Statement statement = connection.createStatement();
            int row = statement.executeUpdate("DELETE FROM node WHERE id="+id);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
