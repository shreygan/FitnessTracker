<%-- 
    EXERCISE
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="JavaClasses.CreateExercises"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <%
            // Creating the CreateExercises class
            CreateExercises e = new CreateExercises();
            
            // If this is the first time the user is visiting this page, set some Attributes
            if (session.getAttribute("Iteration2").toString().equals("1")) {
                session.setAttribute("Exercise", request.getParameter("value"));
                session.setAttribute("ExerciseID", request.getParameter("id"));
            }
        %>
        
        <!--Printing the exercise in the title-->
        <title>Exercise Tracker - <% out.println(request.getParameter("value")); %> </title>
        
        <!--Setting the icon for this page-->
        <link rel="icon" href='Images/Icon.png'>
        
        <!--Importing the CSS Sheet for this page-->
        <link href='CSS/Exercise.css' rel='stylesheet' type='text/css'/>
        
    </head>
    
    <body>        
        
        <!--Creating the options at the top of the page to switch between pages--> 
        <div class="topnav">
            <a href="index.jsp">Log Out</a>
            <a href="Settings.jsp">Settings</a>
            <a href="CreateExercises.jsp">Create Exercises</a>
            <a href="Calendar.jsp">Calendar</a>
            <a href="Main.jsp">Main Menu</a>
        </div>
        
        <br/><br/><br/><br/>
        
        <!--The top message displayed on the page-->
        <h1 id="topMessage"> <% out.println(request.getParameter("value")); %> </h1>
                
        <form action="Exercise.jsp"> 
            
            <div class="change"> <input type="submit" value="Change Date and Time" name="changeDateTime"/> </div> 
            <div class="change"> <input type="submit" value="Change Frequency" name="changeFrequency"/> </div> 
            <div class="change"> <input type="submit" value="Remove Exercise" name="removeExercise"/> </div> 
            <div class="change"> <input type="submit" value="Finish Exercise" name="finishExercise"/> </div> 
        
        </form>
        
        <%
            // Seeing what button the user pressed, and setting attributes and redirecting the user accordingly
            if (request.getParameter("changeDateTime") != null) {
                session.setAttribute("ModifyValue", "DateTime");
                response.sendRedirect("EditExercise.jsp"); 
            } else if (request.getParameter("changeFrequency") != null) {
                session.setAttribute("ModifyValue", "Frequency");
                response.sendRedirect("EditExercise.jsp"); 
            } else if (request.getParameter("removeExercise") != null) {
                session.setAttribute("ModifyValue", "Remove");
                response.sendRedirect("EditExercise.jsp"); 
            } else if (request.getParameter("finishExercise") != null) {
                session.setAttribute("ModifyValue", "Complete");
                response.sendRedirect("EditExercise.jsp"); 
            }
            
            // Setting the session Attribute, to indicate that the user has already visited this page
            // to make sure data isn't printed/set repeatedly
            session.setAttribute("Iteration2", 2);
        %>
        
        <!--Page breaks to make sure that the background fits nicely and isn't squished-->
        <br/><br/><br/><br/><br/>
        <br/><br/><br/><br/><br/>
        <br/><br/><br/><br/><br/>
        <br/>
        
    </body>
</html>
