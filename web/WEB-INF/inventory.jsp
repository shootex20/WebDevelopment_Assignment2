<%-- 
    Document   : inventory
    Created on : Oct 10, 2020, 11:24:35 AM
    Author     : 813017
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory Page</title>
    </head>
    <body>
        <h1>Home Inventory for ${username}.</h1>
        <br>
        <h3>Add Item</h3>
        <br>
        <form method="post">
        <label for="category">Category: </label>
        <select name="category" id="category">
        <option value="bedroom">bedroom</option>
        <option value="garage">garage</option>
        <option value="kitchen">kitchen</option>
        <option value="living">living room</option>
        </select>
        <br>
        <label for="title">Item Name: </label>
        <input type="text" name="itemnames">
        <br>
        <label for="title">Price: </label>
        <input type="text" name="itemprice">
        <br>
        <input type="submit" name="add" value="Add">
        <br>
        <br>
        </form>
        <p>${message}</p>
        <p>${total}</p>
        <br>
        <a href="login?logout" name="logout">Logout</a>
    </body>
</html>
