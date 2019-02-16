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
import org.springframework.test.context.TestExecutionListeners;
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
@Transactional
public class BankDaoTest {

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

    @Before
    @Rollback(false)
    public void init() {
        Client testClient = new Client("Bobby", "Lee", "+375295674321", new Address("Minsk", "Main", "52B", "121"), LocalDate.of(2001, 04, 25), "moonLoght", "notRepeat", "Active", new HashSet<>(), new ArrayList<>());
        Client testClient2 = new Client("Alan", "Walker", "+375447654321", new Address("Los Soligorsk", "Shaxterov", "1", "52"), LocalDate.of(1976, 01, 21), "login7", "password8", "Active", new HashSet<>(), new ArrayList<>());
        Client testClient4 = new Client("Jack", "Morris", "+375445533215", new Address("Polock", "Efrasini", "54", "43B"), LocalDate.of(1985, 8, 05), "mbvfsa", "mcxv42sdf", "Active", new HashSet<>(), new ArrayList<>());
        Client testClient5 = new Client("Zinedin", "Zidan", "+375255555555", new Address("Borisov", "Football", "17", "71"), LocalDate.of(1972, 06, 23), "zinadin", "realChempion", "Active", new HashSet<>(), new ArrayList<>());
        ClientProfile user = new ClientProfile("USER");
        ClientProfile admin = new ClientProfile("ADMIN");
        clientProfileDao.add(user);
        clientProfileDao.add(admin);
        clientDao.add(testClient);
        clientDao.add(testClient2);
        clientDao.add(testClient4);
        clientDao.add(testClient5);
        clientDao.get(testClient.getClientID()).getClientProfiles().add(user);
        clientDao.get(testClient2.getClientID()).getClientProfiles().add(user);
        clientDao.get(testClient4.getClientID()).getClientProfiles().add(admin);
        clientDao.get(testClient5.getClientID()).getClientProfiles().add(admin);
        Bank testBank = new Bank("BFGNM123124FSDFAGADSASGAGAS23", testClient, 112000, new ArrayList<>());
        Bank testBank2 = new Bank("BDSOA123821NFSFA1239M", testClient2, 43000, new ArrayList<>());
        Bank testBank3 = new Bank("BQWE9823POLBVCXD37J", testClient4, 5200, new ArrayList<>());
        Bank testBank4 = new Bank("BCZER9214150MCXVCXDDFS", testClient5, 35700000, new ArrayList<>());
        bankDao.add(testBank);
        bankDao.add(testBank2);
        bankDao.add(testBank3);
        bankDao.add(testBank4);
        CreditCard creditCard = new CreditCard(5643890743210091L, CardType.MASTERCARD, "Jojo", "Pojo", LocalDate.of(2022, 07, 23), 333, 5555, 7, CardStatus.ACTIVE, testBank, new ArrayList<>(), testClient2);
        CreditCard creditCard2 = new CreditCard(4322558700884302L, CardType.VISA, "Alan", "Walker", LocalDate.of(2025, 11, 15), 652, 8218, 0, CardStatus.ACTIVE, testBank2, new ArrayList<>(), testClient2);
        CreditCard creditCard3 = new CreditCard(4531775344009622L, CardType.VISA, "Andrew", "Makarevich", LocalDate.of(2042, 03, LocalDate.now().getDayOfMonth()), 321, 5421, 8, CardStatus.ACTIVE, testBank3, new ArrayList<>(), testClient4);
        CreditCard creditCard4 = new CreditCard(5432123467890123L, CardType.MASTERCARD, "Zinedin", "Zidan", LocalDate.of(2031, 11, 17), 852, 3333, 25, CardStatus.ACTIVE, testBank4, new ArrayList<>(), testClient5);
        testBank.getCardList().add(creditCard);
        testBank2.getCardList().add(creditCard2);
        testBank3.getCardList().add(creditCard3);
        testBank4.getCardList().add(creditCard4);
        bankDao.update(testBank);
        bankDao.update(testBank2);
        bankDao.update(testBank3);
        Transaction transaction = new Transaction(4531775344009622L, TransactionType.DEPOSIT, 2330, LocalDateTime.now(), creditCard3);
        Transaction transaction2 = new Transaction(4531775344009622L, TransactionType.DEPOSIT, 430, LocalDateTime.now(), creditCard3);
        Transaction transaction3 = new Transaction(5643890743210091L, TransactionType.WITHDRAW, 870, LocalDateTime.now(), creditCard2);
        Transaction transaction4 = new Transaction(4322558700884302L, TransactionType.DEPOSIT, 230, LocalDateTime.now(), creditCard);
        transactionDao.add(transaction);
        transactionDao.add(transaction2);
        transactionDao.add(transaction3);
    }

    @Test
    @Rollback(false)
    public void getAllBankAccountsTest() {
        List<Bank> bankList = bankDao.getBankAccounts();
        Assert.assertEquals(4, bankList.size());
    }

    @Test
    public void deleteAccountTest() {
        bankDao.deleteById(1L);
        Assert.assertNull(bankDao.get(1L));
    }

    @Test
    public void getBankAccountInfoByClientIDTest() {
        Bank bankAccount = bankDao.getBankAccountInfoByClientID(1L);
        System.out.println(bankAccount);
        Assert.assertNotNull(bankAccount);
    }

    @Test
    public void getBalanceByClientIDTest() {
        Double balance = bankDao.getBalanceByClientID(1l);
        System.out.println(balance);
        Assert.assertNotNull(balance);
    }

    @Test
    public void getBankAccountByCreditCardNumber() {
        Bank bank = bankDao.getBankAccountByCreditCardNumber(5432123467890123L);
        Bank bank2 = bankDao.getBankAccountByCreditCardNumber(1234567887654321L);
        Assert.assertNotNull(bank);
        Assert.assertNull(bank2);
    }

}
