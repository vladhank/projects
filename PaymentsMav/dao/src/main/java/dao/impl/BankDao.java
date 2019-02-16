package dao.impl;

import dao.BaseDao;
import dao.IBankDao;
import org.springframework.stereotype.Repository;
import pojos.Bank;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BankDao extends BaseDao<Bank> implements IBankDao {

    @PersistenceContext
    EntityManager entityManager;

    public List<Bank> getBankAccounts() {

        List<Bank> bankList = entityManager.createQuery("FROM Bank ").getResultList();
        return bankList;
    }

    public Bank getBankAccountInfoByClientID(Long clientID){
        Bank bank = ( Bank ) entityManager.createQuery("FROM Bank WHERE client.clientID=:clientID").setParameter("clientID",clientID).getSingleResult();
        return bank;
    }

    public Double getBalanceByClientID(Long clientID){
        return ( Double ) entityManager.createQuery("SELECT balance FROM Bank WHERE client.clientID=:clientID")
                .setParameter("clientID",clientID).getSingleResult();
    }

    public Bank getBankAccountByCreditCardNumber(long cardNumber){
        List<Object[]> res = entityManager.createQuery("FROM Bank b join b.cardList cl  WHERE cl.cardNumber=:cardNumber")
                .setParameter("cardNumber",cardNumber).getResultList();
        if(res.size()==0){
            return null;
        }
        Object[] objects = res.get(0);
        Bank bank = ( Bank ) objects[0];
        return bank;
    }

}
