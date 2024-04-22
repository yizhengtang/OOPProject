package ie.atu.Create;

import ie.atu.DatabaseUtils;

import java.sql.*;
import java.util.Scanner;

public class SignUp {

    public SignUp() {
    }

    public static Person UserType(String userType) throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/shoedatabase", "root", "password");
        Scanner scanner = new Scanner(System.in);

        Person p = null;
        p = addUser(conn, scanner, userType);
        return p;
    }

    public static Person addUser(Connection conn, Scanner scanner, String User) {
        int assignedStore = 0;
        try {

            System.out.println("Please enter your username: ");
            String userName = scanner.nextLine();
            System.out.println("Please enter your email:");
            String email = scanner.nextLine();
            System.out.println("Please enter your password:");
            String passWord = scanner.nextLine();

            int addressId = Create.addAddress(conn, scanner);

            PreparedStatement stmt = null;
            if (User.equals("customer")) {
                stmt = conn.prepareStatement("INSERT INTO customer(customer_id, address_id, username, email, password) VALUES (?, ?, ?, ?, ?)");
            }
            else if (User.equals("staff")) {

                System.out.println("Please assign to any of the existing stores: ");
                String availableStore = "SELECT s.store_id, s.store FROM store s";
                try (Connection connection = DatabaseUtils.getConnection();
                     Statement statement = connection.createStatement();
                     ResultSet store = statement.executeQuery(availableStore)){

                    while (store.next()){
                        int storeId = store.getInt("store_id");
                        String storeName = store.getString ("store");
                        System.out.println(storeId + "\t" + storeName);
                    }

                    System.out.println("Please enter the Store ID");
                    assignedStore = scanner.nextInt();
                }catch (SQLException ex) {
                    System.out.println("Failed to display available store.");
                }
                stmt = conn.prepareStatement("INSERT INTO staff(staff_id, address_id, username, email, password, store_id) VALUES (?, ?, ?, ?, ?, ?)");
            }

            if (stmt != null) {
                stmt.setInt(1, getLastInsertId(conn, User, User + "_id"));
                stmt.setInt(2, addressId);
                stmt.setString(3, userName);
                stmt.setString(4, email);
                stmt.setString(5, passWord);
                if(User.equals("staff")){
                    stmt.setInt(6, assignedStore);
                }
                stmt.executeUpdate();
                System.out.println("Success to add new user!");

                if(User.equals("customer")){
                    newCustomer c = new newCustomer();
                    c.setUsername(userName);
                    c.setEmail(email);
                    c.setPassword(passWord);
                    return c;
                }

                else if(User.equals("staff")){
                    newStaff s = new newStaff();
                    s.setUsername(userName);
                    s.setEmail(email);
                    s.setPassword(passWord);
                    s.setAssignedStore(assignedStore);
                    return s;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Failed to add new user!");
            ex.printStackTrace();
        }
        return null;
    }

    private static int getLastInsertId(Connection conn, String selection, String selection_id) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT MAX(" + selection_id + ") FROM " + selection);
            rs.next();
            int maxId = rs.getInt(1);
            return maxId + 1;
        }
    }
}
