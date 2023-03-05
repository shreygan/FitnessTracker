<%-- 
    EDIT EXERCISE
--%>

<%@page import="JavaClasses.User"%>
<%@page import="JavaClasses.CreateExercises"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <!--Printing the exercise in the title-->
        <title>Exercise Tracker - <% out.println(session.getAttribute("Exercise").toString()); %> </title>
        
        <!--Setting the icon for this page-->
        <link rel="icon" href='Images/Icon.png'>
        
        <!--Importing the CSS Sheet for this page-->
        <link href='CSS/EditExercise.css' rel='stylesheet' type='text/css'/>
        
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
        
        <%
            // Creating the CreateExercises class
            CreateExercises e = new CreateExercises();
            
            // Creating the User class
            User u = new User();            
        %>
        
        <!--The top message displayed on the page-->
        <h1 id="topMessage"><% out.println(session.getAttribute("Exercise").toString()); %> </h1>
        
        <form action="EditExercise.jsp">
        
        <%
            // Seeing what the user wants to change, and presenting the page based on that
            if (session.getAttribute("ModifyValue").equals("DateTime")) {
                %>
                    <div class = "inputBox"> <p id="inputDateTime">Date &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
                            &nbsp; &nbsp; &nbsp;Time</p>
                        <input type="date" name="date"/>        <input type="time" name="time"/> </div>
                    
                    <p class="current">Current Date: <% out.println(e.getDate(Integer.parseInt(session.getAttribute("ExerciseID").toString()))); %> </p>
                
                    <p class="current">Current Time: <% out.println(e.getTime(Integer.parseInt(session.getAttribute("ExerciseID").toString()))); %> </p> 
                <%
            } else if (session.getAttribute("ModifyValue").equals("Frequency")) {
                %>
                    <div class = "inputBox"> 
                        <p id="inputFrequency">Frequency</p> 
                        <input type="radio" name="Frequency" value="N/A" id="NA"/> 
                        <label for="NA">N/A</label>
                        <input type="radio" name="Frequency" value="EveryDay" id="EveryDay"/> 
                        <label for="EveryDay">Every Day</label>
                        <input type="radio" name="Frequency" value="EveryOtherDay" id="EveryOtherDay"/>
                        <label for="EveryOtherDay">Every Other Day</label>
                        <input type="radio" name="Frequency" value="EveryWeek" id="EveryWeek"/> 
                        <label for="EveryWeek">Every Week</label>
                    </div> 
                <%
                out.println("Current Frequency: ");
            } else if (session.getAttribute("ModifyValue").equals("Remove")) {
                %>
                    <div class ="inputBox">
                        <p id="ConfirmRemove">Are you sure you want to remove this Exercise?</p>
                        <input type="radio" name="delete" value="NoDelete" id="NoDelete"/> 
                        <label for="noDelete">No</label>
                        <input type="radio" name="delete" value="YesDelete" id="YesDelete"/>
                        <label for="yesDelete">Yes</label>
                    <div>
                <%
            } else if (session.getAttribute("ModifyValue").equals("Complete")) {
                                
                // Setting the exercise in CreateExercises
                e.setExercise(session.getAttribute("Exercise").toString());
                
                // Setting the exerciseID in CreateExercises
                e.setExerciseID(Integer.parseInt(session.getAttribute("ExerciseID").toString()));
                                             
                // Showing input fields to the user, based on what their exercise needs
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
                
                %> <div class = "inputBox"> <p class="input">Extra Notes</p> <textarea id="Notes" name="Notes" rows="10" cols="30"></textarea> <%
            }
        %>
                                
        <!--The submit button-->
        <div id="inputButton"> <input type="submit" value="Submit" name = "submit"/> </div>   
        
        <%
            // Seeing if the user clicked submit
            if (request.getParameter("submit") != null) {                               
                
                // Seeing what they entered data for
                if (session.getAttribute("ModifyValue").equals("DateTime")) {
                    
                    // if checkDateTime returns 0, there are no errors
                    int check = e.checkDateTime(request.getParameter("date"), request.getParameter("time"));
                    
                    // If checkDateTime returns 4, there is an error
                    if (check == 4) {
                        // Get error #4 from CreateExercises, and print it to show the user
                        %>  <div class="error"> <% out.println(e.getError4()); %> </div> <%
                    } else {
                        // Change the Date/Time for the exercise
                        e.changeDateTime(request.getParameter("date"), request.getParameter("time"), Integer.parseInt(session.getAttribute("ExerciseID").toString()));

                        // Redirect the user to the main page 
                        response.sendRedirect("Main.jsp");
                    }
                } if (session.getAttribute("ModifyValue").equals("Frequency")) {
                    // Change the Date/Time for the exercise
                    e.changeDateTime("e", "f", 1);
                } if (session.getAttribute("ModifyValue").equals("Remove")) {
                    
                    // if checkRemoval returns 0, there are no errors
                    int check = e.checkRemoval(request.getParameter("NoDelete"), request.getParameter("YesDelete"));
                    
                    // if checkRemoval returns 5, there is an error
                    if (check == 5) {
                        %>  <div class="error"> <% out.println(e.getError5()); %> </div> <%
                    } else {
                        // Remove the exercise
                        e.remove(request.getParameter("NoDelete"), request.getParameter("YesDelete"), Integer.parseInt(session.getAttribute("ExerciseID").toString()));
                        
                        // Send the user to main page
                        response.sendRedirect("Main.jsp");
                    }
                } else if (session.getAttribute("ModifyValue").equals("Complete")) {
                    // Set the exercise data, based on what the exercise is
                    if (e.getExercise().equals("Running") || e.getExercise().equals("Walking") || e.getExercise().equals("Hiking")) {
                        e.setData(Integer.parseInt(request.getParameter("Distance")), Integer.parseInt(request.getParameter("Duration")),
                                request.getParameter("Notes"));
                        %> <div class="done"> <p>Exercise Completed</p> </div> <%
                        e.addIsComplete(e.getExerciseID());
                        response.sendRedirect("Main.jsp");
                    } else if (e.getExercise().equals("Swimming")) {
                        e.setData(Integer.parseInt(request.getParameter("Distance")), Integer.parseInt(request.getParameter("Duration")),
                                request.getParameter("TypeOfSW"), request.getParameter("Notes"));
                        %> <div class="done"> <p>Exercise Completed</p> </div> <%
                        e.addIsComplete(e.getExerciseID());
                        response.sendRedirect("Main.jsp");
                    } else if (e.getExercise().equals("Weightlifting")) {
                        e.setData(Integer.parseInt(request.getParameter("Reps")), Integer.parseInt(request.getParameter("Sets")),
                                Integer.parseInt(request.getParameter("Weight")), request.getParameter("Weightlifting"), request.getParameter("Notes"));
                        %> <div class="done"> <p>Exercise Completed</p> </div> <%
                        e.addIsComplete(e.getExerciseID());
                        response.sendRedirect("Main.jsp");
                    } else if (e.getExercise().equals("Cycling")) {
                        e.setData(Integer.parseInt(request.getParameter("Distance")), Integer.parseInt(request.getParameter("Duration")),
                                request.getParameter("Cycling"), request.getParameter("Notes"), 1);
                        %> <div class="done"> <p>Exercise Completed</p> </div> <%
                        e.addIsComplete(e.getExerciseID());
                        response.sendRedirect("Main.jsp");
                    } else if (e.getExercise().equals("Martial Arts")) {
                        e.setData(Integer.parseInt(request.getParameter("Duration")), request.getParameter("Martial Arts"), request.getParameter("Notes"));
                        %> <div class="done"> <p>Exercise Completed</p> </div> <%
                        e.addIsComplete(e.getExerciseID());
                        response.sendRedirect("Main.jsp");
                    } else if (e.getExercise().equals("Other")) {
                        e.setData(Integer.parseInt(request.getParameter("Distance")), Integer.parseInt(request.getParameter("Duration")),
                                Integer.parseInt(request.getParameter("Weight")), Integer.parseInt(request.getParameter("Reps")),
                                Integer.parseInt(request.getParameter("Sets")), request.getParameter("Notes"));
                        %> <div class="done"> <p>Exercise Completed</p> </div> <%
                        e.addIsComplete(e.getExerciseID());
                        response.sendRedirect("Main.jsp");
                    } else {
                        e.setData(Integer.parseInt(request.getParameter("Duration")), request.getParameter("Notes"));
                        %> <div class="done"> <p>Exercise Completed</p> </div> <%
                        e.addIsComplete(e.getExerciseID());
                        response.sendRedirect("Main.jsp");
                    }
                }                
            }
        %>
        
        </form>
    </body>
</html>
