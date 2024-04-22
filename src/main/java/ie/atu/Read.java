package ie.atu;
import java.sql.*;
import java.util.Scanner;
public class Read {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
// Prompt the user to enter the name of the table they want to read
        System.out.println("Please enter the name of the table you want to read: ");
        String tableName = scanner.nextLine();
// Prompt the user to enter the name of the column they want to read from
        System.out.println("Please enter the name of the column you want to select data from (e.g., product name): ");
        String columnToSelect = scanner.nextLine();
// Prompt the user to enter the name of the column they want to see (e.g., price)
        System.out.println("Please enter the name of the column you want to see (e.g., price): ");
        String columnToShow = scanner.nextLine();
// Generate the SELECT statement based on user input
        String selectSQL = "SELECT " + columnToShow + " FROM " + tableName + " WHERE " + columnToSelect + " = ?";
        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
// Prompt the user to enter the value of the column they want to select from
            System.out.println("Please enter the value of " + columnToSelect + ": ");
            String columnValue = scanner.nextLine();
// Set the value of the column to select from in the prepared statement
            preparedStatement.setString(1, columnValue);
// Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String valueToShow = resultSet.getString(columnToShow);
                    System.out.println(columnToShow + ": " + valueToShow);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
