package GUI;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class IdAndPassword {

    private HashMap<String, String> logininfo = new HashMap<>();

    IdAndPassword() {
        // Load existing users from file on initialization
        loadUsers();
    }

    public HashMap<String, String> getLoginInfo() {
        return logininfo;
    }

    public boolean userExists(String username) {
        return logininfo.containsKey(username);
    }

    public void addUser(String username, String password) {
        logininfo.put(username, password);
    }

    public void saveUsers() throws IOException {
        try (FileWriter writer = new FileWriter("user_data.txt")) {
            for (Map.Entry<String, String> entry : logininfo.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
        }
    }

    public HashMap getUsers() {
        return logininfo;
    }

    public void loadUsers() {
        File file = new File("user_data.txt");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String username = parts[0];
                    String password = parts[1];
                    logininfo.put(username, password);
                }
            }
        } catch (FileNotFoundException e) {
            // Ignore if the file is not found, it will be created when saving users
        }
    }

    // Static method to save users without creating an instance of IdAndPassword
    public static void saveUsers(Map<String, String> users) throws IOException {
        try (FileWriter writer = new FileWriter("user_data.txt")) {
            for (Map.Entry<String, String> entry : users.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
        }

    }
}

