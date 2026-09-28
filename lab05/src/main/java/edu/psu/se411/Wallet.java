package edu.psu.se411;

import edu.psu.se411.exceptions.InsufficientFundsException;

public class Wallet {

    private double balance;

    public Wallet(double balance) {
        this.balance = balance;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds.");
        }

        balance = balance - amount;
    }
}