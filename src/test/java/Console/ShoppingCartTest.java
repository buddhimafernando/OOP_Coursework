package Console;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {
    @Test
    void addProductToCart_ValidProductAndQuantity_ProductAddedToCart() {
        // Arrange
        ShoppingCart cart = new ShoppingCart();
        Product product = new Electronics("E001", "Laptop", 5, 1200.0, "Dell", 12);
        int quantity = 2;

        // Act
        cart.addProductToCart(product, quantity);

        // Assert
        assertEquals(quantity, cart.getQuantity(product));
    }

    @Test
    void removeProductFromCart_ProductInCart_ProductRemoved() {
        // Arrange
        ShoppingCart cart = new ShoppingCart();
        Product product = new Electronics("E001", "Laptop", 5, 1200.0, "Dell", 12);

        // Act
        cart.addProductToCart(product, 2);
        cart.removeProductFromCart(product);

        // Assert
        assertEquals(0, cart.getQuantity(product));
    }

    @Test
    void viewCart_CartIsEmpty_DisplayEmptyCartMessage() {
        // Arrange
        ShoppingCart cart = new ShoppingCart();

        // Act
        cart.viewCart();

        // Assert
        // The expected output should be captured manually or checked in the console.
        // Example: assertEquals("Your cart is empty.", capturedOutput);
    }

    @Test
    void viewCart_CartIsNotEmpty_DisplayProductsAndTotalCost() {
        // Arrange
        ShoppingCart cart = new ShoppingCart();
        Product product1 = new Electronics("E001", "Laptop", 2, 1200.0, "Dell", 12);
        Product product2 = new Clothing("C001", "Shirt", 3, 29.99, "M", "Blue");

        // Act
        cart.addProductToCart(product1, 2);
        cart.addProductToCart(product2, 3);

        // Assert
        // The expected output should be captured manually or checked in the console.
        // Example: assertEquals("Products in your cart: ...", capturedOutput);
    }

    @Test
    void clearCart_CartIsNotEmpty_CartIsCleared() {
        // Arrange
        ShoppingCart cart = new ShoppingCart();
        Product product = new Electronics("E001", "Laptop", 2, 1200.0, "Dell", 12);

        // Act
        cart.addProductToCart(product, 2);
        cart.clearCart();

        // Assert
        assertTrue(cart.getCart().isEmpty());
    }

}