package edu.psu.se411.lab11_threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.se411.lab11_threads.model.CryptoAccount;
import edu.psu.se411.lab11_threads.model.Transaction;

public class App {

    static Logger logger = LoggerFactory.getLogger(App.class);

    public static Integer nbAccounts = 10;
    public static Double initialBalance = 1000.0;
    public static Integer nbTransactions = 10000;

    public static double calculateTotalBalance(CryptoAccount[] accounts) {
        double total = 0;

        for (CryptoAccount account : accounts) {
            total += account.getBalance();
        }

        return total;
    }

    public static void main(String[] args) {

        logger.info("Application is starting...");

        CryptoAccount[] accounts = new CryptoAccount[nbAccounts];

        for (int i = 0; i < nbAccounts; i++) {
            accounts[i] = new CryptoAccount("acc_" + i, initialBalance);
        }

        System.out.println("Initial total: " + calculateTotalBalance(accounts));

        Thread[] threads = new Thread[nbTransactions];

        for (int i = 0; i < nbTransactions; i++) {

            String id = "transaction_" + i;

            threads[i] = new Thread(() -> {

                CryptoAccount randomA = accounts[(int) (Math.random() * nbAccounts)];
                CryptoAccount randomB = accounts[(int) (Math.random() * nbAccounts)];

                Double randomAmount = Math.random() * 10 + 1;

                Transaction t = new Transaction(id, randomA, randomB, randomAmount);

                t.execute();
            });

            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                logger.error("Thread interrupted", e);
            }
        }

        System.out.println("Final total: " + calculateTotalBalance(accounts));

        logger.info("Application is closing...");
    }
}