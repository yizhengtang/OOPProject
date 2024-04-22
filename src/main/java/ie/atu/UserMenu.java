package ie.atu;

import ie.atu.Create.Create;
import ie.atu.Create.Person;
import ie.atu.Create.SignUp;

import java.sql.*;
import java.util.Scanner;


public class UserMenu {
    public static void main(String[] args) throws SQLException{

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/shoedatabase", "root", "password");
        Scanner scanner = new Scanner(System.in);
        String exit = "n";      //This is set to n(no) to keep for the while loop
        String login = "f";     //This is set to f(fail) everytime the code starts
        System.out.println("Welcome to the User Menu!");
        System.out.println("-------------------------");
        while (exit.equalsIgnoreCase("n")){

            String menu = Menus.LogInMenu();
            System.out.println(menu);
            int userChoice = scanner.nextInt();

            switch (userChoice){
                case 1:
                    try{
                        scanner.nextLine();
                        System.out.println("Please enter your username:");
                        String username = scanner.nextLine();
                        System.out.println("Please enter your password:");
                        String password = scanner.nextLine();

                        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customer WHERE username = ? AND password = ?");
                        stmt.setString(1, username);
                        stmt.setString(2, password);
                        ResultSet resultSet = stmt.executeQuery();

                        if(resultSet.next()){
                            System.out.println("-------------------------");
                            System.out.println("Hello, " + username);
                            login = "s";
                        }
                        else{
                            System.out.println("Login failed. Please try again.");
                        }

                        while (login ==("s")){
                            menu = Menus.customerMenu();
                            System.out.println(menu);
                            userChoice = scanner.nextInt();
                            switch (userChoice){
                                case 1:
                                    System.out.println("-------------------------");
                                    Read.ReadProduct();
                                    break;
                                case 2:
                                    System.out.println("-------------------------");
                                    Read.viewStoreDetails();
                                    break;

                                case 3:
                                    System.out.println("-------------------------");
                                    //Code for edit personal info
                                    Update.updateCustomerAccount();
                                    break;

                                case 4:
                                    System.out.println("-------------------------");
                                    //Code for delete account
                                    Delete.deleteCustomerAccount();
                                    break;

                                case 0:
                                    login = "f";
                                    break;

                                default:
                                    System.out.println("Error input! Try again.");
                                    break;
                            }
                        }


                    }catch (SQLException ex){
                        System.out.println("Error while signing in as a customer!");
                    }
                    break;

                case 2:
                    try{
                        scanner.nextLine();
                        System.out.println("Please enter your username:");
                        String username = scanner.nextLine();
                        System.out.println("Please enter your password:");
                        String password = scanner.nextLine();

                        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM staff WHERE username = ? AND password = ?");
                        stmt.setString(1, username);
                        stmt.setString(2, password);
                        ResultSet resultSet = stmt.executeQuery();

                        if(resultSet.next()){
                            System.out.println("-------------------------");
                            System.out.println("Hello, " + username);
                            login = "s";
                        }
                        else{
                            System.out.println("Login failed. Please try again.");
                        }

                        while (login ==("s")){
                            menu = Menus.staffMenu();
                            System.out.println(menu);
                            userChoice = scanner.nextInt();
                            switch (userChoice){
                                //String sMenu = ("(1)\t Product\n(2)\t Store\n(3)\t Staff\n(4)\t View customer\n(5)\t Delete Account!\n\n(0)\t Logout");
                                case 1:
                                    System.out.println("-------------------------");
                                    System.out.println("Product Menu");
                                    menu = Menus.productMenu();
                                    System.out.println(menu);
                                    //Code for handle product
                                    userChoice = scanner.nextInt();
                                    switch (userChoice){
                                        case 1:
                                            Create.addProduct(conn, scanner);
                                            break;

                                        case 2:
                                            Read.viewProductDetails();
                                            break;

                                        case 3:
                                            //Update product
                                            Update.updateProduct();
                                            break;

                                        case 4:
                                            //Delete product
                                            Delete.deleteProduct();
                                            break;

                                        case 5:
                                            Delete.deleteInventory();

                                        case 0:
                                            System.out.println("-------------------------");
                                            break;

                                        default:
                                            System.out.println("Error input");
                                            break;
                                    }
                                    break;

                                case 2:
                                    System.out.println("-------------------------");
                                    System.out.println("Store Menu");
                                    menu = Menus.selectionMenu();
                                    System.out.println(menu);
                                    //Code for handle Store
                                    userChoice = scanner.nextInt();
                                    switch (userChoice){
                                        case 1:
                                            Create.addStore(conn, scanner);
                                            break;

                                        case 2:
                                            Read.viewStoreDetails();
                                            break;

                                        case 3:
                                            //Update store
                                            Update.updateStore();
                                            break;

                                        case 4:
                                            //Delete store
                                            Delete.deleteStore();
                                            break;

                                        case 0:
                                            System.out.println("-------------------------");
                                            break;

                                        default:
                                            System.out.println("Error input");
                                            break;
                                    }
                                    break;

                                case 3:
                                    System.out.println("-------------------------");
                                    System.out.println("Staff Menu");
                                    menu = Menus.selectionMenu();
                                    System.out.println(menu);
                                    //Code for handle Staff
                                    userChoice = scanner.nextInt();
                                    switch (userChoice){
                                        case 1:
                                            Person p = SignUp.UserType("staff");
                                            System.out.println("-------------------------");
                                            System.out.println(p.toString());
                                            System.out.println("-------------------------");
                                            break;

                                        case 2:
                                            Read.viewStaffDetails();
                                            break;

                                        case 3:
                                            //Update Staff info
                                            Update.updateStaffAccount();
                                            break;

                                        case 4:
                                            //Delete Staff
                                            Delete.deleteStaffAccount();
                                            break;

                                        default:
                                            System.out.println("Error input");
                                            break;
                                    }
                                    break;

                                case 4:
                                    System.out.println("-------------------------");
                                    System.out.println("Read Customer Info");
                                    Read.viewCustomerInfo();
                                    break;

                                case 0:
                                    System.out.println("-------------------------");
                                    login = "f";
                                    break;

                                default:
                                    System.out.println("Error Input.");
                                    break;
                            }
                        }


                    }catch (SQLException ex){
                        System.out.println("Error while signing in as a Staff!");
                    }
                    break;

                case 3:
                    //Code for sign up a new customer
                    Person p = SignUp.UserType("customer");
                    System.out.println("-------------------------");
                    System.out.println(p.toString());
                    System.out.println("-------------------------");
                    break;

                case 0:
                    exit = "y";
                    break;

                default:
                    System.out.println("Wrong input, exit? (y/n):");
                    exit = scanner.nextLine();
                    System.out.println();
                    break;
            }
        }

    }

}