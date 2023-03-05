<%-- 
    CREATE EXERCISES
--%>

<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@page import="JavaClasses.CreateExercises"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Exercise Tracker - Creating an Exercise</title>
       
        <!--Setting the icon for this page-->
        <link rel="icon" href='Images/Icon.png'>
        
        <!--Importing the CSS Sheet for this page-->
        <link href='CSS/CreateExercises.css' rel='stylesheet' type='text/css'/>
        
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
        <h1 id="topMessage">Creating an Exercise</h1>   

        <form action="CreateExercises.jsp">
            <div class = "inputBox"> <p id="inputDateTime">Date &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
                    &nbsp; &nbsp; &nbsp;Time</p>
                <input type="date" name="Date"/>        <input type="time" name="Time"/> </div>
            
            <div class = "inputBox"> 
                <p id="inputFrequency">Frequency</p> 
                <input type="radio" name="Frequency" value="N/A" class="input"/> 
                <label for="NA">N/A</label>
                <input type="radio" name="Frequency" value="EveryDay" class="input"/> 
                <label for="EveryDay">Every Day</label>
                <input type="radio" name="Frequency" value="EveryOtherDay" class="input"/>                             <!-- ACTUALLY IMPLEMENT THIS SHIT INTO ADDING EXERCISES -->
                <label for="EveryOtherDay">Every Other Day</label>
                <input type="radio" name="Frequency" value="EveryWeek" class="input"/> 
                <label for="EveryWeek">Every Week</label>
            </div> 
            
            <div class = "inputBox"> 
                <p id="inputEmailOption">Receive Email Notifications?</p> 
                <input type="radio" name="Notif" value="yes" id="yes"/> 
                <label for="yes">Yes</label>
                <input type="radio" name="Notif" value="no" id="no"/> 
                <label for="no">No</label>
            </div>      
                
            <div class = "inputBox"> 
                <p id="inputExercise">What Type of Exercise is it?</p> 
                <select id="Exercises" name="ExerciseDropDown">
                    <option value="" disabled selected>Select Exercise</option>
                    <option value="Running">Running</option>
                    <option value="Walking">Walking</option>
                    <option value="Weightlifting">Weightlifting</option>
                    <option value="Swimming">Swimming</option>
                    <option value="Basketball">Basketball</option>
                    <option value="Cycling">Cycling</option>
                    <option value="Yoga">Yoga</option>
                    <option value="Soccer">Soccer</option>
                    <option value="Football">Football</option>
                    <option value="Baseball">Baseball</option>
                    <option value="Badminton">Badminton</option>
                    <option value="Tennis">Tennis</option>
                    <option value="Hiking">Hiking</option>
                    <option value="Martial Arts">Martial Arts</option>
                    <option value="Other">Other</option>
                </select>
            </div>
            
            <div id="inputButton"> <input type="submit" value="Next" name = "submit"/> </div>     
            
            <% 
                // Creating the CreateExercises class
                CreateExercises e = new CreateExercises();
                             
                // Seeing if the user has pressed submit
                if (request.getParameter("submit") != null) {
                
                    // If the checkInitialData method returns 0, that means that they entered the correct informatiom
                    int check = e.checkInitialData(request.getParameter("Date"), request.getParameter("Time"), request.getParameter("Notif"), request.getParameter("ExerciseDropDown"));
                
                    // If checkInitialData return 1 or 2, there is an error
                    if (check == 1) {
                        // Get error #1 from CreateExercises, and print it to show the user
                        %> <div class="error"> <% out.println(e.getError1()); %> </div> <%
                    } else if (check == 2) {
                        // Get error #2 from CreateExercises, and print it to show the user
                        %> <div class="error"> <% out.println(e.getError2()); %> </div> <%
                    } else {                    
                        // Setting the exercise in CreateExercises
                        e.setExercise(request.getParameter("ExerciseDropDown"));
                        
                        // Setting the session Attribute for the exercise, for later use by other pages
                        session.setAttribute("Exercise", request.getParameter("ExerciseDropDown"));
                        
                        // Setting the initial data that the user entered
                        e.setInitialData(request.getParameter("Date"), request.getParameter("Time"), request.getParameter("Notif"), request.getParameter("ExerciseDropDown"), session.getAttribute("Username").toString());                        
                        
                        // Redirecting the user the CreateExercises2, for more data to be entered
                        response.sendRedirect("CreateExercises2.jsp");
                    }
                }
            %>
        </form>
    </body>
</html>
