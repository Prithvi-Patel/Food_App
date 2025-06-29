<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Exciting Offers - FoodApp</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            margin: 0;
            padding: 0;
            background: #fff8f5;
        }
        .navbar {
            background-color: #ff4d4d;
            color: white;
            padding: 15px 30px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .navbar a {
            color: white;
            text-decoration: none;
            margin-left: 20px;
            font-weight: bold;
        }
        .offers-container {
            padding: 30px;
        }
        .offer {
            background: #fff0e6;
            border-left: 6px solid #ff704d;
            padding: 20px;
            margin-bottom: 20px;
            border-radius: 8px;
        }
        .offer h3 {
            margin-top: 0;
            color: #d63031;
        }
        .offer p {
            margin: 10px 0 0;
            font-size: 15px;
            color: #555;
        }
    </style>
</head>
<body>

    <div class="navbar">
        <h2>FoodApp</h2>
        <div>
            <a href="Home.jsp"><i class="fas fa-home"></i> Home</a>
            <a href="Offers.jsp"><i class="fas fa-tags"></i> Offers</a>
            <a href="Cart.jsp"><i class="fas fa-shopping-cart"></i> Cart</a>
            <a href="Login.jsp"><i class="fas fa-sign-in-alt"></i> Login</a>
        </div>
    </div>

    <div class="offers-container">
        <div class="offer">
            <h3>🔥 Flat 30% Off</h3>
            <p>Get 30% off on orders above ₹299. Use code <strong>FOOD30</strong>.</p>
        </div>
        <div class="offer">
            <h3>🥤 Free Cold Drink</h3>
            <p>Order from Pizza Hub and get a free cold drink on orders above ₹499.</p>
        </div>
        <div class="offer">
            <h3>🎉 Weekend Bonanza</h3>
            <p>Extra 20% cashback every Saturday & Sunday. No code needed!</p>
        </div>
        <div class="offer">
            <h3>🚚 Free Delivery</h3>
            <p>Enjoy free delivery on your first 3 orders. Automatically applied.</p>
        </div>
    </div>

</body>
</html>
