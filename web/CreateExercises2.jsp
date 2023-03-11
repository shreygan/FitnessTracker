<%-- 
    CREATE EXERCISES 2
--%>

<%@page import="JavaClasses.User"%>
<%@page import="JavaClasses.CreateExercises"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <% 
            CreateExercises e = new CreateExercises(); 
            
            User u = new User();
        %>
        
        <title>Exercise Tracker - <% out.println(session.getAttribute("Exercise").toString()); %> </title>
        
        <link rel="icon" href='Images/Icon.png'>
        
        <link href='CSS/CreateExercises2.css' rel='stylesheet' type='text/css'/>
                
    </head>
    
    <body>
        <!--top page switcher--> 
        <div class="topnav">
            <a href="index.jsp">Log Out</a>
            <a href="Settings.jsp">Settings</a>
            <a href="CreateExercises.jsp">Create Exercises</a>
            <a href="Calendar.jsp">Calendar</a>
            <a href="Main.jsp">Main Menu</a>
        </div>
        
        <br/><br/><br/><br/>
        
        <h1 id="topMessage"><% out.println(session.getAttribute("Exercise").toString()); %></h1>   
        
        <form action="CreateExercises2.jsp">
            <%
                // If the date of the exercise has past already, prompt the user for information of the exercise
                if (!e.isDatePast()) {
                    // Printing what information should be asked based on the exercise
                    if (e.getEAspects().contains("distance")) {
                        %> 
                            <div class = "inputBox"> <p class="input">Distance</p> <input type="number" name="Distance" oninput="this.value = Math.abs(this.value)"/> 
                        <% 
                            out.println(u.getMeasurement(session.getAttribute("Username").toString(), "distance"));
                        %> </div> <%
                    } 
                    if (e.getEAspects().contains("duration")) {
                        %> <div class = "inputBox"> <p class="input">Duration</p>  <input type="number" name="Duration" oninput="this.value = Math.abs(this.value)"/> min </div> <%
                    } 
                    if (e.getEAspects().contains("weight")) {
                        %> <div class = "inputBox"> <p class="input">Weight</p>  <input type="number" name="Weight" oninput="this.value = Math.abs(this.value)"/>
                        <% 
                            out.println(u.getMeasurement(session.getAttribute("Username").toString(), "weight"));
                        %> </div> <%
                    } 
                    if (e.getEAspects().contains("reps")) {
                        %> <div class = "inputBox"> <p class="input">Reps</p>  <input type="number" name="Reps" oninput="this.value = Math.abs(this.value)"/> </div> <%
                    } 
                    if (e.getEAspects().contains("sets")) {
                        %> <div class = "inputBox"> <p class="input">Sets</p>  <input type="number" name="Sets" oninput="this.value = Math.abs(this.value)"/> </div> <%
                    } 
                    if (e.getEAspects().contains("typeOfWL")) {
                        %> <div class = "inputBox"> <p class="input">Type of Weightlifting</p>  <input type="text" name="Weightlifting"/> </div> <%
                    } 
                    if (e.getEAspects().contains("typeOfSW")) {
                        %> <div class = "inputBox"> <p class="input">Type of Swimming</p>  <input type="text" name="Swimming"/> </div> <%
                    } 
                    if (e.getEAspects().contains("typeOfC")) {
                        %> <div class = "inputBox"> <p class="input">Type of Cycling</p>  <input type="text" name="Cycling"/> </div> <%
                    } 
                    if (e.getEAspects().contains("typeOfMA")) {
                        %> <div class = "inputBox"> <p class="input">Type of Martial Arts</p>  <input type="text" name="Martial Arts"/> </div> <%
                    }   
                }
            %>
                        
            <div class = "inputBox"> <p class="input">Extra Notes</p> <textarea id="Notes" name="Notes" rows="10" cols="30"></textarea>
            
            <div id="inputButton"> <input type="submit" value="Next" name = "submit"/> </div>               
                        
            <%                
                // Seeing if the user clicked submit, and if the date has past already
                if (request.getParameter("submit") != null && !e.isDatePast()) {
                    
                    // If checkData returns 0, that means there is no errors
                    int check = e.checkData(request.getParameter("Distance"), request.getParameter("Duration"), request.getParameter("Weight"), 
                            request.getParameter("Reps"), request.getParameter("Sets"), request.getParameter("Weightlifting"), 
                            request.getParameter("Swimming"), request.getParameter("Cycling"), request.getParameter("Martial Arts"));
                    
                    // If checkData returns 3, there is an error
                    if (check == 3) {
                        %> <div class="error"> <% out.println(e.getError3()); %> </div> <%
                    } else {
                        // Adding the data entered by the user into the database, based on what the exercise is
                        if (e.getExercise().equals("Running") || e.getExercise().equals("Walking") || e.getExercise().equals("Hiking")) {
                            e.setData(Integer.parseInt(request.getParameter("Distance")), Integer.parseInt(request.getParameter("Duration")),
                                    request.getParameter("Notes"));
                            %> <div class="error"> <p>Exercise Added</p> </div> <%
                            response.sendRedirect("Main.jsp");
                        } else if (e.getExercise().equals("Swimming")) {
                            e.setData(Integer.parseInt(request.getParameter("Distance")), Integer.parseInt(request.getParameter("Duration")),
                                    request.getParameter("TypeOfSW"), request.getParameter("Notes"));
                            %> <div class="error"> <p>Exercise Added</p> </div> <%
                            response.sendRedirect("Main.jsp");
                        } else if (e.getExercise().equals("Weightlifting")) {
                            e.setData(Integer.parseInt(request.getParameter("Reps")), Integer.parseInt(request.getParameter("Sets")),
                                    Integer.parseInt(request.getParameter("Weight")), request.getParameter("typeOfWL"), request.getParameter("Notes"));
                            %> <div class="error"> <p>Exercise Added</p> </div> <%
                            response.sendRedirect("Main.jsp");
                        } else if (e.getExercise().equals("Cycling")) {
                            e.setData(Integer.parseInt(request.getParameter("Distance")), Integer.parseInt(request.getParameter("Duration")),
                                    request.getParameter("typeOfC"), request.getParameter("Notes"), 1);
                            %> <div class="error"> <p>Exercise Added</p> </div> <%
                            response.sendRedirect("Main.jsp");
                        } else if (e.getExercise().equals("Martial Arts")) {
                            e.setData(Integer.parseInt(request.getParameter("Duration")), request.getParameter("typeOfMA"), request.getParameter("Notes"));
                            %> <div class="error"> <p>Exercise Added</p> </div> <%
                            response.sendRedirect("Main.jsp");
                        } else if (e.getExercise().equals("Other")) {
                            e.setData(Integer.parseInt(request.getParameter("Distance")), Integer.parseInt(request.getParameter("Duration")),
                                    Integer.parseInt(request.getParameter("Weight")), Integer.parseInt(request.getParameter("Reps")),
                                    Integer.parseInt(request.getParameter("Sets")), request.getParameter("Notes"));
                            %> <div class="error"> <p>Exercise Added</p> </div> <%
                            response.sendRedirect("Main.jsp");
                        } else {
                            e.setData(Integer.parseInt(request.getParameter("Duration")), request.getParameter("Notes"));
                            %> <div class="error"> <p>Exercise Added</p> </div> <%
                            response.sendRedirect("Main.jsp");
                        }
                    }
                    
                // If the user clicked submit, but the date is in the future
                } else if (request.getParameter("submit") != null && e.isDatePast()) {
                    if (request.getParameter("Notes") != null) {
                        
                        // Set the notes for the exercise that the user entered, as there is no other data to collect
                        e.setData(request.getParameter("Notes"));
                        
                        response.sendRedirect("Main.jsp");
                    }
                }
            %>
        </form>
    </body>
</html>
