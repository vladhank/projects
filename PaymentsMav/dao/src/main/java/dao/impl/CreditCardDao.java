package dao.impl;

import dao.BaseDao;
import dao.ICreditCardDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import pojos.CreditCard;
import pojos.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Repository
public class CreditCardDao extends BaseDao<CreditCard> implements ICreditCardDao {

    @PersistenceContext
    EntityManager entityManager;

    public List<CreditCard> getCreditCards(){
        Query query = entityManager.createQuery("FROM CreditCard ");
        return query.getResultList();
    }

    public List<CreditCard> getAllClientCards(Serializable clientID){
        Query query = entityManager.createQuery("FROM CreditCard cc WHERE cc.client.clientID=:id ");
        query.setParameter("id",clientID);
        return query.getResultList();
    }

    public List<CreditCard> getExpiredCards(){
        return ( List<CreditCard> ) entityManager.createQuery("FROM CreditCard cc WHERE cc.expDate<=:date ")
                .setParameter("date", LocalDate.now()).getResultList();
    }

    public List<Transaction> getCardTransactions(long cardNumber){
        return ( List<Transaction> ) entityManager.createQuery("SELECT cc.transactionList FROM CreditCard cc WHERE cc.cardNumber=:cardNumber")
                .setParameter("cardNumber",cardNumber).getResultList();
    }


    public double getBalance(long cardNumber){
        double balance = ( double ) entityManager.createQuery("SELECT b.balance FROM Bank b JOIN b.cardList cl  WHERE cl.cardNumber=:cardNumber")
                .setParameter("cardNumber",cardNumber).getSingleResult();
        return balance;
    }

   public CreditCard getCreditCardByClientID(Long clientID){

        return ( CreditCard ) entityManager.createQuery("FROM CreditCard WHERE client.clientID=:clientID").setParameter("clientID",clientID).getResultList().get(0);
   }
}
