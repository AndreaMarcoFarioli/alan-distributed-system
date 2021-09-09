package alands.distributed.database_implementation;

import alands.core.SessionReference;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Deprecated
public final class SessionDatabaseStorage extends DatabaseStorage {
    private final SessionReference sessionReference;

    private static final String
            getSidId = "(SELECT id FROM session WHERE sid=?)",
            insertQuery = "INSERT INTO session_var (name, `value`, sid_id) VALUES(?, ?, "+getSidId+") ON DUPLICATE KEY UPDATE `value`=?",
            getQuery= "SELECT `value` FROM session_var WHERE name=? AND sid_id="+getSidId,
            deleteRecord = "DELETE FROM session_var WHERE name=? AND sid_id=" +getSidId;

    public SessionDatabaseStorage(SessionReference sessionReference){
        this.sessionReference = sessionReference;
    }


    @Override
    public <T> void set(String name, T value) {
        try (Connection connection = DatabaseConfig.getConnection()){
            PreparedStatement ps = connection.prepareStatement(getInsertQuery());
            ps.setObject(1,name);
            ps.setObject(2,value);
            ps.setString(3,getSessionId());
            ps.setObject(4, value);
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
            ps.setString(2, getSessionId());
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
            ps.setString(2, getSessionId());
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private String getSessionId() {
        return sessionReference.getReference().getSessionId();
    }

    @Override
    public String getGetQuery() {
        return getQuery;
    }

    @Override
    public String getInsertQuery() {
        return insertQuery;
    }

    public static String getDeleteRecord() {
        return deleteRecord;
    }
}
