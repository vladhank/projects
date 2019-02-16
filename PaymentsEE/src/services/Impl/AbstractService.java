package services.Impl;

import databaseConnection.ConnectionException;
import databaseConnection.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractService {
    public void startTransaction() throws SQLException{
        ConnectionPool.getConnection().setAutoCommit(false);
    }
    public void commit() throws SQLException{
        ConnectionPool.getConnection().commit();
    }
    public Connection getConnection() {
        return ConnectionPool.getConnection();
    }

    public void rollback() {
        try {
            getConnection().rollback();
        } catch (SQLException e) {
            throw new ConnectionException("rollback error");
        }
    }
}
