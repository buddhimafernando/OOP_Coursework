package Console;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClothingTest {
    @Test
    void constructor_ValidParameters_CreatesClothingObject() {
        // Arrange
        String productId = "C001";
        String productName = "Shirt";
        int quantity = 10;
        double price = 29.99;
        String size = "M";
        String color = "Blue";

        // Act
        Clothing clothing = new Clothing(productId, productName, quantity, price, size, color);

        // Assert
        assertNotNull(clothing);
        assertEquals(productId, clothing.getProductId());
        assertEquals(productName, clothing.getProductName());
        assertEquals(quantity, clothing.getNoItems());
        assertEquals(price, clothing.getPrice());
        assertEquals(size, clothing.getSize());
        assertEquals(color, clothing.getColor());
    }

    @Test
    void toString_ReturnsCorrectStringRepresentation() {
        // Arrange
        Clothing clothing = new Clothing("C001", "Shirt", 10, 29.99, "M", "Blue");

        // Act
        String result = clothing.toString();

        // Assert
        assertEquals("Clothing, C001, Shirt, 10, 29.99, M , Blue", result);
    }

    @Test
    void getCategory_ReturnsClothing() {
        // Arrange
        Clothing clothing = new Clothing("C001", "Shirt", 10, 29.99, "M", "Blue");

        // Act
        String result = clothing.getCategory();

        // Assert
        assertEquals("Clothing", result);
    }

    @Test
    void getInfo_ReturnsColorAndSize() {
        // Arrange
        Clothing clothing = new Clothing("C001", "Shirt", 10, 29.99, "M", "Blue");

        // Act
        String result = clothing.getInfo();

        // Assert
        assertEquals("BlueM", result);
    }

}