package bombe2.distributed.database_implementation;

import bombe2.core.data.EventObject;
import bombe2.core.data.ReturnableObject;
import bombe2.core.definitions.Propagator;
import bombe2.distributed.InterComChannel;
import bombe2.distributed.RemoteNode;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;

public class DatabaseComUnit extends UnicastRemoteObject implements InterComChannel, AutoCloseable {

    private final Propagator propagator;

    private int id;

    private final int port;

    private static final String
        createNode = "INSERT INTO node (host, port) VALUES (?, ?)",
        selectNodeForUpdate = "SELECT * FROM node WHERE available=true AND score = (SELECT MIN(score) FROM node WHERE available=true) ORDER BY rand() ASC LIMIT 1 FOR UPDATE",
        updateScoreNode = "UPDATE node SET score = score %s 1 WHERE id = ?",
        deleteNode = "DELETE FROM node WHERE id = ?",
        setAvailability = "UPDATE node SET available = ? WHERE id = ?",
        createProcess = "INSERT INTO inter_process (local_node, remote_node) VALUES (?,?)",
        clearProcess = "DELETE FROM inter_process WHERE id=?";

    private boolean isClosed = false,sdHookCall = false;

    private final Thread shutdownThread = new Thread(() -> {
        try {
            sdHookCall = true;
            close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sdHookCall = false;
        }
    });

    public DatabaseComUnit(Propagator propagator, int port) throws RemoteException {
        super();
        this.propagator = propagator;
        this.port = port;
        createNode();
        Runtime.getRuntime().addShutdownHook(shutdownThread);
    }

    public void setNodeAvailability (boolean availability){
        try(Connection connection = DatabaseConfig.getConnection()){
            PreparedStatement ps = connection.prepareStatement(getSetAvailability());
            ps.setBoolean(1, availability);
            ps.setInt(2, this.id);
            int row = ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void createNode(){
        try(Connection connection = DatabaseConfig.getConnection()){
            connection.setAutoCommit(false);
            try(PreparedStatement ps =
                        connection.prepareStatement(createNode, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, InetAddress.getLocalHost().getHostAddress());
                ps.setInt(2, this.port);
                int row = ps.executeUpdate();
                if (row == 1) {
                    try (ResultSet resultSet = ps.getGeneratedKeys()) {
                        if (resultSet.next())
                            this.id = resultSet.getInt(1);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        connection.rollback();
                    }
                }
            }catch (SQLException e){
                e.printStackTrace();
                connection.rollback();
                throw new SQLException("Something went wrong during the node creation");
            }
            connection.commit();
        }catch (SQLException | UnknownHostException e){
            e.printStackTrace();
        }
    }

    @Override
    public ReturnableObject<?> call(EventObject eventObject) throws ReflectiveOperationException, RemoteException {
        return propagator.propagate(eventObject);
    }


    @Override
    public ReturnableObject<?> sendOver(EventObject eventObject) throws ReflectiveOperationException {
        ReturnableObject<?> returnableObject = null;
        int pid = -1, id = -1, port;
        String host;
        try(Connection connection = DatabaseConfig.getConnection()){
            connection.setAutoCommit(false);
            try(ResultSet resultSet = getNodeResultSet(connection)) {
                if (resultSet.next()) {
                    id = resultSet.getInt("id");
                    port = resultSet.getInt("port");
                    host = resultSet.getString("host");
                    calcScore(connection, id, true);
                    pid = createProcess(connection, this.id, id);
                    connection.commit();
                    connection.setAutoCommit(true);
                    RemoteNode remoteNode = getRemoteNode(host, port);
                    returnableObject = remoteNode.call(eventObject);
                } else
                    throw new SQLException("Something went wrong, node not found");
            } catch (SQLException | NotBoundException e){
                e.printStackTrace();
                connection.rollback();
                throw new SQLException("rollback");
            } catch (RemoteException e){
                e.printStackTrace();
                connection.rollback();
            } finally {
                if (id != -1)
                    calcScore(connection, id, false);
                if (pid != -1)
                    clearProcess(connection, pid);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return returnableObject;
    }

    private ResultSet getNodeResultSet(Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(getSelectNodeForUpdate());
        return ps.executeQuery();
    }
    
    private void calcScore(Connection connection, int id, boolean sign) throws SQLException {
        String s = getUpdateScoreNode();
        s = String.format(s, sign? "+":"-");
        PreparedStatement ps = connection.prepareStatement(s);
        ps.setInt(1, id);
        if(ps.executeUpdate() != 1)
            throw new SQLException("Something went wrong during the update of score");
    }

    private int createProcess(Connection connection, int localId, int remoteId) throws SQLException{
        int pid = -1;
        PreparedStatement ps = connection.prepareStatement(getCreateProcess(), Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, localId);
        ps.setInt(2, remoteId);
        if (ps.executeUpdate() == 1){
            try(ResultSet resultSet = ps.getGeneratedKeys()){
                if (resultSet.next())
                    pid = resultSet.getInt(1);
            }catch (SQLException e){
                e.printStackTrace();
                throw new SQLException(e);
            }
        }else
            throw new SQLException("Something went wrong during the creation of process");
        return pid;
    }

    private void clearProcess(Connection connection, int pid) throws SQLException{
        PreparedStatement ps = connection.prepareStatement(getClearProcess());
        ps.setInt(1 ,pid);
        if (ps.executeUpdate() != 1)
            throw new SQLException("Something went wrong during the process clearing");
    }

    private RemoteNode getRemoteNode(String host, int port) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(host, port);
        return (RemoteNode) registry.lookup("com");
    }

    @Override
    public void close() throws Exception {
        if (isClosed)
            throw new IllegalStateException("Already closed");
        if (!sdHookCall)
            Runtime.getRuntime().removeShutdownHook(shutdownThread);
        try (Connection connection = DatabaseConfig.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(getDeleteNode());
            statement.setInt(1, this.id);
            int row = statement.executeUpdate();
            if (row == 1)
                isClosed = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getCreateNode() {
        return createNode;
    }

    public static String getDeleteNode() {
        return deleteNode;
    }

    public static String getSelectNodeForUpdate() {
        return selectNodeForUpdate;
    }

    public static String getUpdateScoreNode() {
        return updateScoreNode;
    }

    public static String getSetAvailability() {
        return setAvailability;
    }

    public static String getClearProcess() {
        return clearProcess;
    }

    public static String getCreateProcess() {
        return createProcess;
    }
}
