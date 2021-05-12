package bombe2.distributed;

import bombe2.core.SessionManager;
import bombe2.core.SessionReference;
import bombe2.exceptions.NoSuchSessionException;
import bombe2.exceptions.SessionException;

import java.sql.*;

public class DatabaseSessionManager extends SessionManager {

    private static final String
            createSessionQuery = "INSERT INTO session_storage (sid) VALUES (?)",
            connection = "jdbc:mysql://localhost/bombe",
            getSessionBySid = "SELECT sid FROM session_storage WHERE sid=?",
            destroySession = "DELETE FROM session_storage WHERE sid=?",
            countQuery = "SELECT COUNT(*) FROM session_storage";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection provideConnection() throws SQLException{
        return DriverManager.getConnection(getConnection(), "root", "");
    }

    @Override
    public SessionReference createSession() {
        SessionReference sessionReference;
        try(Connection connection = provideConnection()){
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
        try(Connection connection = provideConnection()){
            PreparedStatement ps = connection.prepareStatement(getGetSessionBySid());
            ps.setString(1, sessionId);
            ResultSet resultSet = ps.executeQuery();
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
        try(Connection connection = provideConnection()){
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
        try(Connection connection = provideConnection()){
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
        try(Connection connection = provideConnection()){
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

    public static String getConnection() {
        return connection;
    }
}
