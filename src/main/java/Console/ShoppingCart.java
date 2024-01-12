package Console;

import java.util.ArrayList;

public class ShoppingCart {

    private ArrayList<Product> cart;

    public ShoppingCart() {
        this.cart = new ArrayList<>();
    }

    public void addProductToCart(Product product, int quantity) {
        boolean found = false;

        for (Product cartProduct : cart) {
            if (cartProduct.equals(product)) {
                cartProduct.setNoItems(cartProduct.getNoItems() + quantity);
                found = true;
                break;
            }
        }

        if (!found) {
            // If the product is not already in the cart, add it
            product.setNoItems(quantity);
            cart.add(product);
        }

        System.out.println("Product added to the shopping cart.");
        System.out.println(product.getProductName() + " added to the cart.");
    }

    public void removeProductFromCart(Product product) {
        if (cart.remove(product)) {
            System.out.println(product.getProductName() + " removed from the cart.");
        } else {
            System.out.println("Product not found in the cart.");
        }
    }

    public int getQuantity(Product product) {
        for (Product cartProduct : cart) {
            if (cartProduct.equals(product)) {
                return cartProduct.getNoItems();
            }
        }
        return 0;
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

