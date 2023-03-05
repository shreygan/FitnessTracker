<%-- 
    CALENDAR
--%>

<%@page import="JavaClasses.CreateExercises"%>
<%@page import="java.util.ArrayList"%>
<%@page import="JavaClasses.Calendar"%>
<%@page import="java.util.Scanner"%>
<%@page import="java.util.Date" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Exercise Tracker - Calendar</title>
        
        <!--Setting the icon for this page-->
        <link rel="icon" href='Images/Icon.png'>
        
        <!--Importing the CSS Sheet for this page-->
        <link href='CSS/Calendar.css' rel='stylesheet' type='text/css'/>
        
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
        
        <br/>
        
        <%            
            // Creating the Calendar class
            Calendar c = new Calendar();
            
            // Creating the CreateExercises class
            CreateExercises e = new CreateExercises();
        %>
        
        <div class="month">
            <form action="Calendar.jsp">
                <input type="submit" value="&#10094;" name = "left" class="prev"/>
                <input type="submit" value="&#10095;" name = "right" class="next"/> 
                <%
                    if (request.getParameter("left") != null) {
                        c.monthLeft();
                    } else if (request.getParameter("right") != null) {
                        c.monthRight();
                    }
                %>
            </form>
            <ul>
                <li><% out.println(c.getMonth()); %><br><span style="font-size:18px"><% out.println(c.getYear()); %></span></li>
            </ul>
        </div>

        <ul class="weekdays">
            <li>Mo</li>
            <li>Tu</li>
            <li>We</li>
            <li>Th</li>
            <li>Fr</li>
            <li>Sa</li>
            <li>Su</li>
        </ul>
            
        <%                    
            session.setAttribute("Iteration", 2);
        %>
            
        <ul class="days">
            <%                
                
                for (int i = 1; i <= (c.getDaysInMonth() + c.getFWeekday()); i++) {
                    if (i - c.getFWeekday() == c.getDate() && c.getCurrentMonth().equals(c.getMonth())) {
                        %> <li>  <div class="day"> <span class="active"> <% out.println(i - c.getFWeekday()); %> </span> <br><br> <p>
                        <% out.println(c.printExercise(session.getAttribute("Iteration").toString().equals("1"), 
                                session.getAttribute("Username").toString(), c.getYear(), c.getMonthInt(), c.getDate()).toString()); %></p> </div> </li> <%
                    } else if (i <= c.getFWeekday()){
                        %> <li> <div class="day"> <br><br> <p>
                        <% out.println(c.printExercise(session.getAttribute("Iteration").toString().equals("1"), 
                                session.getAttribute("Username").toString(), 0, 0, 0).toString()); %></p> </div> </li> <%
                    } else {
                        %> <li> <div class="day"> <% out.println(i - c.getFWeekday());%> <br><br> <p>
                        <% out.println(c.printExercise(session.getAttribute("Iteration").toString().equals("1"), 
                                session.getAttribute("Username").toString(), c.getYear(), c.getMonthInt(), i)); %></p> </div> </li> <%
                    }
                }
            %>
        </ul>
    </body>
</html>
