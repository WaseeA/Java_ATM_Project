import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gui implements ActionListener {

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
        login = new JButton("Login");
        login.setBounds(10, 115, 300, 25);
        login.addActionListener(new gui());
        panel.add(login);

        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Received");
    }
}
