<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - FoodApp</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom Style -->
    <style>
        body {
            background: linear-gradient(to right, #ff4d4d, #ff9966);
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            font-family: 'Segoe UI', sans-serif;
        }

        .card {
            border: none;
            border-radius: 10px;
            box-shadow: 0px 10px 20px rgba(0, 0, 0, 0.15);
        }

        .card-body {
            padding: 40px;
        }

        .form-control {
            border-radius: 7px;
        }

        .btn-primary {
            background-color: #ff4d4d;
            border: none;
            font-weight: 500;
        }

        .btn-primary:hover {
            background-color: #e03a3a;
        }

        .text-muted a {
            color: #ff4d4d;
            text-decoration: none;
        }

        .text-muted a:hover {
            text-decoration: underline;
        }

        .login-heading {
            font-weight: bold;
            color: #444;
            font-size: 28px;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-5">
            <div class="card shadow-lg">
                <div class="card-body">
                    <h3 class="text-center login-heading mb-4">Welcome Back</h3>

                    <% if (request.getParameter("error") != null) { %>
                        <div class="alert alert-danger text-center">
                            <%= request.getParameter("error") %>
                        </div>
                    <% } %>

                    <form method="post" action="LoginServlet">
                        <div class="mb-3">
                            <label class="form-label">Username</label>
                            <input type="text" name="user_name" class="form-control" placeholder="Enter your username" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Password</label>
                            <input type="password" name="password" class="form-control" placeholder="Enter your password" required>
                        </div>

                        <div class="d-grid mb-3">
                            <button type="submit" class="btn btn-primary btn-block">Login</button>
                        </div>

                        <div class="text-center text-muted small">
                            <p>New to FoodApp? <a href="Register.jsp">Create an account</a></p>
                        </div>

                        <div class="text-center text-muted small">
                            <p>Forgot your password? <a href="#">Reset it here</a></p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
