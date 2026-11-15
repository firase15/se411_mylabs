package edu.psu.se411.lab12_threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.se411.lab12_threads.model.CryptoAccount;
import edu.psu.se411.lab12_threads.model.Transaction;

public class AppCached {

    static Logger logger = LoggerFactory.getLogger(AppCached.class);

    public static Integer nbAccounts = 10;
    public static Double initialBalance = 10000.0;
    public static Integer nbTransactions = 1000000;

    public static void main(String[] args) {

        logger.info("Application is starting...");

        CryptoAccount[] accounts = new CryptoAccount[nbAccounts];

        for (int i = 0; i < nbAccounts; i++) {
            accounts[i] = new CryptoAccount("acc_" + i, initialBalance);
        }

        System.out.printf("Initial money volume: %f%n",
                sumTotalMoneyInAccounts(accounts));

        ExecutorService executor = Executors.newCachedThreadPool();

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < nbTransactions; i++) {

            int sender_index = (int) (Math.random() * nbAccounts);
            int receiver_index = (int) (Math.random() * nbAccounts);

            if (sender_index == receiver_index) {
                sender_index = (sender_index + 1) % nbAccounts;
            }

            CryptoAccount sender = accounts[sender_index];
            CryptoAccount receiver = accounts[receiver_index];

            Double amount = Math.random() * 100;

            Transaction t = new Transaction("id_", sender, receiver, amount);

            executor.execute(t);
        }

        executor.shutdown();

        while (!executor.isTerminated()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Final money volume: %f%n",
                sumTotalMoneyInAccounts(accounts));

        long endTime = System.currentTimeMillis();

        System.out.println("Total time using cached thread pool: "
                + (endTime - startTime) + " milliseconds");

        logger.info("Application is closing...");
    }

    private static Double sumTotalMoneyInAccounts(CryptoAccount[] accounts) {
        Double total = 0.0;

        for (CryptoAccount c : accounts) {
            total += c.getBalance();
        }

        return total;
    }
}