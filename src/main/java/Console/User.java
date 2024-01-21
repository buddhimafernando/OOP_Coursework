package Console;

public class User {
    // declaring instance variables in this class
    protected String username;
    protected String password;
    protected int purchaseCount;

    // initializing the constructors
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.purchaseCount = 0;
    }

    public User(String username, String password, int purchaseCount) {
        this.username = username;
        this.password = password;
        this.purchaseCount = purchaseCount;
    }

    // initializing getters for each variable
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }

    // initializing setters for each variable
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPurchaseCount(int purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public void increasePurchaseCount() {
        this.purchaseCount += 1;
    }
}
