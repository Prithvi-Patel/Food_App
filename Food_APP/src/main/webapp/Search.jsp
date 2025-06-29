<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.Food_App.Models.Restaurant" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Restaurants</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f4f4f4;
            padding: 40px;
        }

        h2 {
            color: #ff4d4d;
        }

        form {
            margin-bottom: 30px;
        }

        input[type="text"] {
            padding: 10px;
            width: 300px;
            border-radius: 6px;
            border: 1px solid #ccc;
        }

        button {
            padding: 10px 15px;
            background-color: #ff4d4d;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }

        .card {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            margin-top: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>

    <h2>Search Restaurants</h2>

    <form action="SearchRestaurantServlet" method="get">
        <input type="text" name="query" placeholder="Enter restaurant name" required />
        <button type="submit">Search</button>
    </form>

    <%
        List<Restaurant> results = (List<Restaurant>) request.getAttribute("searchResults");
        if (results != null) {
            if (results.isEmpty()) {
    %>
        <p>No restaurants found.</p>
    <%
            } else {
                for (Restaurant restaurant : results) {
    %>
        <div class="card">
            <h3><%= restaurant.getName() %></h3>
            <p>Type: <%= restaurant.getCuisineType() %></p>
            <p>Rating: <%= restaurant.getRating() %>⭐</p>
        </div>
    <%
                }
            }
        }
    %>

</body>
</html>
