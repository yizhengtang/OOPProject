package ie.atu;
import org.w3c.dom.ls.LSInput;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Delete {
    public int deleteAccount() {
        System.out.println("Please enter your username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.println("Please enter your password: ");
        String password = scanner.nextLine();
        String updateSQL = "DELETE FROM customer WHERE username = '" + username + "' AND password = '" + password + "';";
        try (Connection connection = DatabaseUtils.getConnection(); Statement statement = connection.createStatement()) {
            int rowsUpdated = statement.executeUpdate(updateSQL);
            System.out.println("Your account is deleted");
            return rowsUpdated;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}