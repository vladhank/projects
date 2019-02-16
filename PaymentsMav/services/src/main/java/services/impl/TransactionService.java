package services.impl;

import dao.ITransactionDao;
import dao.impl.ClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojos.Transaction;
import services.BaseService;
import services.ITransactionService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TransactionService extends BaseService<Transaction> implements ITransactionService {

    @Autowired
    ITransactionDao transactionDao;

    public List<Transaction> getTransactions() {
        return transactionDao.getTransactions();
    }
    public List<Transaction> getAllTransactionsByClientID(Long clientID){return transactionDao.getAllTransactionsByClientID(clientID);}
}
