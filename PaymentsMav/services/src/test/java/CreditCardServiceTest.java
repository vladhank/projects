import enums.CardStatus;
import enums.CardType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pojos.Address;
import pojos.Client;
import pojos.ClientProfile;
import pojos.CreditCard;
import services.IClientService;
import services.ICreditCardService;

import java.time.LocalDate;
import java.util.HashSet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/service-beans.xml")
@Transactional
public class CreditCardServiceTest {

    @Autowired
    ICreditCardService creditCardService;

    @Autowired
    IClientService clientService;

//    @Before
    @Test
    public void init() {
        Client testClient = new Client("Jack", "Nikolson", "+375297162534", new Address("Svetlcity", "Molodejnui", "23", "21"), LocalDate.now(), "login77", "password88", "Active",new HashSet<>(),null);
        Client testClient2 = new Client("Jack", "Morris", "+375445533215", new Address("Polock", "Efrasini", "54", "43B"), LocalDate.of(1985, 8, 05), "mbvfsa", "mcxv42sdf", "Active",new HashSet<>(),null);
        Client testClient3 = new Client("Alan", "Walker", "+375447654321", new Address("Los Soligorsk", "Shaxterov", "1", "52"), LocalDate.of(1976, 01, 21), "login7", "password8", "Active",new HashSet<>(),null);
        testClient.getClientProfiles().add(new ClientProfile("USER"));
        testClient2.getClientProfiles().add(new ClientProfile("USER"));
        testClient3.getClientProfiles().add(new ClientProfile("USER"));
        clientService.add(testClient);
        clientService.add(testClient2);
        clientService.add(testClient3);
        CreditCard creditCard = new CreditCard(5643890743210091L, CardType.MASTERCARD, "Jojo", "Pojo", LocalDate.of(2022, 07, 23), 333, 5555, 7, CardStatus.ACTIVE, null, null, testClient);
        CreditCard creditCard2 = new CreditCard(4322558700884302L, CardType.VISA, "Alan", "Walker", LocalDate.of(2025, 11, 15), 652, 8218, 0, CardStatus.ACTIVE, null, null, testClient);
        CreditCard creditCard3 = new CreditCard(4531775344009622L, CardType.VISA, "Andrew", "Makarevich", LocalDate.of(2042, 03, LocalDate.now().getDayOfMonth()), 321, 5421, 8, CardStatus.ACTIVE, null, null, testClient2);
        creditCardService.add(creditCard);
        creditCardService.add(creditCard2);
        creditCardService.add(creditCard3);
    }

    @Test
    public void getAllClientCardsTest(){
        Client client = clientService.findByLogin("login7");
        System.out.println("ClientID" + client.getClientID());
    }

    @Test
    @Rollback(false)
    public void deleteCreditCardTest(){

    }



}
