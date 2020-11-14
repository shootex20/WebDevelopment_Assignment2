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
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();

        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String logout = request.getParameter("logout");

        if (logout != null) {
            session.invalidate();
            request.setAttribute("displayMessage", "Logged out.");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } 
        else if (session.getAttribute("username") != null) {
            response.sendRedirect(response.encodeRedirectURL("inventory"));
        } else if (username == null || password == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
     
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Sets session
        HttpSession session = request.getSession();

        //Sets path to login files.
        String path = getServletContext().getRealPath("/WEB-INF/users.txt");

        String password = request.getParameter("password");
        String username = request.getParameter("username");

        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        String line;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");

            String user = parts[0];
            String pass = parts[1];

            /*
            if (username.equals("admin") && password.equals("password")) {
                session.setAttribute("username", username);
                response.sendRedirect(response.encodeRedirectURL("admin"));
            } else*/ 
            if (user.equals(username) && pass.equals(password)) {
                
                if(username.equals("admin") && password.equals("password"))
                        {
                        session.setAttribute("username", username);
                         response.sendRedirect(response.encodeRedirectURL("admin"));
                        }
                else
                {
               session.setAttribute("username", username);
              response.sendRedirect(response.encodeRedirectURL("inventory"));
                }

            } 
            else if (!user.equals(username) && !pass.equals(password)) {
                request.setAttribute("displayMessage", "Invalid username or password");
               request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

            }
        }


    }

}
