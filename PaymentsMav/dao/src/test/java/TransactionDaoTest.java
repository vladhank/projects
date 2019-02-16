import dao.impl.*;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/beans-hikari.xml")
@Transactional
public class TransactionDaoTest {

    @Autowired
    ClientDao clientDao;

    @Autowired
    TransactionDao transactionDao;

    @Autowired
    CreditCardDao creditCardDao;

    @Autowired
    ClientProfileDao clientProfileDao;

    @Autowired
    BankDao bankDao;

//    @Before
//    @Rollback(false)
    public void init() {
        Client testClient = new Client("Bobby", "Lee", "+375295674321", new Address("Minsk", "Main", "52B", "121"), LocalDate.of(2001, 04, 25), "moonLoght", "notRepeat", "Active",new HashSet<>(),null);
        Client testClient2 = new Client("Alan", "Walker", "+375447654321", new Address("Los Soligorsk", "Shaxterov", "1", "52"), LocalDate.of(1976, 01, 21), "login7", "password8", "Active",new HashSet<>(),null);
        Client testClient4 = new Client("Jack", "Morris", "+375445533215", new Address("Polock", "Efrasini", "54", "43B"), LocalDate.of(1985, 8, 05), "mbvfsa", "mcxv42sdf", "Active",new HashSet<>(),null);
        ClientProfile user = new ClientProfile("USER");
        ClientProfile admin = new ClientProfile("ADMIN");
        clientProfileDao.add(user);
        clientProfileDao.add(admin);
        clientDao.add(testClient);
        clientDao.add(testClient2);
        clientDao.add(testClient4);
        clientDao.get(testClient.getClientID()).getClientProfiles().add(user);
        clientDao.get(testClient2.getClientID()).getClientProfiles().add(user);
        clientDao.get(testClient4.getClientID()).getClientProfiles().add(admin);
        Bank testBank = new Bank("BFGNM123124FSDFAGADSASGAGAS23", testClient, 112000, new ArrayList<>());
        Bank testBank2 = new Bank("BDSOA123821NFSFA1239M", testClient2, 43000, new ArrayList<>());
        Bank testBank3 = new Bank("BQWE9823POLBVCXD37J", testClient4, 5200, new ArrayList<>());
        bankDao.add(testBank);
        bankDao.add(testBank2);
        bankDao.add(testBank3);
        CreditCard creditCard = new CreditCard(5643890743210091L, CardType.MASTERCARD, "Jojo", "Pojo", LocalDate.of(2022, 07, 23), 333, 5555, 7, CardStatus.ACTIVE, testBank, new ArrayList<>(), testClient);
        CreditCard creditCard2 = new CreditCard(4322558700884302L, CardType.VISA, "Alan", "Walker", LocalDate.of(2025, 11, 15), 652, 8218, 0, CardStatus.ACTIVE, testBank2,  new ArrayList<>(), testClient);
        CreditCard creditCard3 = new CreditCard(4531775344009622L, CardType.VISA, "Andrew", "Makarevich", LocalDate.of(2042, 03, LocalDate.now().getDayOfMonth()), 321, 5421, 8, CardStatus.ACTIVE, testBank3,  new ArrayList<>(), testClient4);
        testBank.getCardList().add(creditCard);
        testBank2.getCardList().add(creditCard2);
        testBank3.getCardList().add(creditCard3);
        bankDao.update(testBank);
        bankDao.update(testBank2);
        bankDao.update(testBank3);
        Transaction transaction = new Transaction(4531775344009622L, TransactionType.DEPOSIT, 2330, LocalDateTime.now(), creditCard3);
        Transaction transaction2 = new Transaction(4531775344009622L, TransactionType.DEPOSIT, 430, LocalDateTime.now(), creditCard3);
        Transaction transaction3 = new Transaction(5643890743210091L, TransactionType.WITHDRAW, 870, LocalDateTime.now(), creditCard2);
        transactionDao.add(transaction);
        transactionDao.add(transaction2);
        transactionDao.add(transaction3);
    }

   @Test
   @Rollback(false)
    public void getAllTransactionsTest(){
        List<Transaction> transactionList = transactionDao.getTransactions();
       Assert.assertEquals(3,transactionList.size());
   }

   @Test
   public void getAllTransactionsByClientIDTest(){
//       List<Transaction> transactionList = transactionDao.getAllTransactionsForCard(4531775344009622L);
       List<Transaction> transactionList = transactionDao.getAllTransactionsByClientID(3l);
       transactionList.forEach(System.out::println);
   }


   @Test
    public void userRoleTest(){
       System.out.println( clientDao.get(1L).getClientProfiles());
   }


}
