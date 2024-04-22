package ie.atu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Read {
    public static void main(String[] args) {

        //public static void viewProductDetails() {
        Scanner scanner = new Scanner(System.in);

        // Fetch and display available stores with their IDs and locations
        List<Store> stores = getStores();
        System.out.println("Available Stores:");
        for (Store store : stores) {
            System.out.println(store.getId() + ": " + store.getAddress());
        }

        // Choose store by ID
        System.out.println("\nPlease choose a store by entering its ID:");
        int storeId = Integer.parseInt(scanner.nextLine());
        // Fetch the location of the chosen store based on the ID
        String storeLocation = getStoreLocationById(storeId);

        // Fetch and display available products for the chosen store
        List<Product> products = getProductsForStore(storeLocation);
        System.out.println("\nAvailable Products for Store at " + storeLocation + ":");
        for (Product product : products) {
            System.out.println(product.getId() + ": " + product.getName());
        }

        // Prompt the user to choose a product by ID
        System.out.println("\nPlease choose a product by entering its ID:");
        int productId = Integer.parseInt(scanner.nextLine());

        // Fetch the product details based on the chosen ID
        Product chosenProduct = getProductById(productId);
        if (chosenProduct != null) {
            // Fetch and display available colors for the chosen product
            List<String> colors = getColorsForProduct(productId);
            System.out.println("\nAvailable Colors for " + chosenProduct.getName() + ":");
            for (String color : colors) {
                System.out.println(color);
            }

            // Prompt the user to choose a color
            System.out.println("\nPlease choose a color by entering its value:");
            String colorValue = scanner.nextLine();

            // Fetch and display available sizes for the chosen product and color
            List<String> sizes = getSizesForProductAndColor(productId, colorValue);
            System.out.println("\nAvailable Sizes for " + chosenProduct.getName() + " in " + colorValue + ":");
            for (String size : sizes) {
                System.out.println(size);
            }

            // Prompt the user to choose a size
            System.out.println("\nPlease choose a size by entering its value:");
            String sizeValue = scanner.nextLine();

            // Check if the chosen product, color, and size are in stock
            String stockStatus = checkStockStatus(productId, colorValue, sizeValue);
            System.out.println("\nStock Status: " + stockStatus);
        } else {
            System.out.println("Invalid product ID.");
        }
    }

    private static List<Store> getStores() {
        List<Store> stores = new ArrayList<>();
        String selectSQL = "SELECT store_id, store FROM store";

        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("store_id");
                String address = resultSet.getString("store");
                stores.add(new Store(id, address));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stores;

    }
    private static String getStoreLocationById(int storeId) {
        String storeLocation = "";
        String selectSQL = "SELECT store FROM store WHERE store_id = ?";

        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {

            preparedStatement.setInt(1, storeId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    storeLocation = resultSet.getString("store");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return storeLocation;
    }

    private static List<Product> getProductsForStore(String storeLocation) {
        List<Product> products = new ArrayList<>();
        String selectSQL = "SELECT DISTINCT product.product_id, product.name FROM product " +
                "JOIN inventory ON product.product_id = inventory.product_id " +
                "JOIN store ON inventory.store_id = store.store_id " +
                "WHERE store.store = ?";

        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {

            preparedStatement.setString(1, storeLocation);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("product_id");
                    String name = resultSet.getString("name");
                    products.add(new Product(id, name));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    private static Product getProductById(int productId) {
        Product product = null;
        String selectSQL = "SELECT name FROM product WHERE product_id = ?";

        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {

            preparedStatement.setInt(1, productId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    product = new Product(productId, name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    private static List<String> getColorsForProduct(int productId) {
        List<String> colors = new ArrayList<>();
        String selectSQL = "SELECT DISTINCT colorway.colour FROM product " +
                "JOIN product_colorway ON product.product_id = product_colorway.product_id " +
                "JOIN colorway ON product_colorway.colorway_id = colorway.colorway_id " +
                "WHERE product.product_id = ?";

        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {

            preparedStatement.setInt(1, productId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String color = resultSet.getString("colour");
                    colors.add(color);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return colors;
    }

    private static List<String> getSizesForProductAndColor(int productId, String colorValue) {
        List<String> sizes = new ArrayList<>();
        String selectSQL = "SELECT DISTINCT size.size FROM product " +
                "JOIN product_size ON product.product_id = product_size.product_id " +
                "JOIN size ON product_size.size_id = size.size_id " +
                "JOIN inventory ON product.product_id = inventory.product_id " +
                "JOIN product_colorway ON product.product_id = product_colorway.product_id " +
                "JOIN colorway ON product_colorway.colorway_id = colorway.colorway_id " +
                "WHERE product.product_id = ? AND colorway.colour = ?";

        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {

            preparedStatement.setInt(1, productId);
            preparedStatement.setString(2, colorValue);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String size = resultSet.getString("size");
                    sizes.add(size);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sizes;
    }

    private static String checkStockStatus(int productId, String colorValue, String sizeValue) {
        String stockStatus = "";
        String selectSQL = "SELECT product.status FROM product " +
                "JOIN product_colorway ON product.product_id = product_colorway.product_id " +
                "JOIN colorway ON product_colorway.colorway_id = colorway.colorway_id " +
                "JOIN product_size ON product.product_id = product_size.product_id " +
                "JOIN size ON product_size.size_id = size.size_id " +
                "WHERE product.product_id = ? AND colorway.colour = ? AND size.size = ?";

        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {

            preparedStatement.setInt(1, productId);
            preparedStatement.setString(2, colorValue);
            preparedStatement.setString(3, sizeValue);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    stockStatus = resultSet.getString("status");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stockStatus;
    }
}
