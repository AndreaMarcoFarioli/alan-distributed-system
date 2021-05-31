package alands.distributed.database_implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    public static final String host = "localhost";
    public static final String dbName = "bombe";
    public static final String connection = "jdbc:mysql://"+host+"/"+dbName;
    public static final String username = "root";
    public static final String password = "";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connection, username, password);
    }
}
