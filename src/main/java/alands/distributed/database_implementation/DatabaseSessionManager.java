package alands.distributed.database_implementation;

import alands.core.SessionManager;
import alands.core.SessionReference;
import alands.exceptions.FatalException;
import alands.exceptions.NoSuchSessionException;
import alands.exceptions.SessionException;

import java.sql.*;

public final class DatabaseSessionManager extends SessionManager {
    private static final String
            createSessionQuery = "INSERT INTO session_storage (sid) VALUES (?)",
            getSessionBySid = "SELECT sid FROM session_storage WHERE sid=?",
            destroySession = "DELETE FROM session_storage WHERE sid=?",
            countQuery = "SELECT COUNT(*) FROM session_storage",
            destroyAll = "DELETE FROM session_storage WHERE 1";

    public DatabaseSessionManager(){
        try {
            Runtime.getRuntime().addShutdownHook(new Thread(this::destroyAll));
        }catch (FatalException e){
            e.printStackTrace();
        }
    }

    @Override
    public SessionReference createSession() {
        SessionReference sessionReference;
        try(Connection connection = DatabaseConfig.getConnection()){
            PreparedStatement ps = connection.prepareStatement(getCreateSessionQuery());
            String sessionId = generateSessionId();
            ps.setString(1, sessionId);
            if (ps.executeUpdate() == 1)
                sessionReference = new DatabaseSessionReference(this, sessionId);
            else
                throw new SessionException("Session not created");
        }catch (SQLException e){
            e.printStackTrace();
            throw new SessionException("Something went wrong during the creation");
        }
        return sessionReference;
    }

    @Override
    public SessionReference getSession(String sessionId) {
        SessionReference sessionReference;
        try(Connection connection = DatabaseConfig.getConnection()){
            PreparedStatement ps = connection.prepareStatement(getGetSessionBySid());
            ps.setString(1, sessionId);
            ResultSet resultSet = ps.executeQuery();
            System.out.println(sessionId);
            if (!resultSet.next())
                throw new NoSuchSessionException("Session " + sessionId + " not found");
            sessionReference = new DatabaseSessionReference(this, sessionId);
        }catch (SQLException e){
            e.printStackTrace();
            throw new SessionException("Something went wrong for " + sessionId);
        }
        return sessionReference;
    }

    @Override
    public boolean available(String sessionId) {
        boolean available = true;
        try(Connection connection = DatabaseConfig.getConnection()){
            PreparedStatement ps = connection.prepareStatement(getGetSessionBySid());
            ps.setString(1, sessionId);
            ResultSet resultSet = ps.executeQuery();
            if (!resultSet.next())
                available = false;
        }catch (SQLException e){
            e.printStackTrace();
            throw new SessionException("Something went wrong for " + sessionId);
        }
        return available;
    }

    @Override
    public void destroySession(String sessionId) {
        try(Connection connection = DatabaseConfig.getConnection()){
            PreparedStatement ps = connection.prepareStatement(getDestroySession());
            ps.setString(1, sessionId);
            int deleted = ps.executeUpdate();
            if (deleted > 1)
                throw new SessionException("You have deleted more than one session with the same sid " + sessionId);
        }catch (SQLException e){
            e.printStackTrace();
            throw new SessionException("Can't destroy session " + sessionId);
        }
    }

    @Override
    public int sessionCount() {
        int count = 0;
        try(Connection connection = DatabaseConfig.getConnection()){
            PreparedStatement ps = connection.prepareStatement(getCountQuery());
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next())
                count = resultSet.getInt(1);
        }catch (SQLException e){
            e.printStackTrace();
            throw new SessionException("Can not perform a session count");
        }
        return count;
    }

    @Override
    public void destroyAll() {
        try(Connection connection = DatabaseConfig.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(getDestroyAll());
            int res = preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new FatalException();
        }
    }

    public static String getCreateSessionQuery() {
        return createSessionQuery;
    }

    public static String getDestroySession() {
        return destroySession;
    }

    public static String getCountQuery() {
        return countQuery;
    }

    public static String getGetSessionBySid() {
        return getSessionBySid;
    }

    public static String getDestroyAll() {
        return destroyAll;
    }
}
