package Console;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void constructor_ValidUsernameAndPassword_UserInitialized() {
        // Arrange
        String username = "john_doe";
        String password = "secure_password";

        // Act
        User user = new User(username, password);

        // Assert
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(0, user.getPurchaseCount());
    }

    @Test
    void constructor_UsernameAndPasswordAndPurchaseCount_UserInitializedWithPurchaseCount() {
        // Arrange
        String username = "jane_doe";
        String password = "another_password";
        int purchaseCount = 5;

        // Act
        User user = new User(username, password, purchaseCount);

        // Assert
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(purchaseCount, user.getPurchaseCount());
    }

    @Test
    void setUsername_NewUsername_UsernameUpdated() {
        // Arrange
        User user = new User("old_username", "password");
        String newUsername = "new_username";

        // Act
        user.setUsername(newUsername);

        // Assert
        assertEquals(newUsername, user.getUsername());
    }

    @Test
    void setPassword_NewPassword_PasswordUpdated() {
        // Arrange
        User user = new User("username", "old_password");
        String newPassword = "new_password";

        // Act
        user.setPassword(newPassword);

        // Assert
        assertEquals(newPassword, user.getPassword());
    }

    @Test
    void setPurchaseCount_NewPurchaseCount_PurchaseCountUpdated() {
        // Arrange
        User user = new User("username", "password", 3);
        int newPurchaseCount = 7;

        // Act
        user.setPurchaseCount(newPurchaseCount);

        // Assert
        assertEquals(newPurchaseCount, user.getPurchaseCount());
    }

    @Test
    void increasePurchaseCount_PurchaseCountIncreasedByOne() {
        // Arrange
        User user = new User("username", "password", 3);

        // Act
        user.increasePurchaseCount();

        // Assert
        assertEquals(4, user.getPurchaseCount());
    }
}