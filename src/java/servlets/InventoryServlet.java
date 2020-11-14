/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Item;
import services.Inventory;

/**
 *
 * @author Chelsey Coughlin
 */
@WebServlet(name = "InventoryServlet", urlPatterns = {"/InventoryServlet"})
public class InventoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");
        

        // Item hi = new Item();
        if (username == null) {
            session.setAttribute("displayMessage", "Please Login");
            response.sendRedirect(response.encodeRedirectURL("login"));
        }

            session.setAttribute("username", username);

            //Reads in items value
            String hiPath = getServletContext().getRealPath("/WEB-INF/homeitems.txt");


            int totalPrice = 0;
            Inventory inventory = new Inventory();


            String info = "Total value in inventory: $" + totalPrice;

            //Gives total inventory.
            request.setAttribute("total", info);

            request.getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);

        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Get the user name.
        HttpSession session = request.getSession();
        //Get the user name of the session.
        String username = (String) session.getAttribute("username");

        //Sets path to users files.
        String path = getServletContext().getRealPath("/WEB-INF/homeitems.txt");

        String category = request.getParameter("category");
        String itemName = request.getParameter("itemnames");

        int price = 0;

        //Creates home item object.
        Item hi;
        

        //Initializing value...
        try {
            String tempPrice = request.getParameter("itemprice");
            price = Integer.parseInt(tempPrice);
        } catch (InputMismatchException | NumberFormatException e) {
            request.setAttribute("message", "Invalid. Please re-enter.");
        }
        
        //Prints values
        if (price > 0) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));

            //hi = new Item(username, category, itemName, price);
            //Adds to the end of file
           // writer.append(hi.toString());
            //Creates a new line
            writer.append(System.lineSeparator());
            //Closes file
            writer.close();
            request.setAttribute("message", "Item has been sucessfully added.");

            doGet(request, response);
        } else {
            
            request.setAttribute("message", "Invalid. Please re-enter.");
            doGet(request, response);
        }
    }

}
