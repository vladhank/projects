import dao.BankDAO;
import dao.Impl.BankDAOImpl;
import databaseConnection.ConnectionPool;
import entities.Bank;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class BankDAOTest {
    private BankDAO bankDAO = BankDAOImpl.getInstance();

    public void initData() {

    }

    @Test
    public void saveTest() throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        int beforeSave = bankDAO.getAll().size();
        Bank newAccount = bankDAO.save(new Bank("BNUYTRASASDAFASFASFASFAS",1,8700));
        int afterSave = bankDAO.getAll().size();
        Assert.assertNotSame(beforeSave, afterSave);
        connection.rollback();
    }
    @Test
    public void getBankAccountTest() throws SQLException, ParseException {
        Bank newAccount = bankDAO.get(4567124598526401L);
        List<Bank> testAccount = bankDAO.getAll();
        Assert.assertNotSame(newAccount, testAccount.get(1));
    }

    @Test
    public void updateBankTest() throws SQLException, ParseException {
        Bank newAccount = bankDAO.get(4567124598526401L);
        Bank updatedAccount = bankDAO.get(4567124598526401L);
        updatedAccount.setBalance(newAccount.getBalance() + 600);
        bankDAO.update(updatedAccount);
        System.out.println(newAccount);
        System.out.println(updatedAccount);
    }
}
