/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 813017
 */
@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");
        
        if (username == null) {
            session.setAttribute("displayMessage", "Please Login");
            response.sendRedirect(response.encodeRedirectURL("login"));
        }
        else

        if (username.equals("admin")) {
            //Reads in items value
            String hiPath = getServletContext().getRealPath("/WEB-INF/homeitems.txt");

            BufferedReader br = new BufferedReader(new FileReader(new File(hiPath)));

            int totalPrice = 0;
            String line;
            int temp = 0;
            int lastInt = 0;
            int highestVal = 0;
            String ownername = "", itemname = "";

            while ((line = br.readLine()) != null) {
                //Delimits and splits into array.
                String[] part = line.split(",");
                String name = part[0];
                String categ = part[1];
                String item = part[2];
                String itemPrice = part[3];

                try {
                    temp = Integer.parseInt(itemPrice);
                    totalPrice = totalPrice + temp;
                    if (temp > lastInt) {
                        lastInt = temp;
                        highestVal = temp;
                        ownername = name;
                        itemname = item;
                    }
                } catch (InputMismatchException | NumberFormatException e) {
                }

            }

            br.close();

            String info = "Total value in inventory: $" + totalPrice + ". Most expensive item is " + itemname + " at $" + highestVal + " owned by " + ownername;

            //Gives total inventory.
            request.setAttribute("displayTotal", info);

            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
        } else {
            response.sendRedirect("inventory");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
