package dao.Impl;

import dao.CreditCardDAO;
import databaseConnection.ConnectionPool;
import entities.CreditCard;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CreditCardDAOImpl extends AbstractDAO implements CreditCardDAO {
    private final static Logger logger = Logger.getLogger(CreditCardDAOImpl.class);
    private static volatile CreditCardDAO INSTANCE = null;
    private static final String saveCreditCardQuery = "INSERT INTO CREDIT_CARD (CARD_NUMBER,CARD_COMPANY,FIRST_NAME,LAST_NAME,EXP_DATE,CVV,PIN,BANK_ACCOUNT,CASHBACK,STATUS,CLIENT_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    private static final String updateCreditCardQuery = "UPDATE CREDIT_CARD SET CARD_COMPANY=?,FIRST_NAME=?,LAST_NAME=?,EXP_DATE=?,CVV=?,PIN=?,BANK_ACCOUNT=?,CASHBACK=?,STATUS=? CLIENT_ID=? WHERE CARD_NUMBER=?";
    private static final String getCreditCardInfoQuery = "SELECT * FROM CREDIT_CARD WHERE CARD_NUMBER=?";
    private static final String getCreditCardsQuery = "SELECT * FROM CREDIT_CARD";
    private static final String getCreditCardsByClientIDQuery = "SELECT * FROM CREDIT_CARD WHERE CLIENT_ID=?";
    private static final String getExpiredCardsQuery = "SELECT * FROM CREDIT_CARD WHERE LEFT(EXP_DATE,2) < MONTH(NOW()) AND RIGHT(EXP_DATE,2) <= RIGHT(YEAR(NOW()),2)";
    private static final String getBalanceQuery = "SELECT BALANCE FROM BANK JOIN CREDIT_CARD ON BANK.BANK_ACCOUNT=CREDIT_CARD.BANK_ACCOUNT WHERE CARD_NUMBER=?";
    private static final String deleteCreditCardQuery = "DELETE FROM CREDIT_CARD WHERE CARD_NUMBER=?";

    private PreparedStatement psSave;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psGetAll;
    private PreparedStatement psGetExpiredCards;
    private PreparedStatement psDelete;
    private PreparedStatement psGetBalance;
    private PreparedStatement psGetByID;

    {
        try {
            psSave = ConnectionPool.getConnection().prepareStatement(saveCreditCardQuery);
            psUpdate = ConnectionPool.getConnection().prepareStatement(updateCreditCardQuery);
            psGet = ConnectionPool.getConnection().prepareStatement(getCreditCardInfoQuery);
            psGetAll = ConnectionPool.getConnection().prepareStatement(getCreditCardsQuery);
            psGetExpiredCards = ConnectionPool.getConnection().prepareStatement(getExpiredCardsQuery);
            psDelete = ConnectionPool.getConnection().prepareStatement(deleteCreditCardQuery);
            psGetBalance = ConnectionPool.getConnection().prepareStatement(getBalanceQuery);
            psGetByID = ConnectionPool.getConnection().prepareStatement(getCreditCardsByClientIDQuery);
        } catch (SQLException ex) {
            logger.error("Exception while creating connection for prepared statement", ex);
            ex.printStackTrace();
        }
    }

    public CreditCardDAOImpl() {
    }


    public static CreditCardDAO getInstance() {
        CreditCardDAO creditCardDAO = INSTANCE;
        if (creditCardDAO == null) {
            synchronized (ClientDAOImpl.class) {
                creditCardDAO = INSTANCE;
                if (creditCardDAO == null) {
                    INSTANCE = creditCardDAO = new CreditCardDAOImpl();
                }
            }
        }
        return creditCardDAO;
    }

    @Override
    public CreditCard save(CreditCard creditCard) throws SQLException {
        psSave.setLong(1, creditCard.getCardNumber());
        psSave.setString(2, creditCard.getCardCompany());
        psSave.setString(3, creditCard.getFirstName());
        psSave.setString(4, creditCard.getLastName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yy");
        String stringDate = dateFormat.format(creditCard.getExpDate());
        psSave.setString(5, stringDate);
        psSave.setInt(6, creditCard.getCvv());
        psSave.setInt(7, creditCard.getPin());
        psSave.setString(8, creditCard.getBankAccount());
        psSave.setInt(9, creditCard.getCashBack());
        psSave.setString(10, creditCard.getStatus());
        psSave.setLong(11, creditCard.getClientID());
        psSave.executeUpdate();
        return creditCard;
    }

    void method(int a, double d, char... chars) {
    }

    public CreditCard get(Serializable cardNumber) throws SQLException, ParseException {
        psGet.setLong(1, (long) cardNumber);
        psGet.executeQuery();

        ResultSet rs = psGet.getResultSet();
        if (rs.next()) {
            CreditCard creditCard = new CreditCard();
            creditCard.setCardNumber(rs.getLong(1));
            creditCard.setCardCompany(rs.getString(2));
            creditCard.setFirstName(rs.getString(3));
            creditCard.setLastName(rs.getString(4));
            String date = rs.getString(5);
            String pattern = "MM/yy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            creditCard.setExpDate(simpleDateFormat.parse(date));
            creditCard.setCvv(rs.getInt(6));
            creditCard.setPin(rs.getInt(7));
            creditCard.setBankAccount(rs.getString(8));
            creditCard.setCashBack(rs.getInt(9));
            creditCard.setStatus(rs.getString(10));
            creditCard.setClientID(rs.getLong(11));
            return creditCard;
        }
        close(rs);
        return null;
    }

    public  List<CreditCard> getCardsByID(Serializable clientID) throws SQLException, ParseException {
        psGetByID.setLong(1, (long) clientID);
        List<CreditCard> list = new ArrayList<>();
        psGetByID.executeQuery();
        ResultSet rs = psGetByID.getResultSet();
        while (rs.next()) {
            CreditCard creditCard = new CreditCard();
            creditCard.setCardNumber(rs.getLong(1));
            creditCard.setCardCompany(rs.getString(2));
            creditCard.setFirstName(rs.getString(3));
            creditCard.setLastName(rs.getString(4));
            String pattern = "MM/yy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = rs.getString(5);
            creditCard.setExpDate(simpleDateFormat.parse(date));
            creditCard.setCvv(rs.getInt(6));
            creditCard.setPin(rs.getInt(7));
            creditCard.setBankAccount(rs.getString(8));
            creditCard.setCashBack(rs.getInt(9));
            creditCard.setStatus(rs.getString(10));
            creditCard.setClientID(rs.getLong(11));
            list.add(creditCard);
        }
        close(rs);
        return list;
    }

    public void update(CreditCard creditCard) throws SQLException {
        psUpdate.setLong(11, creditCard.getCardNumber());
        psUpdate.setString(1, creditCard.getCardCompany());
        psUpdate.setString(2, creditCard.getFirstName());
        psUpdate.setString(3, creditCard.getLastName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yy");
        String stringDate = dateFormat.format(creditCard.getExpDate());
        psUpdate.setString(4, stringDate);
        psUpdate.setInt(5, creditCard.getCvv());
        psUpdate.setInt(6, creditCard.getPin());
        psUpdate.setString(7, creditCard.getBankAccount());
        psUpdate.setInt(8, creditCard.getCashBack());
        psUpdate.setString(9, creditCard.getStatus());
        psSave.setLong(10, creditCard.getClientID());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable cardNumber) throws SQLException {
        psDelete.setLong(1, (long) cardNumber);
        return psDelete.executeUpdate();
    }

    @Override
    public List<CreditCard> getExpiredCards() throws SQLException, ParseException {
        List<CreditCard> list = new ArrayList<>();
        psGetExpiredCards.executeQuery();
        ResultSet rs = psGetExpiredCards.getResultSet();
        while (rs.next()) {
            CreditCard creditCard = new CreditCard();
            creditCard.setCardNumber(rs.getLong(1));
            creditCard.setCardCompany(rs.getString(2));
            creditCard.setFirstName(rs.getString(3));
            creditCard.setLastName(rs.getString(4));
            String pattern = "MM/yy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = rs.getString(5);
            creditCard.setExpDate(simpleDateFormat.parse(date));
            creditCard.setCvv(rs.getInt(6));
            creditCard.setPin(rs.getInt(7));
            creditCard.setBankAccount(rs.getString(8));
            creditCard.setCashBack(rs.getInt(9));
            creditCard.setStatus(rs.getString(10));
            creditCard.setClientID(rs.getLong(11));
            list.add(creditCard);
        }
        close(rs);
        return list;

    }

    @Override
    public double getBalance(long cardNumber) throws SQLException {
        double currentBalance = 0;
        psGetBalance.setLong(1, (long) cardNumber);
        psGetBalance.executeQuery();
        ResultSet rs = psGetBalance.getResultSet();
        while (rs.next()) {
            currentBalance = rs.getDouble(1);
        }
        close(rs);
        return currentBalance;
    }


    @Override
    public List<CreditCard> getAll() throws SQLException, ParseException {
        List<CreditCard> list = new ArrayList<>();
        psGetAll.executeQuery();
        ResultSet rs = psGetAll.getResultSet();
        while (rs.next()) {
            CreditCard creditCard = new CreditCard();
            creditCard.setCardNumber(rs.getLong(1));
            creditCard.setCardCompany(rs.getString(2));
            creditCard.setFirstName(rs.getString(3));
            creditCard.setLastName(rs.getString(4));
            String pattern = "MM/yy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = rs.getString(5);
            creditCard.setExpDate(simpleDateFormat.parse(date));
            creditCard.setCvv(rs.getInt(6));
            creditCard.setPin(rs.getInt(7));
            creditCard.setBankAccount(rs.getString(8));
            creditCard.setCashBack(rs.getInt(9));
            creditCard.setStatus(rs.getString(10));
            creditCard.setClientID(rs.getLong(11));
            list.add(creditCard);
        }
        close(rs);
        return list;
    }

}
