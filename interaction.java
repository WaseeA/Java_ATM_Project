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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class interaction {

    public static void main(String[] args) throws IOException, CsvException {
        /* Main Function
        * This is where the user interacts with the program
        */
        bank acc = bank.fetchAccount("Boo", "Car", "8888");
        bank fren = bank.fetchAccount("Hi", "Bye", "9999");
//        friends newfren = new friends();
//        newfren.addFriend(acc, fren);
        ArrayList<Integer> map = friends.seeFriends(acc);
        System.out.println(map);
        for (int i = 0; i < map.size(); i++) {
            System.out.println(map.get(i));
        }
//        friends.saveData();
    }

    public static void interactionScreen(bank account) {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        Font f = new Font("Times New Roman", Font.PLAIN, 20);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        // entry fields
        JLabel amount = new JLabel("Amount");
        amount.setFont(f);
        amount.setBounds(180,10,160,20);
        panel.add(amount);

        JTextField amountText = new JTextField(20);
        amountText.setFont(f);
        amountText.setBounds(180, 30, 200, 25);
        panel.add(amountText);

        JLabel recipient = new JLabel("Recipient UID");
        recipient.setFont(f);
        recipient.setBounds(180, 65, 190, 20);
        panel.add(recipient);

        JTextField receiverText = new JTextField();
        receiverText.setFont(f);
        receiverText.setBounds(180, 95, 200, 25);
        panel.add(receiverText);

        // eTransfer Section
        JButton eTransferButton = new JButton( new AbstractAction("eTransfer") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                try {
//                    bank src = bank.findUID(String.valueOf(account.UID));
                    String UID = receiverText.getText();
                    bank reciever = bank.fetchAccountUID(UID);
                    double numAmount = Double.parseDouble(amountText.getText());
                    friends.eTransfer(account, reciever, numAmount);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
        eTransferButton.setBounds(10, 20, 160, 50);
        panel.add(eTransferButton);

        // deposit section
        JButton deposit = new JButton( new AbstractAction("deposit") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                transactions.transaction(account.UID, Double.valueOf(amountText.getText()), "deposit");
            }
        });
        deposit.setBounds(10, 80, 160, 50);
        panel.add(deposit);

        // withdraw section
        JButton withdraw = new JButton( new AbstractAction("withdraw") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                transactions.transaction(account.UID, Double.valueOf(amountText.getText()), "withdraw");
            }
        });
        withdraw.setBounds(10, 140, 160, 50);
        panel.add(withdraw);

        // Information Section
        JLabel balance = new JLabel("Balance:" + String.valueOf(account.bal));
        balance.setFont(f);
        balance.setBounds(180,125,160,20);
        panel.add(balance);

        JButton friendsList = new JButton(new AbstractAction("Friends List") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    friendsScreen(account);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        friendsList.setFont(f);
        friendsList.setBounds(10,200,160,40);
        panel.add(friendsList);

        JLabel friendUID = new JLabel("Friend UID");
        friendUID.setFont(f);
        friendUID.setBounds(180, 250, 160, 20);
        panel.add(friendUID);

        JTextField UIDText = new JTextField();
        UIDText.setFont(f);
        UIDText.setBounds(180, 270, 200, 25);
        panel.add(UIDText);

        JButton add = new JButton(new AbstractAction("Add Friend") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String destUID = UIDText.getText();
                try {
                    bank dest = bank.fetchAccountUID(destUID);
                    friends fren = new friends();
                    fren.addFriend(account, dest);
                    friends.saveData();
                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
        add.setFont(f);
        add.setBounds(10, 250, 160, 40);
        panel.add(add);

        frame.setVisible(true);
    }

    public static void friendsScreen(bank account) throws IOException {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        Font f = new Font("Times New Roman", Font.PLAIN, 24);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panel);
        int curr_y = 10;

        panel.setLayout(null);
        ArrayList<Integer> lst= new ArrayList<Integer>();
        lst = friends.seeFriends(account);
//        System.out.println(lst);
//        friends map = new friends();
//        map.loadData();
//        List<Integer> friendArray = map.map.get(account);
        for (int i = 0; i < lst.size(); i++) {
            String UID = String.valueOf(lst.get(i));
            JLabel label = new JLabel(UID);
            label.setFont(f);
            label.setBounds(180, curr_y, 200, 25);
            curr_y += 30;
            panel.add(label);
        }
        frame.setVisible(true);
    }

}
