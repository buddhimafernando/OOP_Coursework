package Console;

public class Electronics extends Product{

    private String brand;
    private int warrantyPeriod;

    // declaring the constructor
    public Electronics(String productId, String productName, int noItems, double price, String brand, int warrantyPeriod) {

        // invoking the properties in the parent class Product
        super(productId,productName,noItems,price);

        // initializing properties related to this subclass Electronics
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    //declaring getters for each variable
    public String getBrand() {
        return brand;
    }
    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    //declaring setters for each variable
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public String toString() {
        return "Electronics, " + productId + ", " + productName + ", " + quantity +  ", " + price + ", " + brand+" , "+warrantyPeriod;
    }

    @Override
    public String getCategory() {
        return "Electronics";
    }

    @Override
    public String getInfo() {
        return brand+warrantyPeriod;
    }
}
