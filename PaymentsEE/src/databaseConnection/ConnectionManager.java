package databaseConnection;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ConnectionManager {
    public final static Logger logger = Logger.getLogger(ConnectionManager.class);
    private static ResourceBundle config = null;
    private static volatile boolean isDriverLoaded = false;
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;
    private static Connection connection;

    static {
        ResourceBundle config = ResourceBundle.getBundle("db");
        if (config == null) {
            URL = "UNDEFINED";
            USER = "UNDEFINED";
            PASSWORD = "UNDEFINED";
            System.out.println("Resource bundle for database was not initialize");
            logger.debug("Resource bundle for database was not initialize");
        } else {
            URL = config.getString("db_url");
            USER = config.getString("db_user");
            PASSWORD = config.getString("db_password");
            try {
                Class.forName(config.getString("db_driver"));
                isDriverLoaded = true;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() throws ConnectionException {
        if (!isDriverLoaded) {
            throw new ConnectionException("databaseConnection driver was not loaded");
        }
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                logger.debug("Successful connection");
            }
            return connection;
        } catch (SQLException ex) {
            throw new ConnectionException("Trouble with connection" + ex.getMessage());
        }
    }

}
