package alands.distributed.database_implementation;

import alands.distributed.props.LocalProperties;
//import alands.test.C3P0DataSource;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.SQLException;

@Deprecated
public class DatabaseConfig {
    private static String host = "localhost";
    private static String dbName = "alandb";
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
        // return C3P0DataSource.getInstance().getConnection();
        return null;
    }

    public static void setFromEnv(LocalProperties envLoader){
        host = envLoader.getString("dbhost");
        dbName = envLoader.getString("dbname");
        username = envLoader.getString("dbuser");
        password = envLoader.getString("dbpass");
        if ((port = envLoader.getInt("dbport")) == null)
            port = 3306;
    }

    public static String getConnectionString() {
        return "jdbc:mysql://"+getHost()+"/"+getDbName();
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static Integer getPort() {
        return port;
    }

    public static String getDbName() {
        return dbName;
    }

    public static String getHost() {
        return host;
    }
}
