package com.Food_App.Launch_All;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.Food_App.DAOimpl.UserDAOimpl;
import com.Food_App.Models.User;


public class Launch_User{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        //This is for inserting the data into database
        
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter user name: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter phone number: ");
        String phonenumber = sc.nextLine();

        System.out.print("Enter address: ");
        String address = sc.nextLine();

        System.out.print("Enter role (e.g., super_admin / delivery_agent / customer / restaurant_admin ): ");
        String role = sc.nextLine();
        
        List<String> validRoles = Arrays.asList("customer", "restaurant_admin", "delivery_agent", "super_admin");
        if (!validRoles.contains(role)) {
            System.out.println("Invalid role entered. Please enter one of the allowed roles.");
            return;
        }

        User user = new User();
        
        User u = new User(name, username, password, email, phonenumber, address, role);
        
        UserDAOimpl udao = new UserDAOimpl();         // this line will be same for all the DML Query
        udao.addUser(u);

    }
}
