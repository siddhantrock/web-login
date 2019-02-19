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
        <%@page import="java.io.*" %>
        
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
            
            Age : <%= rs.getInt("age") %>
            <br/>
            
            Gender : <%= rs.getString("gender") %>
            <br/>
            
            Degree : <%= rs.getString("degree") %>
            <br/>
            
            Course : <%= rs.getString("course") %>
            <br/>
            
            <%!
                void insertFile()
                {
                    try
                    {
                      File f=new File("D:\\javaprogram\\login web\\img.jpg");
                      FileOutputStream fos=new FileOutputStream(f);
                      InputStream is=rs.getBinaryStream("pic");
                      
                      byte b1[]=new byte[5000];
                      int c;
                      
                      while((c=is.read(b1))>0)
                      {
                          fos.write(b1, 0, c);
                          fos.flush();
                      }
                    }
                    catch(IOException | SQLException e)
                    {
                        
                    }
                }
                
            %>
            
            Picture :
            <img src="D:\\javaprogram\\img.jpg" height="100" width="100">
            
            <input type="submit" value="Logout">
            
        </form>
    </center>
    </body>
</html>
