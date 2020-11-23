<%-- 
    Document   : inventory
    Created on : Oct 10, 2020, 11:24:35 AM
    Author     : 813017
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory Page</title>
    </head>
    <body>
        <h1>Home Inventory</h1>
        <br>
        <br>
        <h4>Menu</h4>
        <a href="inventory" name="inventory">Inventory</a>
        <br>
        <a href="admin" name="admin">Admin</a>
        <br>
        <a href="login?logout" name="logout">Logout</a>
        <br>
        <h2>Inventory for ${username}.</h2>
        <form method="post">
        <table style="width:30%;border:1px solid black">
         <tr style="border:1px solid black">
           <th style="border:1px solid black">Category</th>
           <th style="border:1px solid black">Name</th>
           <th style="border:1px solid black">Price</th>
           <th style="border:1px solid black"></th>
        <c:forEach items="${homeitems}" var="homeitem">
         <tr style="border:1px solid black">
           <td style="border:1px solid black">${homeitem.category.categoryName}</td>
           <td style="border:1px solid black">${homeitem.itemName}</td>
           <td style="border:1px solid black">$${homeitem.price}</td>
           <td style="border:1px solid black">
               <input type="submit" value="Delete">
            <input type="hidden" name="action" value="delete">
           <input type="hidden" name="itemID" value="${homeitem.itemID}"></td>
         </tr>
         </c:forEach>
       </table>
        <br>
        <h3>Add Item</h3>
        <br>
        <label for="category">Category: </label>
        <select name="category" id="category">
        <option value="kitchen">kitchen</option>
        <option value="bathroom">bathroom</option>
        <option value="living room">living room</option>
        <option value="basement">basement</option>
        <option value="bedroom">bedroom</option>
        <option value="garage">garage</option>
        <option value="office">office</option>
        <option value="utility room">utility room</option>
        <option value="storage">storage</option>
        <option value="other">other</option>
        </select>
        <br>
        <label for="title">Item Name: </label>
        <input type="text" name="itemnames">
        <br>
        <label for="title">Price: </label>
        <input type="text" name="itemprice">
        <br>
        <input type="hidden" name="action" value="add">
        <input type="submit" name="add" value="Add">
        <br>
        <br>
        </form>
        <p>${message}</p>
        <p>${total}</p>
        <br>
    </body>
</html>
