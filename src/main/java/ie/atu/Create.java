package ie.atu;
import ie.atu.DatabaseUtils;

import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;
public class Create

{
    public static void main(String[] args) {
    }




    private static int getLastInsertId(Connection conn, String selection, String selection_id) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            // Retrieve the maximum value of the user_id column from the users table
            ResultSet rs = stmt.executeQuery("SELECT MAX(" + selection_id + ") FROM " + selection);
            rs.next();
            int maxId = rs.getInt(1);
            // Increment the maximum value by 1 to get the next available user_id
            return maxId + 1;
        }
    }

    public static void addProduct(Connection conn, Scanner scanner) {

        try {
            scanner.nextLine();
            System.out.println("Please enter the name of the product:");
            String name = scanner.nextLine();
            System.out.println("Please enter the brand of the product:");
            String brand = scanner.nextLine();
            System.out.println("Please enter the price of the product:");
            int price = scanner.nextInt();
            System.out.println("Please enter the release date of the product:");
            int date = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Please enter the description of the product:");
            String desc = scanner.nextLine();
            System.out.println("Please enter the status of the product (In Stock/Out of Stock): ");
            String status = scanner.nextLine();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO product(product_id,name,brand,price,release_date,description, status) VALUES (?, ?, ?, ?, ?, ?, ?)");
            int productId = getLastInsertId(conn, "product", "product_id");
            stmt.setInt(1, productId);
            stmt.setString(2, name);
            stmt.setString(3, brand);
            stmt.setInt(4, price);
            stmt.setInt(5, date);
            stmt.setString(6, desc);
            stmt.setString(7, status);

            stmt.executeUpdate();
            assignStore(conn, scanner, productId);
            System.out.println("------------------------");
            System.out.println("Creating Product ...");
        } catch (SQLException ex) {
            System.out.println("Failed to create product.");
            ex.printStackTrace();
        }
    }

    public static void addSize(Connection conn, Scanner scanner, int productId, int storeId, int colorId) {
        int exitSize = 1;
        ArrayList<Integer> size = new ArrayList<Integer>();

        String selectSQL = "SELECT s.size_id, s.size FROM size s ";
        String selectedStore = "SELECT s.store FROM store s WHERE s.store_id = " + storeId ;
        String selectedColor = "SELECT c.colour FROM colorway c WHERE c.colorway_id = " + colorId ;
        try (Connection connection = DatabaseUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)){

            System.out.println("SizeID\tSize");
            while (resultSet.next()) {
                int SizeId = resultSet.getInt("size_id");
                float Size = resultSet.getFloat("size");
                System.out.println(SizeId + "\t\t" + Size);
            }

            try (ResultSet store = statement.executeQuery(selectedStore)){
                if (store.next()){
                    String storeName = store.getString("store");

                    try (ResultSet color = statement.executeQuery(selectedColor)) {
                        if (color.next()) {
                            String colorway = color.getString("colour");

                            System.out.println("In store " + storeName + ", Please select available size for color " + colorway + ": ");
                            System.out.println("Please enter the Size ID (Enter 0 to exit):");

                            while (exitSize != 0) {
                                int assignSize = scanner.nextInt();
                                if (assignSize != 0) {
                                    size.add(assignSize);
                                } else {
                                    exitSize = assignSize;
                                }

                                try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO inventory(inventory_id, product_id, store_id, colorway_id, size_id) VALUES (?, ?, ?, ?, ?)")) {
                                    for (int assignedSizeId : size) {
                                        int inventoryId = getLastInsertId(conn, "inventory", "inventory_id");
                                        stmt.setInt(1, inventoryId);
                                        stmt.setInt(2, productId);
                                        stmt.setInt(3, storeId);
                                        stmt.setInt(4, colorId);
                                        stmt.setInt(5, assignedSizeId);
                                        stmt.executeUpdate();
                                    }
                                } catch (SQLException ex) {
                                    System.out.println("Failed to insert size into inventory.");
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to retrieve size, store, color from database.");
            e.printStackTrace();
        }
    }

    public static void assignStore(Connection conn, Scanner scanner, int productId) {
        int exitStore = 1;
        ArrayList<Integer> store = new ArrayList<>();

        String selectSQL = "SELECT s.store_id, s.store FROM store s";
        try (Connection connection = DatabaseUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {
            System.out.println("Please assign the product to any of these stores:");
            System.out.println("Store ID\tStore");

            while (resultSet.next()) {
                int StoreId = resultSet.getInt("store_id");
                String Store = resultSet.getString("store");
                System.out.println(StoreId + "\t\t\t" + Store);
            }
        }catch (SQLException ex) {
            System.out.println("Failed to retrieve stores!");
            ex.printStackTrace();
            return;
        }

                System.out.println("Please enter the Store ID (Enter 0 to exit):");

                while (exitStore != 0) {
                    int assignStore = scanner.nextInt();
                    if (assignStore != 0) {
                        store.add(assignStore);
                    } else {
                        exitStore = assignStore;
                    }
                 }

                //PreparedStatement stmt = conn.prepareStatement("INSERT INTO inventory(product_id, store_id) VALUES (?, ?)");
                for (int assignedStoreId : store) {
                    addColor (conn, scanner, productId, assignedStoreId);
                }


                System.out.println("------------------------");
                System.out.println("Adding products to the selected store(s)....");

    }


    public static void addColor(Connection conn, Scanner scanner, int productId, int storeId) {
        int exitColor = 1;
        ArrayList<Integer> colorway = new ArrayList<Integer>();
        String selectedStore = "SELECT s.store FROM store s WHERE s.store_id = " + storeId;
        String selectSQL = "SELECT c.colorway_id, c.colour FROM colorway c ";
        try (Connection connection = DatabaseUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet store = statement.executeQuery(selectedStore)
        ) {
            if(store.next()) {
                String storeName = store.getString("store");
                System.out.println("Please enter the available colorways for the product in store " + storeName + ":");
                System.out.println("ColorwayID\tColour");
            }

            try (ResultSet resultSet = statement.executeQuery(selectSQL)) {

                while (resultSet.next()) {
                    int ColorwayId = resultSet.getInt("colorway_id");
                    String colour = resultSet.getString("colour");
                    System.out.println(ColorwayId + "\t\t\t" + colour);
                }

                System.out.println("Please enter the Colorway ID (Enter 0 to exit):");
                while (exitColor != 0) {
                    int assignSize = scanner.nextInt();
                    if (assignSize != 0) {
                        colorway.add(assignSize);
                    } else {
                        exitColor = assignSize;
                    }
                }


                for (int assignedColorwayId : colorway) {
                    addSize(conn, scanner, productId, storeId, assignedColorwayId);
                }


                System.out.println("------------------------");
                System.out.println("Creating Colorways available for the created product ...");

            } catch (SQLException ex) {
                System.out.println("Error 1.");
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            System.out.println("error 2");
            ex.printStackTrace();
        }
    }

        public static int addAddress(Connection conn, Scanner scanner) {
            int addressId = 0;
            try {
                System.out.println("Please enter address: ");
                String address = scanner.nextLine();
                System.out.println("Please enter postal code: ");
                String postal = scanner.nextLine();
                System.out.println("Please enter city: ");
                String city = scanner.nextLine();
                System.out.println("Please enter country: ");
                String country = scanner.nextLine();

                addressId = getLastInsertId(conn, "address", "address_id");

                PreparedStatement stmt = conn.prepareStatement("INSERT INTO address(address_id, address, postal_code, city, country) VALUES (?, ?, ?, ?, ?)");
                stmt.setInt(1, addressId);
                stmt.setString(2, address);
                stmt.setString(3, postal);
                stmt.setString(4, city);
                stmt.setString(5, country);
                stmt.executeUpdate();

            } catch (SQLException ex) {
                System.out.println("Failed");
                ex.printStackTrace();
            }

            return addressId;
        }


    public static void addStore(Connection conn, Scanner scanner) {
        try{
            scanner.nextLine();
            System.out.println("Please enter store name: ");
            String store = scanner.nextLine();
            int addressId = addAddress(conn, scanner);

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO store (store_id, address_id, store) VALUES (?, ?, ?)");
            stmt.setInt(1, getLastInsertId(conn, "store", "store_id"));
            stmt.setInt(2, addressId);
            stmt.setString(3, store);
            stmt.executeUpdate();
        }catch (SQLException ex) {
            System.out.println("Error creating store!");
        }
    }



}

