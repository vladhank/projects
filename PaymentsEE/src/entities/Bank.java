package entities;

import java.util.Objects;

public class Bank {
    private long accountID;
    private String bankAccount;
    private long clientID;
    private double balance;

    public Bank() {
    }

    public Bank(String bankAccount, long clientID, double balance) {
        this.bankAccount = bankAccount;
        this.clientID = clientID;
        this.balance = balance;
    }

    public long getAccountID() {
        return accountID;
    }

    public String getBankAccount() {
        return bankAccount;
    }


    public long getClientID() {
        return clientID;
    }

    public double getBalance() {
        return balance;
    }

    public void setAccountID(long accountID) {
        this.accountID = accountID;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void setClientID(long clientID) {
        this.clientID = clientID;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return accountID == bank.accountID &&
                clientID == bank.clientID &&
                balance == bank.balance &&
                Objects.equals(bankAccount, bank.bankAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountID, bankAccount, clientID, balance);
    }

    @Override
    public String toString() {
        return "Bank{" +
                "accountID=" + accountID +
                ", bankAccount='" + bankAccount + '\'' +
                ", clientID=" + clientID +
                ", balance=" + balance +
                '}';
    }
}
