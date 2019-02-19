<%-- 
    Document   : UserInfo
    Created on : Feb 18, 2019, 10:49:02 PM
    Author     : usre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Home</title>
    </head>
    <body>
        
        <%!
            Connection con;
            ResultSet rs;
            Statement st;
            public void jspInit()
           {
              try
              {
                 Class.forName("com.mysql.cj.jdbc.Driver");
              }
              catch(Exception e)
              {

              }
           }

         %>
         
        <%@page import="java.sql.*" %>
        
        <%
            String username=(String)session.getAttribute("username");
            String password=(String)session.getAttribute("password");
            
            try
            {
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/weblogin","root","siddhu1234@#");
            
                st=con.createStatement();
                rs=st.executeQuery("select * from first where username='" + username + "' and password='" + password + "'");
                
                if(rs.next())
                {
                    
                }
                else
                {
                    response.sendRedirect("Login");
                }
            }
            catch(Exception e)
            {
                
            }
        %>
        
    <center>
        <form action="Logout">
            
            Name : <%= rs.getString("name") %>
            <br/>
            
            <input type="submit" value="Logout">
            
        </form>
    </center>
    </body>
</html>
