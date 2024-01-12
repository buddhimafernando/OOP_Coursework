package Console;

public class User {
    // declaring instance variables in this class
    protected String username;
    protected String password;

    // initializing the constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // initializing getters for each variable
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    // initializing setters for each variable
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    // validating the username and the password
    public boolean validateCredentials(String enteredPassword,String enteredUsername){
        return password.equals(enteredPassword) && username.equals(enteredUsername);
    }
}
