package Console;

import java.util.ArrayList;

public interface ShoppingManager {

    public void addProduct(Product product);

    public void removeProduct(String productId);

    public ArrayList<Product> getAllProducts();

    public void saveToFile();

    public void loadFromFile();
}
