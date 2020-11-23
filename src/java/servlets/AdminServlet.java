/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dataaccess.UserDB;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Users;

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
       
        UserDB usertest = new UserDB();
        
        Users admin = null;
        try
        {
        admin = usertest.getUser(username);
                } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         UserDB userDB = new UserDB();
            
            try {
            List<Users> users = (List<Users>) userDB.getAll();
            request.setAttribute("users", users);
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        if (username == null) {
            session.setAttribute("displayMessage", "Please Login");
            response.sendRedirect(response.encodeRedirectURL("login"));
        }
        
        

        if (username.equals(admin.getUsername()) && admin.getIsAdmin() == true){
            
           getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
       } else {
        response.sendRedirect("inventory");
       }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        
         UserDB userDB1 = new UserDB();
            
            try {
            List<Users> users = (List<Users>) userDB1.getAll();
            request.setAttribute("users", users);
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }


        if(action.equals("delete"))
        {
            try {
                String usern = request.getParameter("usersdel");
                UserDB userDB = new UserDB();
                Users user = userDB.getUser(usern);
                if(usern.equals(username))
                {
                userDB.delete(user);
                request.setAttribute("displayMessage", "Successfully deleted user.");
                doGet(request, response);
                }
                else
                {
                 request.setAttribute("displayMessage", "You cannot yourself!");
               doGet(request, response);
                }
                
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(action.equals("save"))
        {
        UserDB userDB = new UserDB();
        String addusername = request.getParameter("addusername");
        String addpassword = request.getParameter("addpassword");
        String addemail = request.getParameter("addemail");
        String addfirstname = request.getParameter("addfirstname");
        String addlastname = request.getParameter("addlastname");
        
        Users user = new Users(addusername, addpassword, addemail, addfirstname, addlastname, true, false);
        
            try {
                userDB.insert(user);
               doGet(request, response);
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
    }

}
