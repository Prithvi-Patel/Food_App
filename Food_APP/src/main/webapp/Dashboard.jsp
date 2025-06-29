<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%
    String user = (String) session.getAttribute("user_name");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
    <h2>Welcome <%= user %>!</h2>
    <p>You are logged in with elevated privileges.</p>
</body>
</html>
