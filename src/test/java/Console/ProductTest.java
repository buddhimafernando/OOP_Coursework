package Console;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    @Test
    void testProductConstructorAndGetterMethods() {
        Product product = new ConcreteProduct("P001", "Test Product", 10, 25.0);

        assertEquals("P001", product.getProductId());
        assertEquals("Test Product", product.getProductName());
        assertEquals(10, product.getNoItems());
        assertEquals(25.0, product.getPrice(), 0.001);
    }

    // Test setters
    @Test
    void testSetterMethods() {
        Product product = new ConcreteProduct("P002", "Test Product", 15, 30.0);

        product.setProductId("P003");
        product.setProductName("Updated Product");
        product.setNoItems(20);
        product.setPrice(35);

        assertEquals("P003", product.getProductId());
        assertEquals("Updated Product", product.getProductName());
        assertEquals(20, product.getNoItems());
        assertEquals(35.0, product.getPrice(), 0.001);
    }

    // Test decreaseQuantity method
    @Test
    void testDecreaseQuantityMethod() {
        Product product = new ConcreteProduct("P004", "Test Product", 25, 40.0);

        product.decreaseQuantity(5);

        assertEquals(20, product.getNoItems());
    }


    // Test getCategory method
    @Test
    void testGetCategoryMethod() {
        Product product = new ConcreteProduct("P006", "Test Product", 40, 60.0);

        assertEquals("Uncategorized", product.getCategory());
    }

    // Test getInfo method
    @Test
    void testGetInfoMethod() {
        Product product = new ConcreteProduct("P007", "Test Product", 50, 70.0);

        assertEquals("", product.getInfo());
    }

    // Concrete implementation for testing
    private static class ConcreteProduct extends Product {
        public ConcreteProduct(String productId, String productName, int noItems, double price) {
            super(productId, productName, noItems, price);
        }

        @Override
        public String toString() {
            return String.format("Product ID: %s, Name: %s, Quantity: %d, Price: £%.2f", productId, productName, quantity, price);
        }

        @Override
        public String getCategory() {
            return "Uncategorized";
        }

        @Override
        public String getInfo() {
            return "";
        }
    }
}

