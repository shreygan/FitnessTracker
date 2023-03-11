<%-- 
    SETTINGS
--%>

<%@page import="JavaClasses.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Exercise Tracker - Settings</title>
        
        <link rel="icon" href='Images/Icon.png'>
        
        <link href='CSS/Settings.css' rel='stylesheet' type='text/css'/>
        
    </head>
    
    <body>
        
        <% 
            User u = new User(); 
        %>
        
        <!--top page switcher--> 
        <div class="topnav">
            <a href="index.jsp">Log Out</a>
            <a href="Settings.jsp">Settings</a>
            <a href="CreateExercises.jsp">Create Exercises</a>
            <a href="Calendar.jsp">Calendar</a>
            <a href="Main.jsp">Main Menu</a>
        </div>
        
        <br/><br/><br/><br/>

        <h1 id="topMessage">Settings</h1>  
        
        <form action="Settings.jsp">
            
            <div class = "inputBox"> 
                <p id="inputUnit">Default Measurement System</p> 
                <select id="System" name="SystemDropDown">
                    <option value="" disabled selected>Select System</option>
                    <option value="imperial">Imperial (Miles, etc.)</option>
                    <option value="metric">Metric (Kilometers, etc.)</option>
                </select>
            </div>
            
            <div id="inputButton"> <input type="submit" value="Submit" name = "submit"/> </div> 
            
        </form>
        
        <%
            if (request.getParameter("submit") != null) {
                // Checking if the user chose a measurement system
                if (request.getParameter("SystemDropDown") != null) {
                    u.updateMeasurement(session.getAttribute("Username").toString(), request.getParameter("SystemDropDown"));
                    
                    %> <div class="done"> <p>Set to <% out.println(u.getMeasurement(session.getAttribute("Username").toString(), "")); %></p> </div> <%
                }
            }
        %>    
            
        <form action="Settings.jsp">
            
            <h2>Change Information</h2>
            
            <div class="change"> <input type="submit" value="Change Username" name="changeUser"/> </div> 
            
            <div class="change"> <input type="submit" value="Change Password" name="changePass"/> </div> 
            
            <div class="change"> <input type="submit" value="Change Email" name="changeEmail"/> </div> 
                                    
        </form>
        
        <%
            // To see what the user is trying to change, and set session Attributes and redirect the user accordingly
            if (request.getParameter("changeUser") != null) {
                session.setAttribute("ChangeValue", "Username");
                response.sendRedirect("ChangeSettings.jsp"); 
            } else if (request.getParameter("changePass") != null) {
                session.setAttribute("ChangeValue", "Password");
                response.sendRedirect("ChangeSettings.jsp");
            } else if (request.getParameter("changeEmail") != null) {
                session.setAttribute("ChangeValue", "Email");
                response.sendRedirect("ChangeSettings.jsp");
            }
        %>
        <br/><br/>
    </body>
</html>
