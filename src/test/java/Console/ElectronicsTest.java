package Console;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElectronicsTest {
    @Test
    void constructor_ValidParameters_CreatesElectronicsObject() {
        // Arrange
        String productId = "E001";
        String productName = "Laptop";
        int quantity = 5;
        double price = 1200.0;
        String brand = "Dell";
        int warrantyPeriod = 12;

        // Act
        Electronics electronics = new Electronics(productId, productName, quantity, price, brand, warrantyPeriod);

        // Assert
        assertNotNull(electronics);
        assertEquals(productId, electronics.getProductId());
        assertEquals(productName, electronics.getProductName());
        assertEquals(quantity, electronics.getNoItems());
        assertEquals(price, electronics.getPrice());
        assertEquals(brand, electronics.getBrand());
        assertEquals(warrantyPeriod, electronics.getWarrantyPeriod());
    }

    @Test
    void toString_ReturnsCorrectStringRepresentation() {
        // Arrange
        Electronics electronics = new Electronics("E001", "Laptop", 5, 1200.0, "Dell", 12);

        // Act
        String result = electronics.toString();

        // Assert
        assertEquals("Electronics, E001, Laptop, 5, 1200.0, Dell , 12", result);
    }

    @Test
    void getCategory_ReturnsElectronics() {
        // Arrange
        Electronics electronics = new Electronics("E001", "Laptop", 5, 1200.0, "Dell", 12);

        // Act
        String result = electronics.getCategory();

        // Assert
        assertEquals("Electronics", result);
    }

    @Test
    void getInfo_ReturnsBrandAndWarrantyPeriod() {
        // Arrange
        Electronics electronics = new Electronics("E001", "Laptop", 5, 1200.0, "Dell", 12);

        // Act
        String result = electronics.getInfo();

        // Assert
        assertEquals("Dell12 Months", result);
    }

}