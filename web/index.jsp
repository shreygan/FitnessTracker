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

        <!--Setting the icon for this page-->
        <link rel="icon" href='Images/Icon.png'>
        
        <!--Importing the CSS Sheet for this page-->
        <link href='CSS/Index.css' rel='stylesheet' type='text/css'/>   
        
    </head>
    
    <body>        
              
        
        <!--Creating the options at the top of the page to switch between pages--> 
        <div class="topnav">
            <a href="NewAccount.jsp">Create an Account</a>
            <a href="index.jsp">Login</a>
            
        </div>
        
        <!--Placing the logo in the top left corner of the page-->
        <div> <img id="image" alt="Logo" src='Images/Logo.png'> </div>
        
        <br/><br/><br/>

        <!--Including the header page, which says the Welcome message-->
        <jsp:include page="Header.html"/>
        
        <br/>
        
        <form action="index.jsp">
                        
            <div class = "inputBox"> <p  class = "input">Username</p>  <input type="text" name="User"/> </div>
            <br/><br/><br/><br/><br/>
            <div class = "inputBox"> <p class = "input">Password</p>  <input type="password" name="Pass"/> </div> 
                        
            <br/><br/>

            <%          
                // Creating the user class
                User u = new User();
                
                // Seeing if the user has pressed submit
                if (request.getParameter("submit") != null) {

                    // Setting the username and password that the user entered into their respective variables
                    String User = request.getParameter("User");
                    String Pass = request.getParameter("Pass");

                    // Calling the checkUser Method in User to see if the user entered valid information
                    int check = u.checkUser(User, Pass);

                    // If the checkUser method returns 0, that means that they entered the correct informatiom
                    if (check == 0) {
                        // Setting the user's username in a session attribute, for use in later pages
                        session.setAttribute("Username", User);
                        
                        // Sets the session attribute for iteration to 1 (first time), for use in later pages
                        // to see if this is the first time the user is visiting the main page, and to print 
                        // details accordingly
                        session.setAttribute("Iteration", 1);
                        
                        // Sending the user to the main page
                        response.sendRedirect("Main.jsp");
                        %>  <%
                        
                    // If checkUser returns 6, there is an error
                    } else if (check == 6) {
                        // Get error #6 from User, and printing it to show the user
                        %>  <div class="error"> <% out.println(u.getError6()); %> </div> <%
                    }
                }
            %>
            
            <!--The submit button-->
            <div id="inputButton"> <input type="submit" value="Enter" name = "submit"/> </div>     
            
        </form>
        
            <!--Page breaks to make sure that the background fits nicely and isn't squished-->
            <br/><br/>
            <br/><br/>
            <br/><br/>
            <br/><br/>
            <br/><br/>
            <br/><br/>
        </body>
</html>