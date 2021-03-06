/* This is the file which performs internal banking processes
 *
 * Copyright and Usage Information
 * This file is provided solely for the personal and private use
 * for non profit purposes. All forms of distribution of this code
 * are expressly prohibited. For more information please contact me
 * at my GitHub @WaseeA.
 *
 * This file is Copyright (c) Wasee Alam.
 * */

import java.io.*;
import java.util.*;

public class bank{
    /* This class represents the bank account of individuals
    * @param firstName: First name of user.
    * @param lastName: Last name of user.
    * @param PIN: User's PIN number. Used as an "encryption" key.
    * @param UID: A unique identifier for each user.
    * @param bal: A user's balance.
    * */
    // Variable attributes
    protected String firstName;
    protected String lastName;
    private Integer PIN;
    protected Integer UID;
    protected double bal;

    public static void createAccount(String firstName, String lastName, Integer PIN) throws IOException {
        /* This function creates an account for the user. */
        bank account = new bank();
        account.firstName = firstName;
        account.lastName = lastName;
        account.PIN = PIN;
        try {
            account.UID = generate_uID();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        account.bal = 0.0;

        insertUser(account);
    }

    public static bank fetchAccount(String firstName, String lastName, String PIN) throws FileNotFoundException {
        /* This function parses the csv and returns the corresponding account */
        Scanner sc = new Scanner (new File("data/users.csv"));
        while(sc.hasNext()){
            String[] values = sc.next().split(",");
            if (values[0].equals(firstName) && values[1].equals(lastName) && values[2].equals(PIN)) {
                bank account = new bank();
                account.firstName = firstName;
                account.lastName = lastName;
                account.PIN = Integer.valueOf(PIN);
                account.UID = Integer.valueOf(values[3]);
                account.bal = Double.parseDouble(values[4]);
                return account;
            }
        }
        System.out.println("Name/PIN Combination not found.");
        return null;
    }

    private static int generate_uID() throws IOException {
        /* This function generates a unique ID for each user */
        // TODO: Make a smart way of saving the list, then only checking the added lines
        BufferedReader br = new BufferedReader(new FileReader("data/users.csv"));
        LinkedList<Integer> IDS = new LinkedList<Integer>();
        String line;
        br.readLine();
        // read all of the used ids
        while ((line = br.readLine()) != null){
            // parse the data
            String[] tempItems = line.split(",");
            int ID = Integer.parseInt(tempItems[3]);
            IDS.add(ID);
        }
        // create a list of possible ids
        LinkedList<Integer>array = new LinkedList<Integer>();
        for (int i =0; i<100; i++){
            array.add(i);
        }
        // choose an id that's not in IDS
        int[] newArray = new int[array.size() - IDS.size()];
        int j = 0;
        for (int i = array.size() - IDS.size() - 1; i >= 0; i--) {
            // If we don't find the number in IDS (used), add it to new array
            if (!IDS.contains(array.get(i))) {
                newArray[j] = array.get(j); }
            j++;
        }
        // Generate a new random number from the limit
        int rnd = new Random().nextInt(newArray.length);
        return newArray[rnd];
    }

    public static boolean findUser(String firstName, String lastName, String PIN) throws FileNotFoundException {
        /* Checks if a user was created and stored in users.csv */
        Scanner sc = new Scanner(new File("data/users.csv"));
        while (sc.hasNext()) {
            String data = sc.next();
            String[] values = data.split(",");
            // If we find the first, last name in the csv return true
            if (values[0].equals(firstName) && values[1].equals(lastName) && values[2].equals(PIN)) {
                return true;
            }
        }
        return false;
    }

    public static bank fetchAccountUID(String UID) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("data/users.csv"));
        while (sc.hasNext()) {
            String data = sc.next();
            String[] values = data.split(",");
            // If we find the first, last name in the csv return true
            if (values[3].equals(UID)){
                bank account = new bank();
                System.out.println(values[0] + " " + values[1]);
                account.firstName = values[0];
                account.lastName = values[1];
                account.PIN = Integer.valueOf(values[2]);
                account.UID = Integer.valueOf(values[3]);
                account.bal = Double.parseDouble(values[4]);

                return account;
            }
        }
        return null;
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

    private static int[] removeItem(int[] array, Integer item) {
        /* This function removes an item from an array.
        Preconditions:
        * - array.length > 0;
        */
        int[] newArray = new int[array.length - 1];
        int j = 0;
        for (int value: array){
            if (value != item) {
                newArray[j] = array[j];
                j++; } }
        return newArray;
    }

}
