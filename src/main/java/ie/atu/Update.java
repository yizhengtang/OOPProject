package ie.atu;

import java.sql.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Update {
    public static void UpdateTable (){
        System.out.println("Please select a table: ");
        Scanner scanner = new Scanner(System.in);
        String table = scanner.nextLine();
        System.out.println("Please select a column: ");
        String columnUpdate = scanner.nextLine();
        System.out.println("Please select a value: ");
        String valueUpdate = scanner.nextLine();
        System.out.println("Please select a reference column: ");
        String columnReference = scanner.nextLine();
        System.out.println("Please select a reference value: ");
        String valueReference = scanner.nextLine();
        String updateSQL;
        Set<String> numericColumns = new HashSet<>(Arrays.asList("price", "amount", "size", "address_id", "city_id", "colorway_id", "country_id", "customer_id", "inventory_id", "payment_id", "product_id", "size_id", "staff_id", "store_id"));
        Set<String> numericReferenceColumns = new HashSet<>(Arrays.asList("price", "amount", "size"));
        if (numericColumns.contains(columnUpdate)) {
            if (numericReferenceColumns.contains(columnReference)) {
                updateSQL = "UPDATE " + table + " SET " + columnUpdate + " = " + valueUpdate + " WHERE " + columnReference + " = " + valueReference;
            } else {
                updateSQL = "UPDATE " + table + " SET " + columnUpdate + " = " + valueUpdate + " WHERE " + columnReference + " = '" + valueReference + "'";
            }
        } else if (numericReferenceColumns.contains(columnReference)) {
            updateSQL = "UPDATE " + table + " SET " + columnUpdate + " = '" + valueUpdate + "' WHERE " + columnReference + " = " + valueReference;
        } else {
            updateSQL = "UPDATE " + table + " SET " + columnUpdate + " = '" + valueUpdate + "' WHERE " + columnReference + " = '" + valueReference + "'";
        }
        try (Connection connection = DatabaseUtils.getConnection();
             Statement statement = connection.createStatement()) {
            int rowsUpdated = statement.executeUpdate(updateSQL);
            System.out.println("Rows updated: " + rowsUpdated);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}