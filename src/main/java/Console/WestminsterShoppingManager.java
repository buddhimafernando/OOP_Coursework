package Console;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class WestminsterShoppingManager {

    ArrayList <Product> products;
    public WestminsterShoppingManager() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        if(products.size()<50){
            products.add(product);
        }else{
            System.out.println("Product limit haas reached...");
        }
    }

    public void removeProduct(String productId) {
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()){
            Product product = iterator.next();
            if (product.getProductId().equals(productId)){
                iterator.remove();
                return;
            }
        } System.out.println("Product not found.");
    }

    public ArrayList<Product> getAllProducts() {
        return new ArrayList<>(this.products);
    }

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

                    if (productType.equals("Electronics")) {
                        addProduct(new Electronics(
                                productData[1], // productId
                                productData[2], // productName
                                Integer.parseInt(productData[3]), // noItems
                                Double.parseDouble(productData[4]), // price
                                productData[5], // brand
                                Integer.parseInt(productData[6]) // warrantyPeriod
                        ));
                    } else if (productType.equals("Clothing")) {
                        // Handle Clothing size differently
                        String size = productData[5].toUpperCase(); // Convert to uppercase for consistency
                        addProduct(new Clothing(
                                productData[1], // productId
                                productData[2], // productName
                                Integer.parseInt(productData[3]), // noItems
                                Double.parseDouble(productData[4]), // price
                                size, // size
                                productData[6] // color
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

