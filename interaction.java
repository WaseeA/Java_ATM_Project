/* This is the main file where users interact with the banking interface
*
* Copyright and Usage Information
* This file is provided solely for the personal and private use
* for non profit purposes. All forms of distribution of this code
* are expressly prohibited. For more information please contact me
* at my GitHub @WaseeA.
*
* This file is Copyright (c) Wasee Alam.
* */

import com.opencsv.exceptions.CsvException;
import java.io.*;
import java.util.Scanner;

public class interaction {
    public static void main(String[] args) throws IOException, CsvException {
        /* Main Function
        * This is where the user interacts with the program
        */
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your first and last name separated by a space");
        String[] fullName = sc.nextLine().split(" ");
        String firstName = fullName[0];
        String lastName = fullName[1];

        if (findUser(firstName, lastName)){
            Scanner sc2 = new Scanner(System.in);
            System.out.println("Enter your PIN");
            String PIN = sc2.nextLine();

            // Access Bank Account
            bank currAcc = bank.fetchAccount(firstName, lastName, PIN);
            bank testAcc = bank.fetchAccount("Killer", "Bee", "8888");

            // Save the Data
            friends friend = new friends();
            friend.addPerson(currAcc);
            friend.addFriend(currAcc, testAcc);
            friend.saveData();
            friend.loadData();
        }
        else{
            // Create Bank Account
            System.out.println("Create your PIN Number");
            int PIN = sc.nextInt();
            bank tempAccount = new bank();
            bank createdAcc = tempAccount.createAccount(firstName, lastName, PIN);

            // insert account into csv file
            insertUser(createdAcc);
        }
    }

    public static boolean findUser(String firstName, String lastName) throws FileNotFoundException {
        /* Checks if a user was created and stored in users.csv */
        Scanner sc = new Scanner(new File("data/users.csv"));
        while (sc.hasNext()) {
            String data = sc.next();
            String[] values = data.split(",");
            // If we find the first, last name in the csv return true
            if (values[0].equals(firstName) && values[1].equals(lastName)) {
                return true;
            }
        }
        return false;
    }

    public static void insertUser(bank account) throws IOException {
        /* Function that creates a bank account and logs it to users.csv */
        try {
            FileWriter csv = new FileWriter("data/users.csv", true);
            BufferedWriter csv2 = new BufferedWriter(csv);
            PrintWriter pw = new PrintWriter(csv2);

            String PIN = String.valueOf(account.PIN);
            String UID = String.valueOf(account.UID);
            String bal = String.valueOf(account.bal);

            pw.println(account.firstName +','+ account.lastName +','+ PIN +','+ UID +','+ bal);
            pw.flush();
            pw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
