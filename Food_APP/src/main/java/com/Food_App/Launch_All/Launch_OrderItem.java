package com.Food_App.Launch_All;

import java.util.Scanner;

import com.Food_App.DAOimpl.OrderItemDAOImpl;
import com.Food_App.Models.OrderItem;

public class Launch_OrderItem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input from user
        System.out.print("Enter Order ID: ");
        int orderId = sc.nextInt();

        System.out.print("Enter Menu ID: ");
        int menuId = sc.nextInt();

        System.out.print("Enter Quantity: ");
        int quantity = sc.nextInt();

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        // Creating object and setting values
        OrderItem orderItem = new OrderItem(orderId, menuId, quantity, price);

        // DAO call to insert
        OrderItemDAOImpl dao = new OrderItemDAOImpl();
        dao.addOrderItem(orderItem);

        System.out.println("Order item added successfully!");

        sc.close();
    }
}
