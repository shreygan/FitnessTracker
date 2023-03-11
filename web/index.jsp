<%-- 
    
    LOGIN / INDEX

--%>

<%@ page import="java.util.Calendar, java.sql.Date"%>
<%@ page import="JavaClasses.User"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Exercise Tracker - Log In</title>

        <link rel="icon" href='Images/Icon.png'>
        
        <link href='CSS/Index.css' rel='stylesheet' type='text/css'/>   
        
    </head>
    
    <body>        
        <!--top page switcher--> 
        <div class="topnav">
            <a href="NewAccount.jsp">Create an Account</a>
            <a href="index.jsp">Login</a>
            
        </div>
        
        <div> <img id="image" alt="Logo" src='Images/Logo.png'> </div>
        
        <br/><br/><br/>

        <jsp:include page="Header.html"/>
        
        <br/>
        
        <form action="index.jsp">
                        
            <div class = "inputBox"> <p  class = "input">Username</p>  <input type="text" name="User"/> </div>
            <br/><br/><br/><br/><br/>
            <div class = "inputBox"> <p class = "input">Password</p>  <input type="password" name="Pass"/> </div> 
                        
            <br/><br/>

            <%          
                User u = new User();
                
                if (request.getParameter("submit") != null) {
                    String User = request.getParameter("User");
                    String Pass = request.getParameter("Pass");

                    int check = u.checkUser(User, Pass);

                    if (check == 0) {
                        session.setAttribute("Username", User);
                        
                        // Sets the session attribute for iteration to 1 (first time), for use in later pages
                        // to see if this is the first time the user is visiting the main page, and to print 
                        // details accordingly
                        session.setAttribute("Iteration", 1);
                        
                        response.sendRedirect("Main.jsp");
                        %>  <%
                        
                    // If checkUser returns 6, there is an error
                    } else if (check == 6) {
                        %>  <div class="error"> <% out.println(u.getError6()); %> </div> <%
                    }
                }
            %>
            
            <div id="inputButton"> <input type="submit" value="Enter" name = "submit"/> </div>     
            
        </form>        
            <br/><br/>
            <br/><br/>
            <br/><br/>
            <br/><br/>
            <br/><br/>
            <br/><br/>
        </body>
</html>