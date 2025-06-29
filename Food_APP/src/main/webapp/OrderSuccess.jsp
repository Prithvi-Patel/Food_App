<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Success</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background: linear-gradient(to right, #e0f7fa, #fff);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .success-box {
            background-color: #ffffff;
            padding: 40px;
            text-align: center;
            border-radius: 10px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.1);
        }

        .success-box h1 {
            color: #4caf50;
            font-size: 30px;
            margin-bottom: 20px;
        }

        .success-box p {
            color: #555;
            font-size: 18px;
            margin-bottom: 30px;
        }

        .btn-home {
            text-decoration: none;
            padding: 12px 25px;
            background-color: #ff4d4d;
            color: white;
            font-size: 16px;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        .btn-home:hover {
            background-color: #e03a3a;
        }

        .success-icon {
            font-size: 48px;
            color: #4caf50;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>

<div class="success-box">
    <div class="success-icon">✔️</div>
    <h1>Order Placed Successfully!</h1>
    <p>Thank you for your order. Your delicious food will arrive shortly.</p>
    <a href="Home.jsp" class="btn-home">Go to Home</a>
</div>

</body>
</html>
