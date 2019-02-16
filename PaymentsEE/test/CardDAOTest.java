import dao.BankDAO;
import dao.ClientDAO;
import dao.CreditCardDAO;
import dao.Impl.BankDAOImpl;
import dao.Impl.ClientDAOImpl;
import dao.Impl.CreditCardDAOImpl;
import databaseConnection.ConnectionPool;
import entities.Client;
import entities.CreditCard;
import org.junit.Assert;
import org.junit.Test;
import services.CreditCardService;
import services.Impl.CreditCardServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CardDAOTest {
    private CreditCardDAO creditCardDAO = CreditCardDAOImpl.getInstance();
    private ClientDAO clientDAO = ClientDAOImpl.getInstance();
    private BankDAO bankDAO= BankDAOImpl.getInstance();
    private CreditCardService creditCardService = CreditCardServiceImpl.getInstance();

    public void initData() {

    }

    @Test
    public void getBalanceTest() throws SQLException, ParseException {
        Connection connection = ConnectionPool.getConnection();
        Client client = clientDAO.get(1L);
        double testBalance = 0;
        Assert.assertNotSame(testBalance, creditCardDAO.getBalance(4110559275236328L));
    }

    @Test
    public void getCardByNumberTest() throws SQLException, ParseException {
        Connection connection = ConnectionPool.getConnection();
        CreditCard creditCard = creditCardDAO.get(4110559275236328L);
        Assert.assertNotNull(creditCard);
    }

    @Test
    public void getCardByIDTest() throws SQLException, ParseException {
        Connection connection = ConnectionPool.getConnection();
        List<CreditCard> list = creditCardDAO.getCardsByID(1L);
        System.out.println(list);
        Assert.assertNotNull(list);
    }

    @Test
    public void getCardByIDServiceTest() throws SQLException, ParseException {
        Connection connection = ConnectionPool.getConnection();
        List<CreditCard> list = creditCardService.getCardsByID(1L);
        System.out.println(list);
        Assert.assertNotNull(list);
    }

    @Test
    public void updateTest() throws SQLException, ParseException {
        Connection connection = ConnectionPool.getConnection();
        CreditCard testCreditCard = creditCardDAO.get(4110559275236328L);
        Assert.assertNotNull(testCreditCard);
    }

    @Test
    public void expiredCardsTest() throws SQLException, ParseException {
        List<CreditCard> list = new ArrayList<>(creditCardDAO.getExpiredCards());
        Assert.assertNotNull(list);
    }

    @Test
    public void sendMoneyTest() throws Throwable{
      creditCardService.sendMoney(1200,4567124598526401L,5343678223196505L);
    }

}

