package dao.Impl;

import databaseConnection.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AbstractDAO {
    public final static Logger logger = Logger.getLogger(ClientDAOImpl.class);

    protected PreparedStatement prepareStatement(String query) throws SQLException {
        return ConnectionPool.getConnection().prepareStatement(query);
    }

    protected PreparedStatement prepareStatement(String query, int flag) throws SQLException {
        return ConnectionPool.getConnection().prepareStatement(query, flag);
    }


    protected static  void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            logger.error("Error during closing ResultSet", ex);
            ex.printStackTrace();
        }
    }
}
