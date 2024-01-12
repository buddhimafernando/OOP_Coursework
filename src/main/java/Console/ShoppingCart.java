package Console;

import java.util.ArrayList;

public class ShoppingCart {

    private ArrayList<Product> cart;

    public ShoppingCart() {
        this.cart = new ArrayList<>();
    }

    public void addProductToCart(Product product) {
        cart.add(product);
        System.out.println(product.getProductName() + " added to the cart.");
    }

    public void removeProductFromCart(Product product) {
        if (cart.remove(product)) {
            System.out.println(product.getProductName() + " removed from the cart.");
        } else {
            System.out.println("Product not found in the cart.");
        }
    }

    public double calculateTotalCost() {
        double totalCost = 0.0;
        for (Product product : cart) {
            totalCost += product.getPrice();
        }
        return totalCost;
    }

    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Products in your cart:");
            for (Product product : cart) {
                System.out.println(product.getProductName() + " - $" + product.getPrice());
            }
            System.out.println("Total Cost: $" + calculateTotalCost());
        }
    }

    public ArrayList<Product> getCart() {
        return new ArrayList<>(cart);
    }

    public void clearCart() {
        cart.clear();
    }
}

