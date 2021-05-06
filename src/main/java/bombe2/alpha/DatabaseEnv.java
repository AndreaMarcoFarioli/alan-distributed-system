package bombe2.alpha;

import bombe2.distributed.MainManager;
import bombe2.exceptions.DataSourceException;
import java.sql.*;

public class DatabaseEnv implements DataTable{

    private final String
            url = "jdbc:mysql://localhost/bombe",
            query = "SELECT * FROM env_table WHERE env_table.name = ?",
            insertUpdate = "INSERT INTO env_table VALUES (?, ?) ON DUPLICATE KEY UPDATE env_table.data = ?";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, "root", "");
    }

    private void close(Connection connection) throws SQLException {
        connection.close();
    }

    private ResultSet executeKeySearch(String key, Connection connection) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, key);
        return preparedStatement.executeQuery();
    }

    public <T>T getGeneric(Class<T> type, String key){
        T value;
        try {
            value = MainManager.getInstance().getClusterTiming().delegateTransaction(() -> {
                T tmp;
                Connection connection = getConnection();
                ResultSet resultSet = executeKeySearch(key, connection);
                resultSet.next();
                tmp = resultSet.getObject("data", type);
                close(connection);
                return tmp;
            }, DataTransactionType.READ);
        } catch (Exception e) {
            throw new DataSourceException(e);
        }
        return value;
    }

    public void setGeneric( String key, Object data){
        try {
            MainManager.getInstance().getClusterTiming().delegateTransaction(() -> {
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(insertUpdate);
                preparedStatement.setString(1, key);
                preparedStatement.setObject(2, data);
                preparedStatement.setObject(3, data);
                preparedStatement.execute();
                connection.close();
                return null;
            }, DataTransactionType.WRITE);
        } catch (Exception e) {
            throw new DataSourceException(e);
        }
    }

    @Override
    public int getInt(String key) {
        return getGeneric(Integer.TYPE, key);
    }

    @Override
    public double getDouble(String key) {
        return getGeneric(Double.TYPE, key);
    }

    @Override
    public float getFloat(String key) {
        return getGeneric(Float.TYPE, key);
    }

    @Override
    public String getString(String key) {
        return getGeneric(String.class, key);
    }

    @Override
    public long getLong(String key) {
        return getGeneric(Long.TYPE, key);
    }

    @Override
    public Object getObject(String key) {
        return getGeneric(Object.class, key);
    }

    @Override
    public void setInt(String key, int value) {
        setGeneric(key,value);
    }

    @Override
    public void setDouble(String key, double value) {
        setGeneric(key,value);
    }

    @Override
    public void setFloat(String key, float value) {
        setGeneric(key,value);
    }

    @Override
    public void setString(String key, String value) {
        setGeneric(key,value);
    }

    @Override
    public void setLong(String key, long value) {
        setGeneric(key,value);
    }

    @Override
    public void setObject(String key, Object value) {
        setGeneric(key,value);
    }
}
