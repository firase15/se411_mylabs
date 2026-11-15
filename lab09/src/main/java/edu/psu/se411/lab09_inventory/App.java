package edu.psu.se411.lab09_inventory;

import edu.psu.se411.lab09_inventory.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    static Logger logger = LoggerFactory.getLogger(App.class);

    public static void displayInventory(Inventory<?> inventory) {
        for (Object item : inventory.getAllItems()) {
            System.out.println(item);
        }
    }

    public static void main(String[] args) {

        logger.info("Application is starting...");

        try {
            Inventory<Item> inventory = new Inventory<>();

            Book book1 = new Book(1, "Concurrency in Java", "Brian Goetz");
            Book book2 = new Book(2, "Effective Java", "Joshua Bloch");

            ElectronicDevice device1 =
                    new ElectronicDevice(3, "Laptop", "Computer");

            ElectronicDevice device2 =
                    new ElectronicDevice(4, "iPhone", "Phone");

            inventory.addItem(book1);
            inventory.addItem(book2);
            inventory.addItem(device1);
            inventory.addItem(device2);

            System.out.println("All items:");
            displayInventory(inventory);

            System.out.println("Search by name:");
            System.out.println(inventory.findItems(
                    new SearchByName("Concurrency in Java")));

            System.out.println("Search by device name:");
            System.out.println(inventory.findItems(
                    new SearchByName("Laptop")));

            System.out.println("Search by ID:");
            System.out.println(inventory.findItems(
                    new SearchById(2)));

            System.out.println("Search by author:");
            System.out.println(inventory.findItems(
                    new SearchByAuthor("Joshua Bloch")));

            System.out.println("Search by category:");
            System.out.println(inventory.findItems(
                    new SearchByCategory("Phone")));

            System.out.println("Lambda search:");
            System.out.println(inventory.findItems(
                    item -> item.getId() == 3));

            inventory.removeItem(book2);

            System.out.println("Inventory after removal:");
            displayInventory(inventory);

        } catch (Exception e) {
            logger.error("Application error", e);
        }

        logger.info("Application is closing...");
    }
}