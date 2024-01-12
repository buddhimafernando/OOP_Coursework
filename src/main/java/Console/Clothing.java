package Console;

public class Clothing extends Product{

    private String size;
    private String color;

    public Clothing(String productId,String productName,int noItems,double price,String size, String color) {
        // invoking the properties in the parent class Product
        super(productId,productName,noItems,price);

        // initializing properties related to this subclass Clothing
        this.size = size;
        this.color = color;
    }

    // declaring getters for the variables in Clothing class
    public String getSize() {
        return size;
    }
    public String getColor() {
        return color;
    }

    // declaring setters for the variables in Clothing class
    public void setSize(String size) {
        this.size = size;
    }
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Clothing, " + productId + ", " + productName + ", " + quantity +  ", " + price + ", " + size+" , "+color;
    }

    @Override
    public String getCategory() {
        return "Clothing";
    }

    @Override
    public String getInfo() {
        return color+size;
    }
}
