<%-- 
    Document   : admin
    Created on : Oct 10, 2020, 11:24:21 AM
    Author     : 813017
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="#">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrator Page</title>
    </head>
    <body>
        <h1>Home Inventory</h1>
        <br>
        <h3>Admin Summary</h3>
        <br>
        <p>${displayTotal}</p>
        <br>
        <a href="login?logout" value="logout" name="logout">Logout</a>
    </body>
</html>
