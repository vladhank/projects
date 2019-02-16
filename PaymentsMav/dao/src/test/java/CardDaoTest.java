import dao.impl.ClientDao;
import dao.impl.ClientProfileDao;
import dao.impl.CreditCardDao;
import dao.impl.TransactionDao;
import enums.CardStatus;
import enums.CardType;
import enums.TransactionType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pojos.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/beans-hikari.xml")
//@ContextConfiguration(locations = "/beans.xml")
@Transactional
public class CardDaoTest {

    @Autowired
    ClientDao clientDao;

    @Autowired
    TransactionDao transactionDao;

    @Autowired
    CreditCardDao creditCardDao;

    @Autowired
    ClientProfileDao clientProfileDao;

//    @Before
//    @Rollback(false)
    public void init() {
        Client testClient = new Client("Bobby", "Lee", "+375295674321", new Address("Minsk", "Main", "52B", "121"), LocalDate.of(2001, 04, 25), "moonLoght", "notRepeat", "Active", new HashSet<>(), null);
        Client testClient2 = new Client("Alan", "Walker", "+375447654321", new Address("Los Soligorsk", "Shaxterov", "1", "52"), LocalDate.of(1976, 01, 21), "login7", "password8", "Active", new HashSet<>(), null);
        Client testClient4 = new Client("Jack", "Morris", "+375445533215", new Address("Polock", "Efrasini", "54", "43B"), LocalDate.of(1985, 8, 05), "mbvfsa", "mcxv42sdf", "Active", new HashSet<>(), null);
        ClientProfile user = new ClientProfile("USER");
        ClientProfile admin = new ClientProfile("ADMIN");
        clientProfileDao.add(user);
        clientProfileDao.add(admin);
        testClient.getClientProfiles().add(user);
        testClient2.getClientProfiles().add(user);
        testClient4.getClientProfiles().add(user);
        clientDao.add(testClient);
        clientDao.add(testClient2);
        clientDao.add(testClient4);
        CreditCard creditCard = new CreditCard(5643890743210091L, CardType.MASTERCARD, "Jojo", "Pojo", LocalDate.of(2022, 07, 23), 333, 5555, 7, CardStatus.ACTIVE, null, null, testClient);
        CreditCard creditCard2 = new CreditCard(4322558700884302L, CardType.VISA, "Alan", "Walker", LocalDate.of(2025, 11, 15), 652, 8218, 0, CardStatus.ACTIVE, null, null, testClient);
        CreditCard creditCard3 = new CreditCard(4531775344009622L, CardType.VISA, "Andrew", "Makarevich", LocalDate.of(2042, 03, LocalDate.now().getDayOfMonth()), 321, 5421, 8, CardStatus.ACTIVE, null, null, testClient4);
        creditCardDao.add(creditCard);
        creditCardDao.add(creditCard2);
        creditCardDao.add(creditCard3);
        Transaction transaction = new Transaction(4531775344009622L, TransactionType.DEPOSIT, 2330, LocalDateTime.now(), creditCard3);
        Transaction transaction2 = new Transaction(4531775344009622L, TransactionType.DEPOSIT, 430, LocalDateTime.now(), creditCard3);
        transactionDao.add(transaction);
        transactionDao.add(transaction2);
    }

    @Test
    @Transactional
    public void saveCardTest() {
//        ClientProfile user = new ClientProfile("USER");
//        ClientProfile admin = new ClientProfile("ADMIN");
//        clientProfileDao.add(user);
//        clientProfileDao.add(admin);
        Client testClient3 = new Client("Jack", "Nikolson", "+375297162534", new Address("Svetlcity", "Molodejnui", "23", "21"), LocalDate.now(), "login77", "password88", "Active", new HashSet<>(), null);
//        testClient3.getClientProfiles().add(user);
        clientDao.add(testClient3);
        CreditCard creditCard = new CreditCard(5111888844446666L, CardType.MASTERCARD, "Boris", "Godunov", LocalDate.of(2023, 12, 12), 783, 5790, 5, CardStatus.DISABLED, null, null, testClient3);
        creditCardDao.add(creditCard);
        Assert.assertEquals(creditCard.getFirstName(),creditCardDao.get(5111888844446666L).getFirstName());

    }

    @Test
    @Transactional
    public void getCardByCardNumber() {
        CreditCard creditCard = creditCardDao.get(5111888844446666L);
        Assert.assertNotNull(creditCardDao.getCreditCards());
    }

    @Test
    @Transactional
    public void updateCardTest() {
        CreditCard creditCard = creditCardDao.get(5643890743210091L);
        creditCard.setPin(1717);
        creditCardDao.update(creditCard);
        Assert.assertEquals(java.util.Optional.ofNullable(creditCardDao.get(5643890743210091L).getPin()), 1717);
    }

    @Test
    @Transactional
//    @Rollback(false)
    public void deleteCardTest() {
        creditCardDao.deleteById(4322558700884302L);
        Assert.assertNull(creditCardDao.get(4322558700884302L));
    }

    @Test
    public void getAllClientCards() {
        List<CreditCard> creditCardList = creditCardDao.getAllClientCards(1l);
        Assert.assertNotNull(creditCardList);
        System.out.println("****************All client cards****************");
        System.out.println(creditCardList.size());
        creditCardList.forEach(System.out::println);
    }

    @Test
    public void getExpiredCardsTest() {
        Client client = new Client("Zinedin", "Zidan", "+375255555555", new Address("Borisov", "Football", "17", "71"), LocalDate.of(1972, 06, 23), "zinadin", "realChempion", "Active", new HashSet<>(), null);
        client.getClientProfiles().add(clientProfileDao.get(1l));
        CreditCard creditCard = new CreditCard(5432123467890123L, CardType.MASTERCARD, "Zinedin", "Zidan", LocalDate.of(2017, 11, 17), 852, 3333, 25, CardStatus.ACTIVE, null, new ArrayList<>(), client);
        clientDao.add(client);
        creditCardDao.add(creditCard);
        List<CreditCard> creditCardList = creditCardDao.getExpiredCards();
        Assert.assertEquals("Zinedin", creditCardList.get(0).getFirstName());
    }

    @Test
    @Transactional
    public void getAllTransactionsTest() {
        List<Transaction> transactionList = creditCardDao.getCardTransactions(4531775344009622L);
        System.out.println(transactionList);
        Assert.assertNotNull(transactionList.get(0));
    }

    @Test
    public void getCreditCardByClientIDTest(){
        CreditCard creditCard = creditCardDao.getCreditCardByClientID(2L);

        Assert.assertNotNull(creditCard);
    }


}

