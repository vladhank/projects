package dao.Impl;

import dao.BankDAO;
import databaseConnection.ConnectionPool;
import entities.Bank;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BankDAOImpl extends AbstractDAO implements BankDAO {
    public final static Logger logger = Logger.getLogger(BankDAOImpl.class);
    private static volatile BankDAO INSTANCE = null;
    private static final String saveBankQuery = "INSERT  INTO BANK (BANK_ACCOUNT,CLIENT_ID,BALANCE) VALUES (?,?,?)";
    private static final String updateBankQuery = "UPDATE BANK SET BANK_ACCOUNT=?,CLIENT_ID=?,BALANCE=? WHERE ACCOUNT_ID=? ";
    private static final String getBankAccountQuery = "SELECT ACCOUNT_ID,BANK.BANK_ACCOUNT,BANK.CLIENT_ID,BALANCE FROM BANK  JOIN CREDIT_CARD ON BANK.BANK_ACCOUNT=CREDIT_CARD.BANK_ACCOUNT WHERE CARD_NUMBER=?";
    private static final String getAllBankAccountsQuery = "SELECT * FROM BANK";
    private static final String getAccountWithSmallBalance = "SELECT * FROM BANK WHERE BALANCE<=100";
    private static final String deleteBankAccountQuery = "DELETE FROM BANK WHERE ACCOUNT_ID=?";


    private PreparedStatement psSave;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psGetAll;
    private PreparedStatement psGetAccountWithSmallBalance;
    private PreparedStatement psDeleteBankAccountQuery;

    {
        try {
            psSave = ConnectionPool.getConnection().prepareStatement(saveBankQuery, Statement.RETURN_GENERATED_KEYS);
            psUpdate = ConnectionPool.getConnection().prepareStatement(updateBankQuery);
            psGet = ConnectionPool.getConnection().prepareStatement(getBankAccountQuery);
            psGetAll = ConnectionPool.getConnection().prepareStatement(getAllBankAccountsQuery);
            psGetAccountWithSmallBalance = ConnectionPool.getConnection().prepareStatement(getAccountWithSmallBalance);
            psDeleteBankAccountQuery = ConnectionPool.getConnection().prepareStatement(deleteBankAccountQuery);

        } catch (SQLException ex) {
            logger.error("Exception while creating connection for prepared statement", ex);
            ex.printStackTrace();
        }
    }

    public static BankDAO getInstance() {
        BankDAO bankDAO = INSTANCE;
        if (bankDAO == null) {
            synchronized (BankDAOImpl.class) {
                bankDAO = INSTANCE;
                if (bankDAO == null) {
                    INSTANCE = bankDAO = new BankDAOImpl();
                }
            }
        }
        return bankDAO;
    }

    @Override
    public Bank save(Bank bank) throws SQLException {
        psSave.setString(1, bank.getBankAccount());
        psSave.setLong(2, bank.getClientID());
        psSave.setDouble(3, bank.getBalance());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            bank.setAccountID(rs.getLong(1));
        }
        close(rs);
        return bank;

    }

    void method(int a, double d, char... chars) {
    }

    @Override
    public Bank get(Serializable cardNumber) throws SQLException {
        psGet.setLong(1, (long) cardNumber);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        if (rs.next()) {
            Bank bank = new Bank();
            bank.setAccountID(rs.getLong(1));
            bank.setBankAccount(rs.getString(2));
            bank.setClientID(rs.getLong(3));
            bank.setBalance(rs.getDouble(4));
            return bank;
        }
        close(rs);
        return null;
    }

    @Override
    public void update(Bank bank) throws SQLException {
        psUpdate.setLong(4, bank.getAccountID());
        psUpdate.setString(1, bank.getBankAccount());
        psUpdate.setLong(2, bank.getClientID());
        psUpdate.setDouble(3, bank.getBalance());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable accountID) throws SQLException {
        psDeleteBankAccountQuery.setLong(1, (long) accountID);
        return psDeleteBankAccountQuery.executeUpdate();
    }

    @Override
    public Bank getAccountWithSmallBalance() throws SQLException {
        psGetAccountWithSmallBalance.executeQuery();
        ResultSet rs = psGetAccountWithSmallBalance.getResultSet();
        Bank bank = new Bank();
        while (rs.next()) {
            bank.setAccountID(rs.getLong(1));
            bank.setBankAccount(rs.getString(2));
            bank.setClientID(rs.getLong(3));
            bank.setBalance(rs.getDouble(4));
            return bank;
        }
        close(rs);
        return bank;
    }

    @Override
    public List<Bank> getAll() throws SQLException {
        psGetAll.executeQuery();
        List<Bank> list = new ArrayList<>();
        ResultSet rs = psGetAll.getResultSet();
        while (rs.next()) {
            Bank bank = new Bank();
            bank.setAccountID(rs.getLong(1));
            bank.setBankAccount(rs.getString(2));
            bank.setClientID(rs.getLong(3));
            bank.setBalance(rs.getDouble(4));
            list.add(bank);
        }
        close(rs);
        return list;
    }
}
