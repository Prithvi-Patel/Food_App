<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register - FoodApp</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom Styles -->
    <style>
        body {
            background: linear-gradient(to right, #ff4d4d, #ff9966);
            font-family: 'Segoe UI', sans-serif;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 30px;
        }

        .card {
            border: none;
            border-radius: 12px;
            box-shadow: 0 8px 20px rgba(0,0,0,0.15);
        }

        .card-body {
            padding: 40px;
        }

        .form-control {
            border-radius: 8px;
        }

        .btn-success {
            background-color: #28a745;
            border: none;
            font-weight: 500;
        }

        .btn-success:hover {
            background-color: #218838;
        }

        h3 {
            font-weight: bold;
            color: #444;
        }

        label {
            font-weight: 500;
        }

        .form-label::after {
            content: " *";
            color: red;
        }

        .optional::after {
            content: "";
        }

        .form-text a {
            color: #ff4d4d;
            text-decoration: none;
        }

        .form-text a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-10 col-lg-7">
            <div class="card">
                <div class="card-body">
                    <h3 class="text-center mb-4">Create Your FoodApp Account</h3>

                    <% if (request.getParameter("success") != null) { %>
                        <div class="alert alert-success text-center"><%= request.getParameter("success") %></div>
                    <% } %>

                    <form method="post" action="RegisterServlet">
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="form-label">Name</label>
                                <input type="text" name="name" class="form-control" placeholder="Your full name" required>
                            </div>

                            <div class="col-md-6 mb-3">
                                <label class="form-label">Username</label>
                                <input type="text" name="user_name" class="form-control" placeholder="Choose a username" required>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Password</label>
                            <input type="password" name="password" class="form-control" placeholder="Create a password" required>
                        </div>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="form-label">Email</label>
                                <input type="email" name="email" class="form-control" placeholder="example@email.com" required>
                            </div>

                            <div class="col-md-6 mb-3">
                                <label class="form-label">Phone Number</label>
                                <input type="text" name="phonenumber" class="form-control" placeholder="e.g., 9876543210" required>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label class="form-label optional">Address</label>
                            <input type="text" name="address" class="form-control" placeholder="Your full address">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Role</label>
                            <select name="role" class="form-control" required>
                                <option value="">-- Select Role --</option>
                                <option value="customer">Customer</option>
                                <option value="restaurant_admin">Restaurant Admin</option>
                                <option value="delivery_agent">Delivery Agent</option>
                                <option value="super_admin">Super Admin</option>
                            </select>
                        </div>

                        <div class="d-grid mt-4">
                            <button type="submit" class="btn btn-success btn-block">Register</button>
                        </div>

                        <div class="form-text text-center mt-3">
                            Already have an account? <a href="Login.jsp">Login here</a>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
