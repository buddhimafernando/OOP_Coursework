package Console;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class WestminsterShoppingManager {

    // ArrayList to store the products
    ArrayList<Product> products;

    // Constructor to initialize the ArrayList
    public WestminsterShoppingManager() {
        products = new ArrayList<>();
    }

    // Method to add a product to the list
    public void addProduct(Product product) {
        if (products.size() < 50) {
            products.add(product);
        } else {
            System.out.println("Product limit has reached...");
        }
    }

    // Method to remove a product from the list based on productId
    public void removeProduct(String productId) {
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductId().equals(productId)) {
                iterator.remove();
                return;
            }
        }
        System.out.println("Product not found.");
    }

    // Method to get a copy of all products in the list
    public ArrayList<Product> getAllProducts() {
        return new ArrayList<>(this.products);
    }

    // Method to save products to a file (ProductsList.txt)
    public void saveToFile() {
        try (FileWriter writer = new FileWriter("ProductsList.txt")) {
            for (Product product : products) {
                writer.write(product.toString() + "\n");  // Write each product's details to the file
            }
            System.out.println("Products saved successfully to ProductsList.txt");
        } catch (IOException e) {
            System.err.println("Error saving products: " + e.getMessage());
        }
    }

    // Method to load products from a file (ProductsList.txt)
    public void loadFromFile() {
        try (Scanner scanner = new Scanner(new File("ProductsList.txt"))) {
            while (scanner.hasNextLine()) {
                String productLine = scanner.nextLine();
                String[] productData = productLine.split(",", -1); // Preserve trailing empty strings
                for (int i = 0; i < productData.length; i++) {
                    productData[i] = productData[i].trim();
                }

                if (productData.length >= 7) {
                    String productType = productData[0];

                    // Handling different product types (Electronics, Clothing)
                    if (productType.equals("Electronics")) {
                        addProduct(new Electronics(
                                productData[1], productData[2], Integer.parseInt(productData[3]),
                                Double.parseDouble(productData[4]), productData[5], Integer.parseInt(productData[6])
                        ));
                    } else if (productType.equals("Clothing")) {
                        String size = productData[5].toUpperCase(); // Convert to uppercase for consistency
                        addProduct(new Clothing(
                                productData[1], productData[2], Integer.parseInt(productData[3]),
                                Double.parseDouble(productData[4]), size, productData[6]
                        ));
                    } else {
                        System.err.println("Invalid product type: " + productType);
                    }
                } else {
                    System.err.println("Invalid format in line: " + productLine);
                }
            }
            System.out.println("Products loaded successfully from ProductsList.txt");
        } catch (IOException e) {
            if (e.getMessage().contains("No such file or directory")) {
                System.out.println("No saved products found. Starting with a fresh list.");
            } else {
                System.err.println("Error loading products: " + e.getMessage());
            }
        }
    }
}
