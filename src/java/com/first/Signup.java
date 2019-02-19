package com.first;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Signup extends HttpServlet 
{

    String name;
    int age;
    String username;
    String password;
    String gender;
    String degree;
    String course;
    String path;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) 
        {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Signup</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<center>");
            out.println("<form action=\"Signup\" method=\"post\">");
            out.println("Enter name <input type=\"text\" name=\"name\">");
            out.println("<br/>");
            out.println("Enter age <input type=\"text\" name=\"age\">");
            out.println("<br/>");
            out.println("Select gender <input type=\"checkbox\" name=\"ch\" value=\"male\"> male");
            out.println("<input type=\"checkbox\" name=\"ch\" value=\"female\"> female");
            out.println("<br/>");
            out.println("Select degree <select name=\"sel\">");
            out.println("<option value=\"U.G.\">U.G.</option>");
            out.println("<option value=\"P.G.\">P.G.</option>");
            out.println("</select>");
            out.println("<br/>");
            out.println("Select course <select name=\"sel1\">");
            out.println("<option value=\"Bsc IT\">Bsc IT</option>");
            out.println("<option value=\"BCA\">BCA IT</option>");
            out.println("<option value=\"B.Tech\">B.Tech IT</option>");
            out.println("<option value=\"Msc IT\">Msc IT</option>");
            out.println("<option value=\"MCA\">MCA</option>");
            out.println("<option value=\"M.Tech\">M.Tech</option>");
            out.println("</select>");
            out.println("<br/>");
            out.println("Select image <input type=\"text\" name=\"fil\">");
            out.println("<br/>");
            out.println("Enter username <input type=\"text\" name=\"username\">");
            out.println("<br/>");
            out.println("Enter password <input type=\"password\" name=\"password\">");
            out.println("<br/>");
            out.println("<input type=\"submit\" name=\"check\" value=\"signup\">");
            out.println("<input type=\"submit\" name=\"check\" value=\"home\">");
            out.println("</form>");
            out.println("</center>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    protected void processRequest1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String type=request.getParameter("check");
        if(type.equals("home"))
        {
            response.sendRedirect("index.jsp");
        }
        else
        {
            name=request.getParameter("name");
           age=Integer.parseInt(request.getParameter("age"));
           gender=request.getParameter("ch");
           degree=request.getParameter("sel");
           course=request.getParameter("sel1");
           path=request.getParameter("fil");
           username=request.getParameter("username");
           password=request.getParameter("password");
        
           try
           {
              Class.forName("com.mysql.cj.jdbc.Driver");
           } 
           catch (ClassNotFoundException ex) 
           {
              Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, null, ex);
           }
        
           try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/weblogin","root","siddhu1234@#"))
           {
              PreparedStatement pst=con.prepareStatement("insert into first values(?,?,?,?,?,?,?,?)");
              pst.setString(1,name);
              pst.setString(2,username);
              pst.setString(3,password);
              pst.setInt(4, age);
              pst.setString(5,gender);
              pst.setString(6,degree);
              pst.setString(7,course);
            
              FileInputStream fis=new FileInputStream(path);
              pst.setBinaryStream(8,fis);
            
              int i=pst.executeUpdate();
            
              if(i>0)
              {
                 response.sendRedirect("index.jsp");
              }
              else
              {
                  response.sendRedirect("Signup");
              }
            
           } 
           catch (SQLException ex) 
           {
              Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        processRequest1(request, response);
    }

    @Override
    public String getServletInfo() 
    {
        return "Short description";
    }

}
