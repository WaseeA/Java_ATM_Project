/* This file is where functions that modify user data reside.
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
import java.util.Scanner;

public class transactions extends bank {

    public static void transaction(Integer UID, double amount, String type) {
        /* This function deposits/withdraws money from a user's account
        *
        * @param type: The type of interaction the user wishes to perform.
        * @param amount: The amount a user wishes to withdraw/deposit.
        *
        Preconditions
        * - type in {"deposit", "withdraw"}
        */
        // Initialize a temporary file
        String tempFile = "tempFile.csv";
        String filepath = "data/users.csv";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);

        // Categories in the csv file
        String curr_fName = "";
        String curr_lName = "";
        String curr_PIN = "";
        String curr_UID = "";
        String curr_balance = "";

        try {
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            Scanner sc = new Scanner(new File(filepath));
            sc.useDelimiter("[,\n]");

            while (sc.hasNext()) {
                curr_fName = sc.next();
                curr_lName = sc.next();
                curr_PIN = sc.next();
                curr_UID = sc.next();
                curr_balance = sc.next();

                if (Integer.parseInt(curr_UID) == UID) {
                    if (type.equals("withdraw")) {
                        double newBal = Double.parseDouble(curr_balance) - amount;
                        pw.println(curr_fName + ',' + curr_lName + ',' + curr_PIN + ','
                                + curr_UID + ',' + newBal);
                    } else {
                        double newBal = Double.parseDouble(curr_balance) + amount;
                        pw.println(curr_fName + ',' + curr_lName + ',' + curr_PIN + ','
                                + curr_UID + ',' + newBal);
                    }
                }
                else {
                    pw.println(curr_fName + ',' + curr_lName + ',' + curr_PIN + ',' +
                            curr_UID + ',' + curr_balance); }
            }

            // Delete the old file
            sc.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            // Rename the old file to the new file
            File dump = new File(filepath);
            newFile.renameTo(dump);
        } catch (IOException e) {
            e.printStackTrace(); }

    }
}
