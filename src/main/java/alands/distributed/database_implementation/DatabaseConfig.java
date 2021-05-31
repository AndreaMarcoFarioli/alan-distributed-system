package alands.distributed.database_implementation;

import alands.distributed.EnvLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static String host = "localhost";
    private static String dbName = "alandb";
    private static String connection = "jdbc:mysql://"+host+"/"+dbName;
    private static String username = "root";
    private static String password = "";
    private static Integer port = 3306;

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

    public static void setFromEnv(EnvLoader envLoader){
        host = envLoader.getString("dbhost");
        dbName = envLoader.getString("dbname");
        username = envLoader.getString("dbuser");
        password = envLoader.getString("dbpass");
        if ((port = envLoader.getInt("dbport")) == null)
            port = 3306;
        connection = "jdbc:mysql://"+host+"/"+dbName;
    }
}
