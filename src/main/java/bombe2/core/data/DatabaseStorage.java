package bombe2.core.data;

import java.sql.*;

public class DatabaseStorage implements Storage {
    private static final String
            connection = "jdbc:mysql://localhost/bombe",
            insertQuery = "INSERT INTO vars VALUES (?, ?) ON DUPLICATE KEY UPDATE val=?",
            getQuery = "SELECT val FROM vars WHERE name=?",
            deleteRecord = "DELETE FROM vars WHERE name=?";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> void setParameter(String name, T value) {
        try (Connection connection = DriverManager.getConnection(getConnection(), "root", "")){
            PreparedStatement ps = connection.prepareStatement(getInsertQuery());
            set(ps, name, value, value);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    protected void set(PreparedStatement ps, Object... params) throws SQLException{
        for(int i = 0; i < params.length; i++){
            ps.setObject(i+1,params[i]);
        }
    }

    @Override
    public Object getParameter(String name, Class<?> type) {
        Object result = null;
        try (Connection connection = DriverManager.getConnection(getConnection(), "root", "")){
            PreparedStatement ps = connection.prepareStatement(getGetQuery());
            set(ps, name);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getObject(1, type);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void clear(String name) {
        try (Connection connection = DriverManager.getConnection(getConnection(), "root", "")){
            PreparedStatement ps = connection.prepareStatement(getDeleteRecord());
            set(ps, name);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public String getGetQuery() {
        return getQuery;
    }

    public String getInsertQuery() {
        return insertQuery;
    }

    public static String getDeleteRecord() {
        return deleteRecord;
    }

    public static String getConnection() {
        return connection;
    }
}
