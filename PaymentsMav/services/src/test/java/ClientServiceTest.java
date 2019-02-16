import dao.impl.ClientDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pojos.Address;
import pojos.Client;
import pojos.ClientProfile;
import pojos.CreditCard;
import services.IClientProfileService;
import services.IClientService;
import services.impl.ClientService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/service-beans.xml")
@Transactional
public class ClientServiceTest {

    @Autowired
    IClientService clientService;

    @Autowired
    IClientProfileService clientProfileService;

//    @Before
//    @Rollback
    public void init(){
        Client testClient = new Client("Jack", "Nikolson", "+375297162534", new Address("Svetlcity", "Molodejnui", "23", "21"), LocalDate.now(), "login77", "password88", "Active",new HashSet<>(),null);
        Client testClient2 = new Client("Jack", "Morris", "+375445533215", new Address("Polock", "Efrasini", "54", "43B"), LocalDate.of(1985, 8, 05), "mbvfsa", "mcxv42sdf", "Active",new HashSet<>(),null);
        testClient.getClientProfiles().add(new ClientProfile("USER"));
        testClient2.getClientProfiles().add(new ClientProfile("USER"));
        clientService.add(testClient);
        clientService.add(testClient2);
    }

    @Test
    @Rollback(false)
    public void deleteClientServiceTest(){
//        Client clientFromDb = clientService.get(1L);
//        clientService.delete(clientFromDb);
//        Assert.assertNull(clientService.get(clientFromDb.getClientID()));
        clientService.delete(clientService.get(2l));
    }

    @Test
    public void updateTest(){
        Client client = clientService.get(2L);
        client.getAddress().setCity("Erevan");
        clientService.update(client);
        Assert.assertEquals("Erevan",clientService.get(client.getClientID()).getAddress().getCity());
    }

    @Test
    public void getAllClientsServiceTest() {
        List<Client> clientList = clientService.getClients();
        Assert.assertEquals(4,clientList.size());
        ClientProfile clientProfile = clientProfileService.get(1L);
        System.out.println( clientProfile.getType());
        Assert.assertEquals("USER",clientProfile.getType());
    }

    @Test
    public void getAllClientCards(){
        Client client = clientService.findByLogin("login7");
        List<CreditCard> creditCardList = client.getCards();
        for(CreditCard creditCard:creditCardList){
            System.out.println(creditCard);
        }
    }

    @Test
    public void findClientByLoginServiceTest(){
        Client client = clientService.findByLogin("mbvfsa");
        Assert.assertNotNull(client);
    }


}
