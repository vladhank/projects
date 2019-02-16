import dao.impl.ClientDao;
import dao.impl.ClientProfileDao;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pojos.Address;
import pojos.Client;
import pojos.ClientProfile;
import pojos.CreditCard;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/beans-hikari.xml")
//@ContextConfiguration(locations = "/beans.xml")
@Transactional
public class ClientDaoTest {

    public final static Logger logger = Logger.getLogger(ClientDaoTest.class);

    @Autowired
    ClientDao clientDao;

    @Autowired
    ClientProfileDao clientProfileDao;

    @PersistenceContext(name = "entityManagerFactory")
    EntityManager entityManager;

    @Before
    public void init() {
        Client testClient3 = new Client("Jack", "Nikolson", "+375297162534", new Address("Svetlcity", "Molodejnui", "23", "21"), LocalDate.now(), "login77", "password88", "Active",new HashSet<>(),null);
        Client testClient4 = new Client("Jack", "Morris", "+375445533215", new Address("Polock", "Efrasini", "54", "43B"), LocalDate.of(1985, 8, 05), "mbvfsa", "mcxv42sdf","Active",new HashSet<>(), null);
        Client testClient5 = new Client("Jack", "Morris", "+375445533215", new Address("Polock", "Efrasini", "54", "43B"), LocalDate.of(1985, 8, 05), "mbvfsa", "mcxv42sdf","Active",new HashSet<>(), null);
        ClientProfile user = new ClientProfile("USER");
        ClientProfile admin = new ClientProfile("ADMIN");
        clientProfileDao.add(user);
        clientProfileDao.add(admin);
        testClient3.getClientProfiles().add(user);
        testClient4.getClientProfiles().add(user);
        testClient5.getClientProfiles().add(user);
        clientDao.add(testClient3);
        clientDao.add(testClient4);
    }

    @Test
    @Transactional
    public void findByLoginTest() {
        Client testClient = new Client("Bobby", "Lee", "+375295674321", new Address("Minsk", "Main", "52B", "121"), LocalDate.of(2001, 04, 25), "moonLoght", "notRepeat","Active",new HashSet<>(), null);
        Client testClient2 = new Client("Alan", "Walker", "+375447654321", new Address("Los Soligorsk", "Shaxterov", "1", "52"), LocalDate.of(1976, 01, 21), "login7", "password8","Active",new HashSet<>(), null);
        testClient.getClientProfiles().add(new ClientProfile("USER"));
        testClient2.getClientProfiles().add(new ClientProfile("USER"));
        clientDao.add(testClient);
        clientDao.add(testClient2);
        Client newClient = clientDao.findByLogin("login7");
        System.out.println(newClient);
        Assert.assertEquals(newClient.getFirstName(), "Alan");
    }

    @Test
    @Transactional
    public void getClientTest() {
        Client testClient = clientDao.get(1L);
        Assert.assertNotNull(testClient);
    }

    @Test
    @Transactional
    public void updateClientTest() {
        Client client = clientDao.get(2L);
        client.setFirstName("Michael");
        clientDao.update(client);
        Client updatedClient = clientDao.get(client.getClientID());
        Assert.assertEquals(updatedClient.getFirstName(), "Michael");
    }

    @Test
    @Transactional
    public void deleteClientTest() {
        List<Client> clientList = clientDao.getClients();
        clientDao.deleteById(2L);
        List<Client> listAfterDelete = clientDao.getClients();
        Assert.assertNotEquals(clientList.size(), listAfterDelete.size());
    }

    @Test
    public void getAllClientCards(){
        Client client = clientDao.get(1l);
        List<CreditCard> creditCardList = client.getCards();
        for(CreditCard creditCard:creditCardList){
            System.out.println(creditCard);
        }
    }

    @Test
    public void getAllClientsTest(){
        List<Client> clientList = clientDao.getClients();
       for(Client client:clientList){
           System.out.println(client);
       }
    }

}
