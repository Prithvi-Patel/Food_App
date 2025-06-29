package com.Food_App.Launch_All;

import java.util.Scanner;

import com.Food_App.DAOimpl.RestaurantDAOimpl;
import com.Food_App.Models.Restaurant;

public class Launch_Restaurant {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter restaurant name: ");
        String name = sc.nextLine();

        System.out.print("Enter address: ");
        String address = sc.nextLine();

        System.out.print("Enter phone number: ");
        String phoneNumber = sc.nextLine();

        System.out.print("Enter cuisine type (e.g., South Indian, North Indian, Chinese, etc.): ");
        String cuisineType = sc.nextLine();

        System.out.print("Enter delivery time (e.g., 30 mins): ");
        String deliveryTime = sc.nextLine();

        System.out.print("Enter admin user ID (must exist in user table): ");
        int adminUserId = sc.nextInt();
        sc.nextLine(); // Clear buffer

        System.out.print("Enter rating (e.g., 4.3): ");
        float rating = sc.nextFloat();
        sc.nextLine(); // Clear buffer

        System.out.print("Enter image path (e.g., /images/restaurant1.jpg): ");
        String imagePath = sc.nextLine();

        // By default setting restaurant as active
        boolean isActive = true;

        // Create restaurant object without setting restaurant_id (as it's auto-generated)
        Restaurant restaurant = new Restaurant(name, address, phoneNumber, cuisineType, deliveryTime, adminUserId, rating, isActive, imagePath);

        // Insert via DAO
        RestaurantDAOimpl dao = new RestaurantDAOimpl();
        dao.addRestaurant(restaurant);

        System.out.println("Restaurant added successfully!");
    }
}
