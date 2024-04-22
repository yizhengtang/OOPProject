package ie.atu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Delete {
    public static void main(String[] args){
    }
    public void deleteCustomerAccount() { //Customer
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
        int i = 0;
        while(i == 0) {
            System.out.println("Please select a product_id: ");
            String updateSQL = "SELECT product_id FROM inventory;";
            try (Connection connection = DatabaseUtils.getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(updateSQL)) {
                int j = 0;
                while(resultSet.next()) {
                    String product_id = resultSet.getString("product_id");
                    System.out.print(product_id + "\t");
                    j++;
                }
                Scanner scanner = new Scanner(System.in);
                int selectedProduct_id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Please select a store_id: ");
                updateSQL = "SELECT store_id FROM inventory WHERE product_id = " + selectedProduct_id + ";";
                try (Connection connection2 = DatabaseUtils.getConnection();
                     Statement statement2 = connection.createStatement();
                     ResultSet resultSet2 = statement.executeQuery(updateSQL)) {
                    int k = 0;
                    while(resultSet2.next()) {
                        String store_id = resultSet2.getString("store_id");
                        System.out.print(store_id + "\t");
                        k++;
                    }
                    scanner = new Scanner(System.in);
                    int selectedStore_id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Please select a colorway_id: ");
                    updateSQL = "SELECT colorway_id FROM inventory WHERE product_id = " + selectedProduct_id + " AND store_id = " + selectedStore_id + ";";
                    try (Connection connection3 = DatabaseUtils.getConnection();
                         Statement statement3 = connection.createStatement();
                         ResultSet resultSet3 = statement.executeQuery(updateSQL)) {
                        int l = 0;
                        while(resultSet3.next()) {
                            String colorway_id = resultSet3.getString("colorway_id");
                            System.out.print(colorway_id + "\t");
                            l++;
                        }
                        scanner = new Scanner(System.in);
                        int selectedColorway_id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Please select a size_id: ");
                        updateSQL = "SELECT size_id FROM inventory WHERE product_id = " + selectedProduct_id + " AND store_id = " + selectedStore_id + " AND colorway_id = " + selectedColorway_id + ";";
                        try (Connection connection4 = DatabaseUtils.getConnection();
                             Statement statement4 = connection.createStatement();
                             ResultSet resultSet4 = statement.executeQuery(updateSQL)) {
                            int m = 0;
                            while(resultSet4.next()) {
                                String size_id = resultSet4.getString("size_id");
                                System.out.print(size_id + "\t");
                                m++;
                            }
                            scanner = new Scanner(System.in);
                            int selectedSize_id = scanner.nextInt();
                            scanner.nextLine();
                            updateSQL = "DELETE FROM inventory WHERE product_id = " + selectedProduct_id + " AND store_id = " + selectedStore_id + " AND colorway_id = " + selectedColorway_id + " AND size_id = " + selectedSize_id + ";";
                            try (Connection connection5 = DatabaseUtils.getConnection();
                                 Statement statement5 = connection.createStatement();) {
                                int rowsUpdated = statement.executeUpdate(updateSQL);
                                if(rowsUpdated > 0) {
                                    System.out.println("An inventory has been deleted");
                                } else {
                                    System.out.println("Error: Please try again");
                                }
                                System.out.println("Do you want to continue? (y/n)");
                                String answer = scanner.nextLine();
                                if(answer.equals("n")) {
                                    i++;
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void deleteProduct() { //Staff
        int i = 0;
        while(i == 0) {
            System.out.println("Please select a product: ");
            String updateSQL = "SELECT name FROM product;";
            try (Connection connection = DatabaseUtils.getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(updateSQL)) {
                List<String> names = new ArrayList<>();
                int j = 1;
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    names.add(name);
                    System.out.print(j + ". " + name + "\t");
                    j++;
                }
                Scanner scanner = new Scanner(System.in);
                int selection = scanner.nextInt();
                scanner.nextLine();
                if (selection >= 1 && selection <= names.size()) {
                    String selectedName = names.get(selection - 1);
                    updateSQL = "DELETE p, i FROM product p JOIN inventory i ON p.product_id = i.product_id WHERE name = '" + selectedName + "';";
                    try (Connection connection2 = DatabaseUtils.getConnection();
                         Statement statement2 = connection.createStatement()) {
                        int rowsUpdated = statement.executeUpdate(updateSQL);
                        if(rowsUpdated > 0) {
                            System.out.println("A product has been deleted");
                        } else {
                            System.out.println("Error: Please try again");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Error: Incorrect selection");
                }
                System.out.println("Do you want to continue? (y/n)");
                String answer = scanner.nextLine();
                if(answer.equals("n")) {
                    i++;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void deleteStaffAccount() { //Staff
        int i = 0;
        while(i == 0) {
            System.out.println("Please enter a username: ");
            Scanner scanner = new Scanner(System.in);
            String username = scanner.nextLine();
            System.out.println("Please enter a password: ");
            String password = scanner.nextLine();
            String updateSQL = "DELETE s, a FROM staff s JOIN address a ON s.address_id = a.address_id WHERE username = '" + username + "' AND password = '" + password + "';";
            try (Connection connection = DatabaseUtils.getConnection();
                 Statement statement = connection.createStatement()) {
                int rowsUpdated = statement.executeUpdate(updateSQL);
                if(rowsUpdated == 2) {
                    System.out.println("An account has been deleted");
                } else {
                    System.out.println("Error: Incorrect username or password");
                }
                System.out.println("Do you want to continue? (y/n)");
                String answer = scanner.nextLine();
                if(answer.equals("n")) {
                    i++;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void deleteStore() { //Staff
        int i = 0;
        while(i == 0) {
            System.out.println("Please select a store: ");
            String updateSQL = "SELECT store FROM store;";
            try (Connection connection = DatabaseUtils.getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(updateSQL)) {
                List<String> stores = new ArrayList<>();
                int j = 1;
                while (resultSet.next()) {
                    String store = resultSet.getString("store");
                    stores.add(store);
                    System.out.print(j + ". " + store + "\t");
                    j++;
                }
                Scanner scanner = new Scanner(System.in);
                int selection = scanner.nextInt();
                scanner.nextLine();
                if (selection >= 1 && selection <= stores.size()) {
                    String selectedStore = stores.get(selection - 1);
                    updateSQL = "DELETE s, a, i FROM store s JOIN address a ON s.address_id = a.address_id JOIN inventory i ON s.store_id = i.store_id WHERE store = '" + selectedStore + "';";
                    try (Connection connection2 = DatabaseUtils.getConnection();
                         Statement statement2 = connection.createStatement()) {
                        int rowsUpdated = statement.executeUpdate(updateSQL);
                        if(rowsUpdated > 0) {
                            System.out.println("A store has been deleted");
                        } else {
                            System.out.println("Error: Please try again");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Error: Incorrect selection");
                }
                System.out.println("Do you want to continue? (y/n)");
                String answer = scanner.nextLine();
                if(answer.equals("n")) {
                    i++;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}