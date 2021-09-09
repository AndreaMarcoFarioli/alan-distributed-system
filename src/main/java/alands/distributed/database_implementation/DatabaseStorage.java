package alands.distributed.database_implementation;

import alands.core.data.Storage;

import java.sql.*;

@Deprecated
public class DatabaseStorage implements Storage {
    private static final String
            insertQuery = "INSERT INTO vars VALUES (?, ?) ON DUPLICATE KEY UPDATE val=?",
            getQuery = "SELECT val FROM vars WHERE name=?",
            deleteRecord = "DELETE FROM vars WHERE name=?";

    //private final String varTable;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public DatabaseStorage(){
        //this.varTable = varTable;
    }
    @Override
    public <T> void set(String name, T value) {
        try (Connection connection = DatabaseConfig.getConnection()){
            PreparedStatement ps = connection.prepareStatement(getInsertQuery());
            ps.setObject(1,name);
            ps.setObject(2,value);
            ps.setObject(3,value);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public <T>T get(String name, Class<T> type) {
        Object result = null;
        try (Connection connection = DatabaseConfig.getConnection()){
            PreparedStatement ps = connection.prepareStatement(getGetQuery());
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getObject(1, type);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return type.cast(result);
    }

    @Override
    public void clear(String name) {
        try (Connection connection = DatabaseConfig.getConnection()){
            PreparedStatement ps = connection.prepareStatement(getDeleteRecord());
            ps.setString(1, name);
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
}
