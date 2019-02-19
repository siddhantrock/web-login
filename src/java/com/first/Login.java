package com.first;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

public class Login extends HttpServlet 
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) 
        {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Login</title>");            
            out.println("</head>");
            out.println("<body>");
           
            out.println("<center>");
            out.println("<form action=\"Login\" method=\"post\">");
            
            out.println("Enter username <input type=\"text\" name=\"username\">");
            out.println("<br/>");
            out.println("Enter password <input type=\"password\" name=\"password\">");
            out.println("<br/>");
            out.println("<input type=\"submit\" name=\"check\" value=\"Login\">");
            out.println("<input type=\"submit\" name=\"check\" value=\"Home\">");
            
            out.println("</form>");
            out.println("</center>");
            
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    protected void processRequest1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String type=request.getParameter("check");
        if(type.equals("Home"))
        {
            response.sendRedirect("index.jsp");
        }
        else
        {
            String username=request.getParameter("username");
            String password=request.getParameter("password");
        
            try
            {
               Class.forName("com.mysql.cj.jdbc.Driver");
            } 
            catch (ClassNotFoundException ex) 
            {
               Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/weblogin","root","siddhu1234@#"))
            {
               Statement st=con.createStatement();
               ResultSet rs=st.executeQuery("select * from first where username='" + username + "' and password='" + password + "'");
            
               if(rs.next())
               {
                  HttpSession session=request.getSession();
                  session.setAttribute("username", username);
                  session.setAttribute("password", password);
                
                  response.sendRedirect("UserInfo.jsp");
               }
               else
               {
                  response.sendRedirect("Login");
               }
            } 
            catch (SQLException ex) 
            {
              Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        processRequest1(request, response);
    }

    @Override
    public String getServletInfo() 
    {
        return "Short description";
    }

}
