<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%
    String user = (String) session.getAttribute("username");
    if (user == null) {
        response.sendRedirect("Login.jsp");
        return;
    }
%>
<html>
<head>
    <title>Welcome - FoodApp</title>
    <link rel="stylesheet" href="Style.css">
</head>
<body>
    <div class="form-container">
        <h2>Welcome, <%= user %> 👋</h2>
        <p>You have successfully logged in to FoodApp.</p>
        <a href="Login.jsp">Logout</a>
    </div>
</body>
</html>
