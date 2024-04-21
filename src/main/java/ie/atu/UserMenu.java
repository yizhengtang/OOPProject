package ie.atu;
import ie.atu.Create.Person;
import ie.atu.Create.SignUp;

import java.sql.*;
import java.util.Scanner;


public class UserMenu {
    public static void main(String[] args) throws SQLException{

        Scanner scanner = new Scanner(System.in);
        String exit = "n";
        System.out.println("Welcome to the User Menu!");
        System.out.println("-------------------------");
        while (exit.equalsIgnoreCase("n")){
            System.out.println("(1)\t Sign in as User");
            System.out.println("(2)\t Sign in as Staff");
            System.out.println("(3)\t Sign up new User");
            System.out.println("(0)\t Exit");
            int userChoice = scanner.nextInt();

            switch (userChoice){
                case 1:
                    //Code for Sign in as User
                    //Check if sign in correct
                    //If correct, create a new switch case that includes view product, edit account, delete account

                    break;

                case 2:
                    //Code for Sign in as Staff
                    //Check if sign in correct
                    //If correct, create a new switch case that includes everything except for edit and delete customer
                    break;

                case 3:
                    //Code for sign up a new customer
                    Person p = SignUp.UserType("customer");
                    System.out.println("---------------");
                    System.out.println(p.toString());
                    System.out.println("---------------");
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