package dao.impl;

import dao.BaseDao;
import dao.ITransactionDao;
import org.springframework.stereotype.Repository;
import pojos.CreditCard;
import pojos.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Repository
public class TransactionDao extends BaseDao<Transaction> implements ITransactionDao {

    @PersistenceContext
    EntityManager entityManager;

    public List<Transaction> getTransactions() {
        Query query = entityManager.createQuery("FROM Transaction ");
        return query.getResultList();
    }

    public List<Transaction> getAllTransactionsForCard(Long cardNumber) {
        return entityManager
                .createQuery("FROM Transaction WHERE creditCard.cardNumber=:cardNumber")
                .setParameter("cardNumber", cardNumber).getResultList();
    }

    public List<Transaction> getAllTransactionsByClientID(Long clientID) {
        List<Transaction> transactionList;
        transactionList = entityManager.createQuery("FROM Transaction WHERE creditCard.client.clientID=:clientID")
                .setParameter("clientID", clientID).getResultList();
        return transactionList;
    }


}
