package com.Food_App.Launch_All;

import java.util.Scanner;
import com.Food_App.DAOimpl.MenuDAOimpl;
import com.Food_App.Models.Menu;

public class Launch_Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Restaurant ID: ");
        int restaurantId = sc.nextInt();
        sc.nextLine();  // consume newline

        System.out.print("Enter Item Name: ");
        String itemName = sc.nextLine();

        System.out.print("Enter Description: ");
        String description = sc.nextLine();

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        System.out.print("Is item available? (true/false): ");
        boolean isAvailable = sc.nextBoolean();
        sc.nextLine();  // consume newline

        System.out.print("Enter Image Path (or URL): ");
        String imagePath = sc.nextLine();

        // Create a new Menu object (without setting menu_id)
        Menu menuItem = new Menu();
        menuItem.setRestaurantId(restaurantId);
        menuItem.setItemName(itemName);
        menuItem.setDescription(description);
        menuItem.setPrice(price);
        menuItem.setAvailability(isAvailable);
        menuItem.setImagePath(imagePath);

        // Use DAO to add to database
        MenuDAOimpl dao = new MenuDAOimpl();
        dao.addMenuItem(menuItem);

        System.out.println("Menu item added successfully!");
    }
}
