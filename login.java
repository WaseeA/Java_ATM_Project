import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class login implements ActionListener {

    private static JLabel fName;
    private static JTextField fNameText;
    private static JLabel lName;
    private static JTextField lNameText;
    private static JLabel PIN;
    private static JTextField PINText;
    private static JButton login;

    public static void main(String[] args){
        start();
    }

    public static void start(){
        // Initialization
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        Font f = new Font("Times New Roman", Font.PLAIN, 24);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        // User Name Section
        fName = new JLabel("First Name");
        fName.setFont(f);
        fName.setBounds(10,20,160,25);
        panel.add(fName);
        fNameText = new JTextField(20);
        fNameText.setFont(f);
        fNameText.setBounds(170, 20, 200, 25);
        panel.add(fNameText);

        // Last Name Section
        lName = new JLabel("Last Name");
        lName.setFont(f);
        lName.setBounds(10,50,160,25);
        panel.add(lName);
        lNameText = new JTextField(20);
        lNameText.setFont(f);
        lNameText.setBounds(170, 50, 200, 25);
        panel.add(lNameText);

        // PIN Section
        PIN = new JLabel("PIN");
        PIN.setFont(f);
        PIN.setBounds(10,80,160,25);
        panel.add(PIN);
        PINText = new JTextField(20);
        PINText.setFont(f);
        PINText.setBounds(170, 80, 200, 25);
        panel.add(PINText);

        // Login Button
        JButton login = new JButton( new AbstractAction("Login") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                try {
                    String firstName = fNameText.getText();
                    String lastName = lNameText.getText();
                    String PIN = PINText.getText();
                    if (bank.findUser(firstName, lastName, PIN)) {
                        bank account = bank.fetchAccount(firstName, lastName, PIN);
                        assert account != null;
                        interaction.interactionScreen(account);
                        frame.dispose();
                    }

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        login.setBounds(10, 115, 200, 25);
        login.addActionListener(new login());
        panel.add(login);

        frame.setVisible(true);

        // Create account button
        JButton createAcc = new JButton( new AbstractAction("Create Account") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                try {
                    String firstName = fNameText.getText();
                    String lastName = lNameText.getText();
                    bank.createAccount(firstName, lastName, Integer.valueOf(PINText.getText()));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        createAcc.setBounds(210, 115, 200, 25);
        createAcc.addActionListener(new login());
        panel.add(createAcc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        // https://youtu.be/HgkBvwgciB4?t=375
//        String firstName = fNameText.getText();
//        String lastName = lNameText.getText();
//        String PIN = PINText.getText();
//        try {
//            if (bank.findUser(firstName, lastName)) {
//                bank currAcc = bank.fetchAccount(firstName, lastName, PIN);
//                assert currAcc != null;
//                interaction.interactionScreen(currAcc);
//            }
//            else{
//                System.out.println("Error, User not found");
//            }
//        } catch (FileNotFoundException fileNotFoundException) {
//            fileNotFoundException.printStackTrace();
//        }
//
//    }
}
