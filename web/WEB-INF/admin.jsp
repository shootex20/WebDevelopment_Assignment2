<%-- 
    Document   : admin
    Created on : Oct 10, 2020, 11:24:21 AM
    Author     : 813017
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <h3>Menu</h3>
        <a href="inventory" name="inventory">Inventory</a>
        <br>
        <a href="admin" name="admin">Admin</a>
        <br>
        <a href="login?logout" name="logout">Logout</a>
        <br>
        <h2>Manage Users</h2>
        <br>            

        <table style="width:30%;border:1px solid black">
         <tr style="border:1px solid black">
           <th style="border:1px solid black">Username</th>
           <th style="border:1px solid black">First Name</th>
           <th style="border:1px solid black">Last Name</th>
           <th style="border:1px solid black">Delete</th>
           <th style="border:1px solid black">Edit</th>
        <c:forEach items="${users}" var="user">
         <form method="post">
         <tr style="border:1px solid black">
           <td style="border:1px solid black">${user.username}</td>
           <td style="border:1px solid black">${user.firstName}</td>
           <td style="border:1px solid black">${user.lastName}</td>
           <td style="border:1px solid black">
            <input type="hidden" name="action" value="delete">
            <input type="submit" value="Delete">
            <input type="hidden" name="userdel" value="${user.username}"></td>
            </td>
            <td style="border:1px solid black">
            <input type="hidden" name="action" value="edit">
            <input type="submit" value="Edit">
            <input type="hidden" name="useredit" value="${user.username}"></td>
            </td>
         </tr>
         </form>
         </c:forEach>
       </table>

        <div ${showAdd}>
       <form method="post">
            <h2>Add User</h2>
            <label for="title">Username: </label>
            <input type="text" name="addusername">
            <br>
            <label for="title">Password: </label>
            <input type="text" name="addpassword">
            <br>
            <label for="title">Email:  </label>
            <input type="text" name="addemail">
            <br>
            <label for="title">First Name: </label>
            <input type="text" name="addfirstname">
            <br>
            <label for="title">Last Name: </label>
            <input type="text" name="addlastname">
            <br>
            <input type="submit" value="Save">
            <input type="hidden" name="action" value="save">
            </form>
    </div>
       <div ${showEdit}>
       <form method="post" >
            <h2>Edit User</h2>
            <label for="title">Username: </label>
            <input type="text" name="editusername">
            <br>
            <label for="title">Password: </label>
            <input type="text" name="editpassword">
            <br>
            <label for="title">Email:  </label>
            <input type="text" name="editemail">
            <br>
            <label for="title">First Name: </label>
            <input type="text" name="editfirstname">
            <br>
            <label for="title">Last Name: </label>
            <input type="text" name="editlastname">
            <br>
            <label for="active">Active User: </label>
            <select name="activeUsers" id="activeUser">
            <option value="true">True</option>
            <option value="false">False</option>
            </select>
            <br>
            <label for="title">Admin User: </label>
            <select name="activeAdmins" id="adminUser">
            <option value="true">True</option>
            <option value="false">False</option>
            </select>
            <br>
            <input type="submit" value="Save">
            <input type="hidden" name="action" value="saveInfo">
            </form>
        </div>
            <br>
           ${displayMessage}
        <br>
    </body>
</html>
