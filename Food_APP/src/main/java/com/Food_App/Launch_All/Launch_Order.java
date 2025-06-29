package com.Food_App.Launch_All;

import java.util.List;
import java.util.Scanner;

import com.Food_App.DAOimpl.OrderDAOImpl;
import com.Food_App.Models.Order;

public class Launch_Order {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OrderDAOImpl orderDao = new OrderDAOImpl();

        System.out.println("Choose Option:");
        System.out.println("1. Get orders by User ID");
        System.out.println("2. Get orders by Restaurant ID");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Enter User ID: ");
                int userId = sc.nextInt();
                List<Order> userOrders = orderDao.getOrdersByUserId(userId);
                if (userOrders.isEmpty()) {
                    System.out.println("No orders found for User ID: " + userId);
                } else {
                    System.out.println("Orders placed by User ID " + userId + ":");
                    for (Order o : userOrders) {
                        System.out.println(o);
                    }
                }
                break;

            case 2:
                System.out.print("Enter Restaurant ID: ");
                int restId = sc.nextInt();
                List<Order> restaurantOrders = (List<Order>) orderDao.getOrdersByRestaurantId(restId);
                if (restaurantOrders.isEmpty()) {
                    System.out.println("No orders found for Restaurant ID: " + restId);
                } else {
                    System.out.println("Orders placed at Restaurant ID " + restId + ":");
                    for (Order o : restaurantOrders) {
                        System.out.println(o);
                    }
                }
                break;

            default:
                System.out.println("Invalid choice!");
        }

        sc.close();
    }
}
