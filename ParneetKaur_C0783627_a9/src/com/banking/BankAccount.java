package com.banking;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BankAccount {

    private String accountNumber;

    public BankAccount(){
        this.accountNumber = "";
    }

    /**
     *
     * @param accountNumber existing acountNumber, if the use has account, overloaded constructor will be used
     */
    public BankAccount(String accountNumber){
        this();
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber(){
        return this.accountNumber;
    }

    /**
     *
     * @param name name of the user opening the account
     * @return BankAcount object having new random account number
     * @throws IOException if the file not found or unable to write to the file...IOException is thrown
     */
    public static BankAccount open(String name) throws IOException {
        String randomId = (int) (Math.random() * 10000)+"";
        while(true){
            if(!accountExists(randomId)){
                File file = new File(randomId+".txt");
                file.createNewFile();
                FileWriter writer = new FileWriter(file);
                writer.write(name+"-"+0);
                writer.close();

                return new BankAccount(randomId);
            }
            randomId = (int) (Math.random() * 10000)+"";
        }
    }

    /**
     *
     * @return String array of length 2 or 0...2 for valid account and 0 if acount does no exists
     * @throws FileNotFoundException if the account does not exists
     */
    public String[] getDetails() throws FileNotFoundException {
        String[] details = {};
        if(accountExists(this.accountNumber)){
            File file = new File(this.accountNumber+".txt");
            Scanner reader = new Scanner(file);
            String data = "";
            while (reader.hasNextLine()) {
                data = reader.nextLine().trim();
                if(!data.equals(""))
                    break;
            }
            details = data.split("-");
        }

        return details;
    }

    /**
     *
     * @param transaction transaction has the amount to withderaw or deposit
     * @throws IOException
     */
    public void processTransaction(Transaction transaction) throws IOException {
        String[] details = getDetails();
        String name = details[0];
        double balance = Double.parseDouble(details[1]);

        double updatedBalance = balance + transaction.getAmount();

        if(updatedBalance < 0){
            System.out.println("Insufficient Balance!");
            return ;
        }

        File file = new File(this.accountNumber+".txt");
        FileWriter writer = new FileWriter(file, false);
        writer.write(name+"-"+updatedBalance);
        writer.close();
    }

    /**
     *
     * @param accountNumber name of the file without .txt extension
     * @return true if the account exists, false otherwise
     */
    public static boolean accountExists(String accountNumber){
        return new File(accountNumber + ".txt").exists();
    }

}

class Transaction{
    private double amount;

    public Transaction(double amount){
        this.amount = amount;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }
    public double getAmount(){
        return this.amount;
    }
}
