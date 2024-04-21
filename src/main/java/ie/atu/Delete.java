package ie.atu;

import java.sql.*;
import java.util.Scanner;

public class Delete {
    public static void main(String[] args){
        Delete delete = new Delete();
        delete.deleteProduct();
    }
    public void deleteAccount() { //Customer
        int i = 0;
        while(i == 0) {
            System.out.println("Please enter your username: ");
            Scanner scanner = new Scanner(System.in);
            String username = scanner.nextLine();
            System.out.println("Please enter your password: ");
            String password = scanner.nextLine();
            String updateSQL = "DELETE c, a FROM customer c JOIN address a ON c.address_id = a.address_id WHERE username = '" + username + "' AND password = '" + password + "';";
            try (Connection connection = DatabaseUtils.getConnection();
                 Statement statement = connection.createStatement()) {
                int rowsUpdated = statement.executeUpdate(updateSQL);
                if(rowsUpdated == 2) {
                    System.out.println("Your account has been deleted");
                    i++;
                } else {
                    System.out.println("Error: Incorrect username or password");
                    System.out.println("Do you want to continue? (y/n)");
                    String answer = scanner.nextLine();
                    if(answer.equals("n")) {
                        i++;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void deleteInventory() { //Staff

    }
    public void deleteProduct() { //Staff
        System.out.println("Please select a product: ");
        String updateSQL = "SELECT name FROM product";
        try (Connection connection = DatabaseUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(updateSQL)) {
            int i = 1;
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                System.out.println(i + ". " + name);
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteStaff() { //Staff

    }
    public void deleteStore() { //Staff

    }
}