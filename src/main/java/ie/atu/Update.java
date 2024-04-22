package ie.atu;

import java.sql.*;
import java.util.Scanner;

public class Update {
    public static void main(String[] args) {

    }
    public static void updateCustomerAccount(){ //All
        int i = 0;
        while(i == 0) {
            System.out.println("Please select a field");
            System.out.println("1. Username\t2. Email\t3. Password\t4. Address");
            Scanner scanner = new Scanner(System.in);
            int selection = scanner.nextInt();
            scanner.nextLine();
            switch(selection) {
                case 1:
                    System.out.println("Please enter a new username");
                    String newUsername = scanner.nextLine();
                    System.out.println("Please enter your current username");
                    String currentUsername = scanner.nextLine();
                    System.out.println("Please enter your password");
                    String password = scanner.nextLine();
                    String updateSQL = "UPDATE customer SET username = '" + newUsername + "' WHERE username = '" + currentUsername + "' AND password = '" + password + "';";
                    try (Connection connection = DatabaseUtils.getConnection();
                         Statement statement = connection.createStatement()) {
                        int rowsUpdated = statement.executeUpdate(updateSQL);
                        if(rowsUpdated == 1) {
                            System.out.println("Your username has been updated");
                        }
                        System.out.println("Do you want to continue? (y/n)");
                        String answer = scanner.nextLine();
                        if(answer.equals("n")) {
                            i++;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("Please enter an email");
                    String email = scanner.nextLine();
                    System.out.println("Please enter your username");
                    String username = scanner.nextLine();
                    System.out.println("Please enter your password");
                    password = scanner.nextLine();
                    updateSQL = "UPDATE customer SET email = '" + email + "' WHERE username = '" + username + "' AND password = '" + password + "';";
                    try (Connection connection = DatabaseUtils.getConnection();
                         Statement statement = connection.createStatement()) {
                        int rowsUpdated = statement.executeUpdate(updateSQL);
                        if(rowsUpdated == 1) {
                            System.out.println("Your email has been updated");
                        }
                        System.out.println("Do you want to continue? (y/n)");
                        String answer = scanner.nextLine();
                        if(answer.equals("n")) {
                            i++;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.println("Please enter a new password");
                    String newPassword = scanner.nextLine();
                    System.out.println("Please enter your username");
                    username = scanner.nextLine();
                    System.out.println("Please enter your current password");
                    String currentPassword = scanner.nextLine();
                    updateSQL = "UPDATE customer SET password = '" + newPassword + "' WHERE username = '" + username + "' AND password = '" + currentPassword + "';";
                    try (Connection connection = DatabaseUtils.getConnection();
                         Statement statement = connection.createStatement()) {
                        int rowsUpdated = statement.executeUpdate(updateSQL);
                        if(rowsUpdated == 1) {
                            System.out.println("Your password has been updated");
                        }
                        System.out.println("Do you want to continue? (y/n)");
                        String answer = scanner.nextLine();
                        if(answer.equals("n")) {
                            i++;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    System.out.println("Please select a field");
                    System.out.println("1. Address\t2. City\t3. Country\t4. Postal Code");
                    scanner = new Scanner(System.in);
                    selection = scanner.nextInt();
                    scanner.nextLine();
                    switch(selection) {
                        case 1:
                            System.out.println("Please enter an address");
                            String address = scanner.nextLine();
                            System.out.println("Please enter your username");
                            username = scanner.nextLine();
                            System.out.println("Please enter your password");
                            password = scanner.nextLine();
                            updateSQL = "UPDATE address a JOIN customer c ON a.address_id = c.address_id SET a.address = '" + address + "' WHERE c.username = '" + username + "' AND c.password = '" + password + "';";
                            try (Connection connection = DatabaseUtils.getConnection();
                                 Statement statement = connection.createStatement()) {
                                int rowsUpdated = statement.executeUpdate(updateSQL);
                                if(rowsUpdated == 1) {
                                    System.out.println("Your address has been updated");
                                }
                                System.out.println("Do you want to continue? (y/n)");
                                String answer = scanner.nextLine();
                                if(answer.equals("n")) {
                                    i++;
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                            System.out.println("Please enter a city");
                            String city = scanner.nextLine();
                            System.out.println("Please enter your username");
                            username = scanner.nextLine();
                            System.out.println("Please enter your password");
                            password = scanner.nextLine();
                            updateSQL = "UPDATE address a JOIN customer c ON a.address_id = c.address_id SET a.city = '" + city + "' WHERE c.username = '" + username + "' AND c.password = '" + password + "';";
                            try (Connection connection = DatabaseUtils.getConnection();
                                 Statement statement = connection.createStatement()) {
                                int rowsUpdated = statement.executeUpdate(updateSQL);
                                if(rowsUpdated == 1) {
                                    System.out.println("Your city has been updated");
                                }
                                System.out.println("Do you want to continue? (y/n)");
                                String answer = scanner.nextLine();
                                if(answer.equals("n")) {
                                    i++;
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 3:
                            System.out.println("Please enter a country");
                            String country = scanner.nextLine();
                            System.out.println("Please enter your username");
                            username = scanner.nextLine();
                            System.out.println("Please enter your password");
                            password = scanner.nextLine();
                            updateSQL = "UPDATE address a JOIN customer c ON a.address_id = c.address_id SET a.country = '" + country + "' WHERE c.username = '" + username + "' AND c.password = '" + password + "';";
                            try (Connection connection = DatabaseUtils.getConnection();
                                 Statement statement = connection.createStatement()) {
                                int rowsUpdated = statement.executeUpdate(updateSQL);
                                if(rowsUpdated == 1) {
                                    System.out.println("Your country has been updated");
                                }
                                System.out.println("Do you want to continue? (y/n)");
                                String answer = scanner.nextLine();
                                if(answer.equals("n")) {
                                    i++;
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 4:
                            System.out.println("Please enter a postal code");
                            String postalCode = scanner.nextLine();
                            System.out.println("Please enter your username");
                            username = scanner.nextLine();
                            System.out.println("Please enter your password");
                            password = scanner.nextLine();
                            updateSQL = "UPDATE address a JOIN customer c ON a.address_id = c.address_id SET a.postal_code = '" + postalCode + "' WHERE c.username = '" + username + "' AND c.password = '" + password + "';";
                            try (Connection connection = DatabaseUtils.getConnection();
                                 Statement statement = connection.createStatement()) {
                                int rowsUpdated = statement.executeUpdate(updateSQL);
                                if(rowsUpdated == 1) {
                                    System.out.println("Your postal code has been updated");
                                }
                                System.out.println("Do you want to continue? (y/n)");
                                String answer = scanner.nextLine();
                                if(answer.equals("n")) {
                                    i++;
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            break;
                        default:
                            System.out.println("Error: Incorrect selection");
                            System.out.println("Do you want to continue? (y/n)");
                            String answer = scanner.nextLine();
                            if(answer.equals("n")) {
                                i++;
                            }
                            break;
                    }
                    break;
                default:
                    System.out.println("Error: Incorrect selection");
                    System.out.println("Do you want to continue? (y/n)");
                    String answer = scanner.nextLine();
                    if(answer.equals("n")) {
                        i++;
                    }
                    break;
            }
        }
    }
    public void updateProduct(){ //Staff

    }
    public void updateInventory(){ //Staff

    }
    public static void updateStaffAccount(){ //All
        int i = 0;
        while(i == 0) {
            System.out.println("Please select a field");
            System.out.println("1. Username\t2. Email\t3. Password\t4. Address");
            Scanner scanner = new Scanner(System.in);
            int selection = scanner.nextInt();
            scanner.nextLine();
            switch(selection) {
                case 1:
                    System.out.println("Please enter a new username");
                    String newUsername = scanner.nextLine();
                    System.out.println("Please enter your current username");
                    String currentUsername = scanner.nextLine();
                    System.out.println("Please enter your password");
                    String password = scanner.nextLine();
                    String updateSQL = "UPDATE staff SET username = '" + newUsername + "' WHERE username = '" + currentUsername + "' AND password = '" + password + "';";
                    try (Connection connection = DatabaseUtils.getConnection();
                         Statement statement = connection.createStatement()) {
                        int rowsUpdated = statement.executeUpdate(updateSQL);
                        if(rowsUpdated == 1) {
                            System.out.println("Your username has been updated");
                        }
                        System.out.println("Do you want to continue? (y/n)");
                        String answer = scanner.nextLine();
                        if(answer.equals("n")) {
                            i++;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("Please enter an email");
                    String email = scanner.nextLine();
                    System.out.println("Please enter your username");
                    String username = scanner.nextLine();
                    System.out.println("Please enter your password");
                    password = scanner.nextLine();
                    updateSQL = "UPDATE staff SET email = '" + email + "' WHERE username = '" + username + "' AND password = '" + password + "';";
                    try (Connection connection = DatabaseUtils.getConnection();
                         Statement statement = connection.createStatement()) {
                        int rowsUpdated = statement.executeUpdate(updateSQL);
                        if(rowsUpdated == 1) {
                            System.out.println("Your email has been updated");
                        }
                        System.out.println("Do you want to continue? (y/n)");
                        String answer = scanner.nextLine();
                        if(answer.equals("n")) {
                            i++;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.println("Please enter a new password");
                    String newPassword = scanner.nextLine();
                    System.out.println("Please enter your username");
                    username = scanner.nextLine();
                    System.out.println("Please enter your current password");
                    String currentPassword = scanner.nextLine();
                    updateSQL = "UPDATE staff SET password = '" + newPassword + "' WHERE username = '" + username + "' AND password = '" + currentPassword + "';";
                    try (Connection connection = DatabaseUtils.getConnection();
                         Statement statement = connection.createStatement()) {
                        int rowsUpdated = statement.executeUpdate(updateSQL);
                        if(rowsUpdated == 1) {
                            System.out.println("Your password has been updated");
                        }
                        System.out.println("Do you want to continue? (y/n)");
                        String answer = scanner.nextLine();
                        if(answer.equals("n")) {
                            i++;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    System.out.println("Please select a field");
                    System.out.println("1. Address\t2. City\t3. Country\t4. Postal Code");
                    scanner = new Scanner(System.in);
                    selection = scanner.nextInt();
                    scanner.nextLine();
                    switch(selection) {
                        case 1:
                            System.out.println("Please enter an address");
                            String address = scanner.nextLine();
                            System.out.println("Please enter your username");
                            username = scanner.nextLine();
                            System.out.println("Please enter your password");
                            password = scanner.nextLine();
                            updateSQL = "UPDATE address a JOIN staff s ON a.address_id = s.address_id SET a.address = '" + address + "' WHERE s.username = '" + username + "' AND s.password = '" + password + "';";
                            try (Connection connection = DatabaseUtils.getConnection();
                                 Statement statement = connection.createStatement()) {
                                int rowsUpdated = statement.executeUpdate(updateSQL);
                                if(rowsUpdated == 1) {
                                    System.out.println("Your address has been updated");
                                }
                                System.out.println("Do you want to continue? (y/n)");
                                String answer = scanner.nextLine();
                                if(answer.equals("n")) {
                                    i++;
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                            System.out.println("Please enter a city");
                            String city = scanner.nextLine();
                            System.out.println("Please enter your username");
                            username = scanner.nextLine();
                            System.out.println("Please enter your password");
                            password = scanner.nextLine();
                            updateSQL = "UPDATE address a JOIN staff s ON a.address_id = s.address_id SET a.city = '" + city + "' WHERE s.username = '" + username + "' AND s.password = '" + password + "';";
                            try (Connection connection = DatabaseUtils.getConnection();
                                 Statement statement = connection.createStatement()) {
                                int rowsUpdated = statement.executeUpdate(updateSQL);
                                if(rowsUpdated == 1) {
                                    System.out.println("Your city has been updated");
                                }
                                System.out.println("Do you want to continue? (y/n)");
                                String answer = scanner.nextLine();
                                if(answer.equals("n")) {
                                    i++;
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 3:
                            System.out.println("Please enter a country");
                            String country = scanner.nextLine();
                            System.out.println("Please enter your username");
                            username = scanner.nextLine();
                            System.out.println("Please enter your password");
                            password = scanner.nextLine();
                            updateSQL = "UPDATE address a JOIN staff s ON a.address_id = s.address_id SET a.country = '" + country + "' WHERE s.username = '" + username + "' AND s.password = '" + password + "';";
                            try (Connection connection = DatabaseUtils.getConnection();
                                 Statement statement = connection.createStatement()) {
                                int rowsUpdated = statement.executeUpdate(updateSQL);
                                if(rowsUpdated == 1) {
                                    System.out.println("Your country has been updated");
                                }
                                System.out.println("Do you want to continue? (y/n)");
                                String answer = scanner.nextLine();
                                if(answer.equals("n")) {
                                    i++;
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 4:
                            System.out.println("Please enter a postal code");
                            String postalCode = scanner.nextLine();
                            System.out.println("Please enter your username");
                            username = scanner.nextLine();
                            System.out.println("Please enter your password");
                            password = scanner.nextLine();
                            updateSQL = "UPDATE address a JOIN staff s ON a.address_id = s.address_id SET a.postal_code = '" + postalCode + "' WHERE s.username = '" + username + "' AND s.password = '" + password + "';";
                            try (Connection connection = DatabaseUtils.getConnection();
                                 Statement statement = connection.createStatement()) {
                                int rowsUpdated = statement.executeUpdate(updateSQL);
                                if(rowsUpdated == 1) {
                                    System.out.println("Your postal code has been updated");
                                }
                                System.out.println("Do you want to continue? (y/n)");
                                String answer = scanner.nextLine();
                                if(answer.equals("n")) {
                                    i++;
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            break;
                        default:
                            System.out.println("Error: Incorrect selection");
                            System.out.println("Do you want to continue? (y/n)");
                            String answer = scanner.nextLine();
                            if(answer.equals("n")) {
                                i++;
                            }
                            break;
                    }
                    break;
                default:
                    System.out.println("Error: Incorrect selection");
                    System.out.println("Do you want to continue? (y/n)");
                    String answer = scanner.nextLine();
                    if(answer.equals("n")) {
                        i++;
                    }
                    break;
            }
        }
    }
    public void updateStore(){ //Staff

    }
}