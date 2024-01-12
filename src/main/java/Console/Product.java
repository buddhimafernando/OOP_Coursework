package Console;

public abstract class Product {
    protected String productId;
    protected String productName;
    protected int quantity;
    protected double price;

    // declaring the constructor
    public Product(String productId, String productName, int noItems, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = noItems;
        this.price = price;

    }

    // declaring getters for each variable
    public String getProductId() {
        return productId;
    }
    public String getProductName() {
        return productName;
    }
    public int getNoItems() {
        return quantity;
    }
    public double getPrice() {
        return price;
    }

    //declaring setters for each variable
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setNoItems(int noItems) {
        this.quantity = noItems;
    }
    public void setPrice(int price){
        this.price=price;
    }

    // to String method
    public abstract String toString();

    public abstract String getCategory();

    public abstract String getInfo();

    public void decreaseQuantity(int quantity) {
    }
}


