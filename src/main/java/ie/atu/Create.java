package ie.atu;
import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;
public class Create {
    public static void main(String[] args) throws SQLException {

        int exitSize = 1, exitColor = 1;

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/shoedatabase", "root", "password");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select one for create:" +
                "\n1.\tProduct" +
                "\n2.\tColorway" +
                "\n3.\tSize" +
                "\n4.\tCustomer" +
                "\n5.\tStaff");

        int create = scanner.nextInt();

        switch (create) {
            case 1:
                addProduct(conn, scanner, exitSize, exitColor);
                break;

            case 2:

                break;
        }
    }

    private static int getLastInsertId(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            // Retrieve the maximum value of the user_id column from the users table
            ResultSet rs = stmt.executeQuery("SELECT MAX(product_id) FROM product");
            rs.next();
            int maxId = rs.getInt(1);
            // Increment the maximum value by 1 to get the next available user_id
            return maxId + 1;
        }
    }

    public static void addProduct(Connection conn, Scanner scanner, int exitSize, int exitColor) {
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

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO product(product_id,name,brand,price,release_date,description) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, getLastInsertId(conn));
            stmt.setString(2, name);
            stmt.setString(3, brand);
            stmt.setInt(4, price);
            stmt.setInt(5, date);
            stmt.setString(6, desc);
            stmt.executeUpdate();

            addSize(conn, scanner, exitSize);
            addColor(conn, scanner, exitColor);

            System.out.println("------------------------");
            System.out.println("Creating Product ...");
        } catch (SQLException ex) {
            System.out.println("Failed");
            ex.printStackTrace();
        }
    }

    public static void addSize (Connection conn, Scanner scanner, int exitSize){
        ArrayList<Integer> size = new ArrayList<Integer>();

        String selectSQL = "SELECT s.size_id, s.size FROM size s " ;
        try (Connection connection = DatabaseUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {
            System.out.println("Please enter the available sizes for the product:");
             System.out.println("SizeID\tSize");

            while (resultSet.next()) {
                int SizeId = resultSet.getInt("size_id");
                float Size = resultSet.getFloat("size");
                System.out.println(SizeId + "\t\t" + Size);
            }

            try {
                System.out.println("Please enter the Size ID (Enter 0 to exit):");
                while (exitSize != 0) {
                    int assignSize = scanner.nextInt();
                    if (assignSize != 0) {
                        size.add(assignSize);
                    }
                    else {
                        exitSize = assignSize;
                    }
                }

                int productId = getLastInsertId(conn) - 1;
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO product_size(product_id, size_id) VALUES (?, ?)");
                for (int assignedSizeId:size)
                {
                    stmt.setInt(1, productId);
                    stmt.setInt(2,assignedSizeId);
                    stmt.executeUpdate();
                }


                System.out.println("------------------------");
                System.out.println("Creating Size available for the created product ...");
            }catch (SQLException ex) {
                System.out.println("Failed");
                ex.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addColor (Connection conn, Scanner scanner, int exitColor) {
        ArrayList<Integer> colorway = new ArrayList<Integer>();

        String selectSQL = "SELECT c.colorway_id, c.colour FROM colorway c " ;
        try (Connection connection = DatabaseUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {
            System.out.println("Please enter the available colorways for the product:");
            System.out.println("ColorwayID\tColour");

            while (resultSet.next()) {
                int ColorwayId = resultSet.getInt("colorway_id");
                String colour = resultSet.getString("colour");
                System.out.println(ColorwayId + "\t\t\t" + colour);
            }

            try {
                System.out.println("Please enter the Colorway ID (Enter 0 to exit):");
                while (exitColor != 0) {
                    int assignSize = scanner.nextInt();
                    if (assignSize != 0) {
                        colorway.add(assignSize);
                    }
                    else {
                        exitColor = assignSize;
                    }
                }

                int productId = getLastInsertId(conn) - 1;
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO product_colorway(product_id, colorway_id) VALUES (?, ?)");
                for (int assignedColorwayId:colorway)
                {
                    stmt.setInt(1, productId);
                    stmt.setInt(2,assignedColorwayId);
                    stmt.executeUpdate();
                }


                System.out.println("------------------------");
                System.out.println("Creating Colorways available for the created product ...");
            }catch (SQLException ex) {
                System.out.println("Failed");
                ex.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}