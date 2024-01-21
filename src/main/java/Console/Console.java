package Console;

import GUI.Main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {

    private static void selectFromMenu() {
        System.out.println("\n_______________Please select an option_____________\n");
        System.out.println("1) Add new product");
        System.out.println("2) Delete existing product");
        System.out.println("3) Print the list of products");
        System.out.println("4) Save to file");
        System.out.println("5) Open customer GUI");
        System.out.println("0) Quit");
    }

    public static boolean validateChoice(int choice){
        return choice <= -1 || choice >= 7;
    }

    public static void main (String [] args){

        Scanner input = new Scanner(System.in);
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        // load the content if there is any text file with system data
        shoppingManager.loadFromFile();

        System.out.println("\n                    Welcome to                      ");
        System.out.println("        ____ Westminster Shopping Centre! ____        ");
        System.out.println("______________________________________________________");



        // Display menu
        selectFromMenu();

        boolean quit = false;

        while (!quit) {
            System.out.print("\nEnter Your Choice Here :  ");
            int choice;
            try {
                choice = input.nextInt();
                input.nextLine(); // consume the leftover newline character

                if (validateChoice(choice)) {
                    System.out.println("Please select an option between 0-5 >>>");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please recheck and enter a valid option...");
                input.nextLine(); // consume the leftover newline character
                continue;
            }

            switch (choice) {
                case 1:
                    // Add a new product to the system
                    while (true) {
                        try {
                            System.out.print("\nEnter Product ID: ");
                            String newProductId = input.next();
                            System.out.print("Enter Product Name: ");
                            String newProductName = input.next();
                            System.out.print("Enter Quantity: ");
                            int newQuantity = input.nextInt();
                            System.out.print("Enter Price: ");
                            int newPrice = input.nextInt();

                            System.out.println("Select the product type:");
                            System.out.println("1) Clothing       2) Electronics");

                            int productTypeChoice = input.nextInt();
                            input.nextLine(); // consume the leftover newline character

                            switch (productTypeChoice) {
                                case 1:
                                    // Adding a Clothing product
                                    System.out.print("Enter Size: ");
                                    String newSize = input.next();
                                    input.nextLine(); // consume the leftover newline character
                                    System.out.print("Enter Color: ");
                                    String newColor = input.next();

                                    Clothing clothing = new Clothing(newProductId,newProductName,newQuantity,newPrice,newSize,newColor);
                                    shoppingManager.addProduct(clothing);
                                    break;

                                case 2:
                                    // Adding an Electronics product
                                    System.out.print("Enter Brand: ");
                                    String newBrand = input.next();
                                    System.out.print("Enter Warranty Period: ");
                                    int newWarrantyPeriod = input.nextInt();

                                    Electronics electronics = new Electronics(newProductId,newProductName,newQuantity,newPrice,newBrand,newWarrantyPeriod);
                                    shoppingManager.addProduct(electronics);
                                    System.out.println("Product has been successfully removed...");
                                    break;

                                default:
                                    System.out.println("Invalid product type choice. Try again...");
                                    continue; // Continue to the next iteration of the loop
                            }

                            break; // Exit the loop if input is successful
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter valid input.");
                            input.nextLine();
                        }
                    }
                    break;

                case 2:
                    // Remove a product from the system
                    System.out.print("ProductID : ");
                    String productId = input.next();
                    shoppingManager.removeProduct(productId);
                    break;

                case 3:
                    // Print all products in the system alphabetically by productId
                    ArrayList<Product> listOfProducts = shoppingManager.getAllProducts();

                    // Bubble sort to alphabetically order the products by productId
                    for (int i = 0; i < listOfProducts.size() - 1; i++) {
                        for (int j = 0; j < listOfProducts.size() - i - 1; j++) {
                            String productId1 = listOfProducts.get(j).getProductId();
                            String productId2 = listOfProducts.get(j + 1).getProductId();
                            if (productId1.compareToIgnoreCase(productId2) > 0) {
                                // Swap products if they are in the wrong order
                                Product temp = listOfProducts.get(j);
                                listOfProducts.set(j, listOfProducts.get(j + 1));
                                listOfProducts.set(j + 1, temp);
                            }
                        }
                    }

                    System.out.println("Number of products: " + listOfProducts.size());  // Debug print

                    for (Product product : listOfProducts) {
                        System.out.println(product.toString());
                    }

                    System.out.println("");  // Debug print
                    break;



                case 4:
                    // Save products to a file
                    shoppingManager.saveToFile();
                    break;

                case 5:
                    // Open GUI
                    Main main = new Main();
                    main.main();
                    break;

                case 0:
                    //quit the loop and the program
                    quit = true;
                    break;

                default:
                    System.out.println("Not a valid response. Try again...");
            }

            selectFromMenu();
        }
    }

}




