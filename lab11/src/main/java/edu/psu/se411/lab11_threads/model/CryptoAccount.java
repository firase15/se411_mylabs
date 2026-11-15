package edu.psu.se411.lab11_threads.model;

import edu.psu.se411.lab11_threads.exceptions.InsufficientBalanceException;

public class CryptoAccount {

    private String id;
    private Double balance;

    public CryptoAccount(String id, Double balance) {
        this.id = id;
        this.balance = balance;
    }

    public synchronized void addToBalance(Double amount) {
        balance += amount;
    }

    public synchronized void retrieveFromBalance(Double amount)
            throws InsufficientBalanceException {

        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        balance -= amount;
    }

    public String getId() {
        return id;
    }

    public Double getBalance() {
        return balance;
    }
}