import dao.Impl.TransactionDAOImpl;
import dao.TransactionDAO;
import entities.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAOTest {
    private TransactionDAO transactionDAO = TransactionDAOImpl.getInstance();

    public void initData() {

    }

    @Test
    public void getAllTest() throws SQLException {
        List<Transaction> list = new ArrayList<>();
        list.addAll(transactionDAO.getAll());
        System.out.println(list);
    }

    @Test
    public void getAllByClientIDTest() throws SQLException {
        List<Transaction> list = new ArrayList<>();
        list.addAll(transactionDAO.getAllByClientID(3L));
        Assert.assertNotNull(list);
        System.out.println(list);
    }

}
