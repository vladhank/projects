package databaseConnection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;


public  class ConnectionPool {

    public final static Logger logger = Logger.getLogger(ConnectionManager.class);
    private static ResourceBundle rb = null;
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;
    private static final String DRIVERCLASS;
    private static Connection connection;
    private static HikariDataSource dataSource;
    private static HikariConfig config = new HikariConfig();

    static {
        ResourceBundle rb = ResourceBundle.getBundle("db");
        if (rb==null){
            URL="UNDEFINED";
            USER="UNDEFINED";
            PASSWORD="UNDEFINED";
            DRIVERCLASS="UNDEFINED";
            System.out.println("Database configuration file was not initialize");
            logger.debug("Database configuration file was not initialize");
        }
        else {
            //dataSource = new HikariDataSource();
            URL=rb.getString("db_url");
            USER=rb.getString("db_user");
            PASSWORD=rb.getString("db_password");
            DRIVERCLASS=rb.getString("db_driver");
            logger.debug("Database configuration was loaded successfully");
            config.setJdbcUrl(URL);
            config.setUsername(USER);
            config.setPassword(PASSWORD);
            config.setDriverClassName(DRIVERCLASS);
            config.setMinimumIdle(1);
            config.setMaximumPoolSize(200);
            config.setAutoCommit(true);
            dataSource= new HikariDataSource(config);
        }
    }

    public static Connection getConnection(){
        try{
            if(connection==null)
            {
                connection=dataSource.getConnection();
                logger.debug("connection pool was created successfully");
            }
            return connection;
        } catch (SQLException ex){
            logger.error("Trouble with connection",ex);
            throw new ConnectionException("Trouble with connection" + ex.getMessage());
        }

    }

}
