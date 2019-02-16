package dao.Impl;

import dao.TransactionDAO;
import databaseConnection.ConnectionPool;
import entities.Transaction;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAOImpl extends AbstractDAO implements TransactionDAO {
    public final static Logger logger = Logger.getLogger(TransactionDAOImpl.class);
    private static volatile TransactionDAO INSTANCE = null;
    private static final String saveTransactionQuery = "INSERT  INTO PAYMENT_TRANSACTION (CARD_NUMBER,ACCOUNT_ID,TRANSACTION_TYPE,MONEY_AMOUNT) VALUES (?,?,?,?)";
    private static final String updateTransactionQuery = "UPDATE PAYMENT_TRANSACTION SET CARD_NUMBER=?,ACCOUNT_ID=?,TRANSACTION_TYPE=?,MONEY_AMOUNT=? WHERE TRANSACTION_ID=?";
    private static final String getTransactionQuery = "SELECT * FROM PAYMENT_TRANSACTION WHERE TRANSACTION_ID=?";
    private static final String getAllTransactionQuery = "SELECT * FROM PAYMENT_TRANSACTION";
    private static final String getAllTransactionByIDQuery = "SELECT * FROM PAYMENT_TRANSACTION WHERE ACCOUNT_ID=(SELECT ACCOUNT_ID FROM BANK WHERE CLIENT_ID=?)";
    private static final String getDayLimitWithdrawTransaction = "SELECT * FROM PAYMENT_TRANSACTION WHERE MONEY_AMOUNT>300 AND TRANSACTION_TYPE='WITHDRAW'";
    private static final String deleteTransactionQuery = "DELETE FROM TRANSACTION WHERE TRANSACTION_ID=?";

    private PreparedStatement psSave;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psGetAll;
    private PreparedStatement psGetAllByID;
    private PreparedStatement psGetDayLimitWithdrawTransaction;
    private PreparedStatement psDelete;

    {
        try {
            psSave = ConnectionPool.getConnection().prepareStatement(saveTransactionQuery, Statement.RETURN_GENERATED_KEYS);
            psUpdate = ConnectionPool.getConnection().prepareStatement(updateTransactionQuery);
            psGet = ConnectionPool.getConnection().prepareStatement(getTransactionQuery);
            psGetAll = ConnectionPool.getConnection().prepareStatement(getAllTransactionQuery);
            psGetAllByID = ConnectionPool.getConnection().prepareStatement(getAllTransactionByIDQuery);
            psGetDayLimitWithdrawTransaction = ConnectionPool.getConnection().prepareStatement(getDayLimitWithdrawTransaction);
            psDelete = ConnectionPool.getConnection().prepareStatement(deleteTransactionQuery);
        } catch (SQLException ex) {
            logger.error("Exception while creating connection for prepared statement", ex);
            ex.printStackTrace();
        }
    }

    public TransactionDAOImpl() {
    }

    public static TransactionDAO getInstance() {
        TransactionDAO transactionDAO = INSTANCE;
        if (transactionDAO == null) {
            synchronized (ClientDAOImpl.class) {
                transactionDAO = INSTANCE;
                if (transactionDAO == null) {
                    INSTANCE = transactionDAO = new TransactionDAOImpl();
                }
            }
        }
        return transactionDAO;
    }

    @Override
    public Transaction save(Transaction transaction) throws SQLException {
        psSave.setLong(1, transaction.getCardNumber());
        psSave.setLong(2, transaction.getAccountID());
        psSave.setString(3, transaction.getTransactionType());
        psSave.setDouble(4, transaction.getAmountMoney());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            transaction.setTransactionID(rs.getLong(1));
        }
        close(rs);
        return transaction;

    }

    void method(int a, double d, char... chars) {
    }

    @Override
    public Transaction get(Serializable transactionID) throws SQLException {
        psGet.setLong(1, (long) transactionID);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        if (rs.next()) {
            Transaction transaction = new Transaction();
            transaction.setTransactionID(rs.getLong(1));
            transaction.setCardNumber(rs.getLong(2));
            transaction.setAccountID(rs.getLong(3));
            transaction.setTransactionType(rs.getString(4));
            transaction.setAmountMoney(rs.getDouble(5));
            return transaction;
        }
        close(rs);
        return null;
    }

    @Override
    public void update(Transaction transaction) throws SQLException {
        psUpdate.setLong(5, transaction.getTransactionID());
        psUpdate.setLong(1, transaction.getCardNumber());
        psUpdate.setLong(2, transaction.getAccountID());
        psUpdate.setString(3, transaction.getTransactionType());
        psUpdate.setDouble(4, transaction.getAmountMoney());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable transactionID) throws SQLException {
        psDelete.setLong(1, (long) transactionID);
        return psDelete.executeUpdate();
    }

    @Override

    public List<Transaction> getDayLimitWithdrawTransaction() throws SQLException {
        List<Transaction> list = new ArrayList<>();
        psGetDayLimitWithdrawTransaction.executeQuery();
        ResultSet rs = psGetDayLimitWithdrawTransaction.getResultSet();
        while (rs.next()) {
            Transaction transaction = new Transaction();
            transaction.setTransactionID(rs.getLong(1));
            transaction.setCardNumber(rs.getLong(2));
            transaction.setAccountID(rs.getLong(3));
            transaction.setTransactionType(rs.getString(4));
            transaction.setAmountMoney(rs.getDouble(5));
            list.add(transaction);
        }
        close(rs);
        return list;
    }

    public List<Transaction> getAllByClientID(Serializable clientID) throws SQLException {
        psGetAllByID.setLong(1, (long) clientID);
        List<Transaction> list = new ArrayList<>();
        psGetAllByID.executeQuery();
        ResultSet rs = psGetAllByID.getResultSet();
        while (rs.next()) {
            Transaction transaction = new Transaction();
            transaction.setTransactionID(rs.getLong(1));
            transaction.setCardNumber(rs.getLong(2));
            transaction.setAccountID(rs.getLong(3));
            transaction.setTransactionType(rs.getString(4));
            transaction.setAmountMoney(rs.getDouble(5));
            list.add(transaction);
        }
        close(rs);
        return list;
    }

    public List<Transaction> getAll() throws SQLException {
        List<Transaction> list = new ArrayList<>();
        psGetAll.executeQuery();
        ResultSet rs = psGetAll.getResultSet();
        while (rs.next()) {
            Transaction transaction = new Transaction();
            transaction.setTransactionID(rs.getLong(1));
            transaction.setCardNumber(rs.getLong(2));
            transaction.setAccountID(rs.getLong(3));
            transaction.setTransactionType(rs.getString(4));
            transaction.setAmountMoney(rs.getDouble(5));
            list.add(transaction);
        }
        close(rs);
        return list;
    }

}
