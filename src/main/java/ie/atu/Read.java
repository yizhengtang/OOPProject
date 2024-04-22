package ie.atu;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Read {
    public static void ReadProduct() {
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

            // Get price for the chosen product
            double price = getPriceForProduct(productId);

            // Display product details
            System.out.println("\nProduct Details:");
            System.out.println("Name: " + chosenProduct.getName());
            System.out.println("Color: " + colorValue);
            System.out.println("Size: " + sizeValue);
            System.out.println("Price: $" + price);
        } else {
            System.out.println("Invalid product ID.");
        }
    }

    public static List<Store> getStores() {
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
        String selectSQL = "SELECT DISTINCT colorway.colour FROM colorway " +
                "JOIN inventory ON colorway.colorway_id = inventory.colorway_id " +
                "WHERE inventory.product_id = ?";

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
        String selectSQL = "SELECT DISTINCT size.size FROM size " +
                "JOIN inventory ON size.size_id = inventory.size_id " +
                "JOIN colorway ON inventory.colorway_id = colorway.colorway_id " +
                "WHERE inventory.product_id = ? AND colorway.colour = ?";

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
    private static double getPriceForProduct(int productId) {
        double price = 0;
        String selectSQL = "SELECT price FROM product WHERE product_id = ?";

        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {

            preparedStatement.setInt(1, productId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    price = resultSet.getDouble("price");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return price;
    }
    public static void viewStoreDetails() {
        String selectSQL = "SELECT store.store_id, store.store, address.address, address.city, address.postal_code, address.country " +
                "FROM store " +
                "JOIN address ON store.address_id = address.address_id";

        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("store_id");
                String name = resultSet.getString("store");
                String street = resultSet.getString("address");
                String city = resultSet.getString("city");
                String postalCode = resultSet.getString("postal_code");
                String country = resultSet.getString("country");

                System.out.println("Store ID: " + id);
                System.out.println("Store Name: " + name);
                System.out.println("Address: " + street);
                System.out.println("City: " + city);
                System.out.println("Postal Code: " + postalCode);
                System.out.println("Country: " + country);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static void viewStaffDetails() {
        String selectSQL = "SELECT staff.staff_id, staff.username, staff.email, address.address, address.city, address.postal_code, address.country " +
                "FROM staff " +
                "JOIN address ON staff.address_id = address.address_id";

        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("staff_id");
                String name = resultSet.getString("username");
                String email = resultSet.getString("email");
                String street = resultSet.getString("address");
                String city = resultSet.getString("city");
                String postalCode = resultSet.getString("postal_code");
                String country = resultSet.getString("country");

                System.out.println("Staff ID: " + id);
                System.out.println("Staff Name: " + name);
                System.out.println("Email: " + email);
                System.out.println("Address: " + street);
                System.out.println("City: " + city);
                System.out.println("Postal Code: " + postalCode);
                System.out.println("Country: " + country);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewCustomerInfo() {
        String selectSQL = "SELECT customer.customer_id, customer.username, customer.email, address.address, address.city, address.postal_code, address.country " +
                "FROM customer " +
                "JOIN address ON customer.address_id = address.address_id";

        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("Customer Information:");
            while (resultSet.next()) {
                int id = resultSet.getInt("customer_id");
                String name = resultSet.getString("username");
                String email = resultSet.getString("email");
                String street = resultSet.getString("address");
                String city = resultSet.getString("city");
                String postalCode = resultSet.getString("postal_code");
                String country = resultSet.getString("country");

                System.out.println("Customer ID: " + id);
                System.out.println("Customer Name: " + name);
                System.out.println("Email: " + email);
                System.out.println("Address: " + street);
                System.out.println("City: " + city);
                System.out.println("Postal Code: " + postalCode);
                System.out.println("Country: " + country);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void viewProductDetails() {
        String selectSQL = "SELECT * FROM product";

        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("Product Details:");
            while (resultSet.next()) {
                int id = resultSet.getInt("product_id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String status = resultSet.getString("status"); // Assuming there's a 'stock' column
                System.out.println("Product ID: " + id + ", Name: " + name + ", Price: $" + price + ", Stock: " + status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
