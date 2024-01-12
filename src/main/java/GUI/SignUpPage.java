package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SignUpPage implements ActionListener {

    JFrame jFrame = new JFrame("SignUp Page");
    JLabel signUpLabel = new JLabel();
    JLabel informationLabel = new JLabel("Set up your username and password");
    JLabel enterUsernameLabel = new JLabel("Username : ");
    JLabel enterPasswordLabel = new JLabel("Password : ");
    JLabel reEnterPasswordLabel = new JLabel("Re-enter Password : ");
    JTextField enterUsernameField = new JTextField();
    JPasswordField enterPasswordField = new JPasswordField();
    JPasswordField reEnterPasswordField = new JPasswordField();
    JButton confirmButton = new JButton("Confirm");
    JLabel successMessage = new JLabel();
    IdAndPassword userInformation;


    SignUpPage() {

        userInformation = new IdAndPassword();

        signUpLabel.setBounds(50, 20, 100, 25);
        signUpLabel.setFont(new Font(null, Font.BOLD, 15));
        signUpLabel.setText("SignUp");

        informationLabel.setBounds(50, 60, 250, 25);

        enterUsernameLabel.setBounds(50, 110, 130, 25);
        enterPasswordLabel.setBounds(50, 160, 130, 25);
        reEnterPasswordLabel.setBounds(50, 210, 130, 25);

        enterUsernameField.setBounds(180, 110, 170, 25);
        enterPasswordField.setBounds(180, 160, 170, 25);
        reEnterPasswordField.setBounds(180, 210, 170, 25);

        confirmButton.setBounds(245, 285, 100, 25);
        confirmButton.setFocusable(false);
        confirmButton.addActionListener(this);

        successMessage.setBounds(50, 300, 100, 25);

        jFrame.add(signUpLabel);
        jFrame.add(informationLabel);
        jFrame.add(enterUsernameLabel);
        jFrame.add(enterPasswordLabel);
        jFrame.add(reEnterPasswordLabel);
        jFrame.add(enterUsernameField);
        jFrame.add(enterPasswordField);
        jFrame.add(reEnterPasswordField);
        jFrame.add(confirmButton);
        jFrame.add(successMessage);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(420, 420);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }
}