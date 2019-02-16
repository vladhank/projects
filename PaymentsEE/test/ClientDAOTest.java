import dao.ClientDAO;
import dao.Impl.ClientDAOImpl;
import databaseConnection.ConnectionManager;
import databaseConnection.ConnectionPool;
import entities.Client;
import org.junit.Assert;
import org.junit.Test;
import services.ClientService;
import services.Impl.ClientServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientDAOTest {
    private ClientDAO clientDAO = ClientDAOImpl.getInstance();
    private ClientService clientService = ClientServiceImpl.getInstance();

    public void initData() {
    }

    @Test
    public void saveTest() throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        Date date = new Date(1989 - 01 - 02);

        int beforeSave = clientDAO.getAll().size();
        Client newClient = clientDAO.save(new Client("Monya", "Newbie", "+375253213423", "Melnikaite 1-15", date, "logNB", "0975dmLP"));
        int afterSave = clientDAO.getAll().size();
        Assert.assertNotSame(beforeSave, afterSave);
        System.out.println(beforeSave);
        System.out.println(afterSave);
    }

    @Test
    public void getAuthorizationTest() throws SQLException, ParseException {
        Connection connection = ConnectionPool.getConnection();
        Client client = clientDAO.getAuthorizationInfo("loopSo");
        Client clientID2 = clientDAO.get(client.getClientID());
        Assert.assertEquals(clientID2, client);
        System.out.println(client);
    }


    @Test
    public void updateTest() throws SQLException, ParseException {
        Connection connection = ConnectionPool.getConnection();
        Client client = clientDAO.get(3L);
        client.setFirstName("Ivan");
        clientDAO.update(client);
        Client updatedClient = clientDAO.get(3L);
        Assert.assertEquals(client, updatedClient);
    }

    @Test
    public void getAllTest() throws SQLException {
        List<Client> list = new ArrayList<>(clientDAO.getAll());
        for (Client client : list) {
            System.out.println(client);
        }
    }

    @Test
    public void saveServiceTest() throws SQLException {
        Date date = new Date(1989 - 01 - 02);
        Client client = clientService.save(( new Client("Monya", "Newbie", "+375253213423", "Melnikaite 1-15", date, "logNB2", "0975dmLP") ));
    }

    @Test
    public void serviceTest() throws SQLException{
        List<Client> list=new ArrayList<>(clientService.getAll());
        System.out.println(list);
    }
}
