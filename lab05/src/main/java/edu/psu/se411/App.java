package edu.psu.se411;

import edu.psu.se411.exceptions.InvalidAgeException;
import edu.psu.se411.exceptions.InsufficientFundsException;
public class App
{
    public static void main(String[] args)
    {
    	try {
    	    validateAge(20);
    	} catch (InvalidAgeException e) {
    	    System.out.println(e.getMessage());
    	}
    	Wallet wallet = new Wallet(100);

    	try {
    	    wallet.withdraw(150);
    	} catch (InsufficientFundsException e) {
    	    System.out.println(e.getMessage());
    	}
    }

    public static void validateAge(int age) throws InvalidAgeException
    {
        if (age < 18)
        {
            throw new InvalidAgeException("Invalid age. Age must be 18 or higher.");
        }

        System.out.println("Age valid message.");
    }
}
