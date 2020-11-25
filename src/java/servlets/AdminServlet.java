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
               request.setAttribute("showEdit", "hidden");
               request.setAttribute("showAdd", "");

                 UserDB userDB1 = new UserDB();
            
            try {
            List<Users> users = (List<Users>) userDB1.getAll();
            request.setAttribute("users", users);
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

                getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");


        if(action.equals("delete"))
        {
            try {
                String usern = request.getParameter("userdel");
                UserDB userDB = new UserDB();
                Users user = new Users();
                user = (Users) userDB.getUser(usern);
                Users adminUser = userDB.getUser(username);
                if(!user.equals(adminUser))
                {
                userDB.delete(user);
                request.setAttribute("displayMessage", "Successfully deleted user.");
                doGet(request, response);
                }
                else
                {
                request.setAttribute("displayMessage", "You cannot delete yourself!");
               doGet(request, response);
                }
                
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("displayMessage", "Uh-oh! Something went wrong.");
               doGet(request, response);
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
                request.setAttribute("displayMessage", "Uh-oh! Something went wrong.");
               doGet(request, response);
            }
        }
        /* DOES NOT WORK RIGHT NO
        
        if(action.equals("edit"))
        {
            
        request.setAttribute("showEdit", "");
        request.setAttribute("showAdd", "hidden");
        
        String usern = request.getParameter("useredit");
        UserDB userDB = new UserDB();
        Users user = (Users) userDB.getUser(usern);
        
        request.setAttribute("editusername", user.getUsername());
        request.setAttribute("editpassword", user.getUsername());        
        request.setAttribute("editemail", user.getUsername());        
        request.setAttribute("editfirstname", user.getUsername());        
        request.setAttribute("editlastname", user.getUsername());
        
        if(user.getActive() == true)
        {
        request.setAttribute("activeUsers", "true"); 
        }
        else
        {
        request.setAttribute("activeUsers", "false"); 
        }
        if(user.getIsAdmin() == true)
        {
        request.setAttribute("activeAdmins", "true"); 
        }
        else
        {
        request.setAttribute("activeAdmins", "false"); 
        }
      
        
        
        
        
        
        
        
        String editUser = request.getParameter("editusername");
        String editPassword = request.getParameter("editpassword");
        String editemail = request.getParameter("editemail");
        String editfirstname = request.getParameter("editfirstname");
        String editlastname = request.getParameter("editlastname");        
        String editisactive = request.getParameter("activeUsers");
        String editisadmin = request.getParameter("activeAdmins");

        
        }
        */
        
       
    
        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
    }

}
