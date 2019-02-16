package services;

import entities.Bank;

import java.io.Serializable;
import java.util.List;

public interface BankService {
    Bank save(Bank bank);

    Bank get(Serializable id);

    void update(Bank bank);

    int delete(Serializable id);

    List<Bank> getAll();
}
