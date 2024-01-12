package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Login implements ActionListener {

    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userIDLabel = new JLabel("Username:");
    JLabel userPasswordLabel = new JLabel("Password:");
    JLabel messageLabel = new JLabel();
    HashMap<String, String> logininfo = new HashMap<String, String>();

    Login(HashMap<String, String> loginInfoOriginal) {

        logininfo = loginInfoOriginal;

        userIDLabel.setBounds(60,80,75,25);
        userPasswordLabel.setBounds(60,130,75,25);

        messageLabel.setBounds(110,320,250,25);
        messageLabel.setFont(new Font(null,Font.BOLD,18));

        //signUpLabel.setBounds(60,260,250,35);

        userIDField.setBounds(135,80,200,25);
        userPasswordField.setBounds(135,130,200,25);

        loginButton.setBounds(135,180,95,25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        resetButton.setBounds(240,180,95,25);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

//        signUpButton.setBounds(235,265,100,25);
//        signUpButton.setFocusable(false);
//        signUpButton.addActionListener(this);

        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(messageLabel);
        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(loginButton);
        frame.add(resetButton);
//        frame.add(signUpButton);
//        frame.add(signUpLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == resetButton) {
            userIDField.setText("");
            userPasswordField.setText("");
        }

        if (e.getSource() == loginButton) {

            String userID = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());

            if (logininfo.containsKey(userID)) {
                if (logininfo.get(userID).equals(password)) {
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Login successful");
                    frame.dispose();
                    ShoppingCentre welcomePage = new ShoppingCentre(new ArrayList<>());
                } else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Recheck password");
                }

            } else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Username not found");
            }
        }
    }
}