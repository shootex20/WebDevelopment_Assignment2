/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dataaccess.CategoriesDB;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static jdk.nashorn.internal.objects.NativeMath.round;
import models.Categories;
import models.HomeItems;
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
        
        Inventory inv = new Inventory();

        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");
        

        // HomeItems hi = new HomeItems();
        if (username == null) {
            session.setAttribute("displayMessage", "Please Login");
            response.sendRedirect(response.encodeRedirectURL("login"));
        }

            session.setAttribute("username", username);
            
            CategoriesDB catdb = new CategoriesDB();

            List<Categories> categories = null;
        try {
            categories = (List<Categories>) catdb.getAll();
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("categories", categories);

            double totalPrice = 0;
            List<HomeItems> items = null;
            try
            {
                items = (List<HomeItems>) inv.getAll(username);
            }
            catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            for(int i = 0; i < items.size(); i++)
            {
                double temp = items.get(i).getPrice();
                totalPrice = totalPrice + temp;
            }
            
            round(totalPrice, 2);

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


        String category = request.getParameter("category");
        String itemName = request.getParameter("itemnames");
        
        
        
        //Checks category List
        CategoriesDB catdb = new CategoriesDB();
        
        List<Categories> cat = null;
        
        int catNum = 0;
        
        try {
            cat = (List<Categories>) catdb.getAll();
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(int i = 0; i < cat.size(); i++)
        {
            if(cat.get(i).getCategoryName().equals(category))
            {
                catNum = cat.get(i).getCategoryID();
            }
        }

        double price = 0;

        Inventory inv = new Inventory();
        //Creates home item object.
        HomeItems hi;
        
        try {
            inv.insert(itemName, price, username);
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

            request.getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
    }

}


 
/*
        
        for(int i = 0; i < items.size(); i++)
        {

        try {
                double temp = items.get(i).getPrice();
                price = totalPrice + temp;
        } catch (InputMismatchException | NumberFormatException e) {
            request.setAttribute("message", "Invalid. Please re-enter.");
        }
        
        //Prints values
        if (price > 0) {

            hi = 
            request.setAttribute("message", "Item has been sucessfully added.");

            doGet(request, response);
        } else {
            
            request.setAttribute("message", "Invalid. Please re-enter.");
            doGet(request, response);
        }
*/