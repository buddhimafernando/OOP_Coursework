package GUI;

import Console.Clothing;
import Console.Electronics;
import Console.Product;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
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

    public void loadProducts(ArrayList<Product> products) {
        try (Scanner scanner = new Scanner(new File("ProductsList.txt"))) {
            while (scanner.hasNextLine()) {
                String productLine = scanner.nextLine();
                // Split the line into product attributes, trimming any leading/trailing whitespace
                String[] productData = productLine.split(",", -1); // Preserve trailing empty strings
                for (int i = 0; i < productData.length; i++) {
                    productData[i] = productData[i].trim(); // Trim each element
                }

                // Create the appropriate Product object based on the product type
                Product product;
                if (productData.length >= 7) {
                    String productType = productData[0];

                    if (productType.equals("Electronics")) {
                        product = new Electronics(
                                productData[1], // productId
                                productData[2], // productName
                                Integer.parseInt(productData[3]), // noItems
                                Double.parseDouble(productData[4]), // price
                                productData[5], // brand
                                Integer.parseInt(productData[6]) // warrantyPeriod
                        );
                    } else if (productType.equals("Clothing")) {
                        // Handle Clothing size differently
                        String size = productData[5].toUpperCase(); // Convert to uppercase for consistency
                        product = new Clothing(
                                productData[1], // productId
                                productData[2], // productName
                                Integer.parseInt(productData[3]), // noItems
                                Double.parseDouble(productData[4]), // price
                                size, // size
                                productData[6] // color
                        );
                    } else {
                        System.err.println("Invalid product type: " + productType);
                        continue; // Skip to the next iteration if the product type is invalid
                    }

                    products.add(product);
                } else {
                    System.err.println("Invalid format in line: " + productLine);
                }
            }
            System.out.println("Products loaded successfully from ProductsList.txt");
        } catch (IOException e) {
            if (e.getMessage().contains("No such file or directory")) {
                // File doesn't exist, print a custom message
                System.out.println("No saved products found. Starting with a fresh list.");
            } else {
                // Other error, print the original message
                System.err.println("Error loading products: " + e.getMessage());
            }
        }
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

                    ArrayList<Product> products = new ArrayList<>();
                    loadProducts(products);
                    ShoppingCentre shoppingCentre = new ShoppingCentre(products);
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