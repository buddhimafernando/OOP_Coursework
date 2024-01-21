package Console;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WestminsterShoppingManagerTest {
    @Test
    void addProduct_ProductAddedSuccessfully() {
        // Arrange
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        Product product = new Electronics("E001", "Laptop", 5, 999.99, "Dell", 12);

        // Act
        shoppingManager.addProduct(product);

        // Assert
        assertEquals(1, shoppingManager.getAllProducts().size());
        assertTrue(shoppingManager.getAllProducts().contains(product));
    }

    @Test
    void addProduct_ProductLimitReached_DisplayMessage() {
        // Arrange
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        for (int i = 0; i < 50; i++) {
            shoppingManager.addProduct(new Electronics("E00" + i, "Product" + i, 1, 10.0 * i, "Brand" + i, 6));
        }

        // Act
        shoppingManager.addProduct(new Electronics("E051", "ExceedProduct", 1, 1000.0, "Brand", 12));

        // Assert
        assertEquals(50, shoppingManager.getAllProducts().size());
        assertFalse(shoppingManager.getAllProducts().stream().anyMatch(p -> p.getProductName().equals("ExceedProduct")));
        // You may want to capture and verify the console output for the message
    }

    @Test
    void removeProduct_ProductExists_ProductRemovedSuccessfully() {
        // Arrange
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        Product product = new Electronics("E001", "Laptop", 5, 999.99, "Dell", 12);
        shoppingManager.addProduct(product);

        // Act
        shoppingManager.removeProduct("E001");

        // Assert
        assertEquals(0, shoppingManager.getAllProducts().size());
    }

    @Test
    void removeProduct_ProductDoesNotExist_DisplayMessage() {
        // Arrange
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        // Act
        shoppingManager.removeProduct("NonExistentID");

        // Assert
        // You may want to capture and verify the console output for the message
    }

    @Test
    void getAllProducts_EmptyList_ReturnEmptyList() {
        // Arrange
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        // Act
        ArrayList<Product> products = shoppingManager.getAllProducts();

        // Assert
        assertTrue(products.isEmpty());
    }
}