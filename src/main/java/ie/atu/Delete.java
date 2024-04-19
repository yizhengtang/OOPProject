package ie.atu;
import org.w3c.dom.ls.LSInput;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Delete {
    private String username;
    private String password;
    public void deleteAccount() {
        System.out.println("Please enter your username: ");
        Scanner scanner = new Scanner(System.in);
        username = scanner.nextLine();
        System.out.println("Please enter your password: ");
        password = scanner.nextLine();
        String updateSQL = "DELETE FROM customer WHERE username = '" + username + "' AND password = '" + password + "';";
        try (Connection connection = DatabaseUtils.getConnection(); Statement statement = connection.createStatement()) {
            System.out.println("Your account is deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
