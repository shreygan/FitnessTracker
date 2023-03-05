<%-- 

    MAIN

--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Scanner"%>
<%@page import="JavaClasses.CreateExercises"%>
<%@page import="JavaClasses.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Exercise Tracker - Main</title>
        
        <!--Setting the icon for this page-->
        <link rel="icon" href='Images/Icon.png'>
        
        <!--Importing the CSS Sheet for this page-->
        <link href='CSS/Main.css' rel='stylesheet' type='text/css'/>
        
    </head>
    
    <body>
        
        <!--Creating the user class-->
        <% User u = new User();%>
        
        <!--Creating the options at the top of the page to switch between pages--> 
        <div class="topnav">
            <a href="index.jsp">Log Out</a>
            <a href="Settings.jsp">Settings</a>
            <a href="CreateExercises.jsp">Create Exercises</a>
            <a href="Calendar.jsp">Calendar</a>
            <a href="Main.jsp">Main Menu</a>
        </div>
        
        <br/><br/><br/>
                
        <%
            // Setting the First Name of the user
            session.setAttribute("FName", u.getName(session.getAttribute("Username").toString()));
            String Name = session.getAttribute("FName").toString();
        %>
        
        <!--The top message displayed on the page, and adding the name of the user-->
        <h1 id="topMessage">Welcome <% out.println(Name); %> </h1>   
        
        <h2 align='center'>Today's To-Do List</h2>
      
        <%   
            // Creating the CreateExercises class
            CreateExercises e = new CreateExercises();
        
            // Setting the username of the user in CreateExercises
            e.setUsername(session.getAttribute("Username").toString());
            
            // If this the users first time on this page in this session, this will add the necessary data in CreateExercises
            if (session.getAttribute("Iteration").toString().equals("1")) {
                e.addExistingData();
            }
            
            // Creating an ArrayList of the indicies of exercises that belong to the user
            ArrayList<Integer> temp = e.getIndiciesList();
            
            // Creating an ArrayList to add to, for exercises that have been printed on the screen
            // Not using diamond operator because JSP doesn't compile properly with it
            ArrayList<Integer> printedID = new ArrayList<Integer>();
            
            // Setting a variable for how many times the loop needs to run
            int loops = temp.size();
                                    
            for (int i = 0; i < loops; i++) {   
                // Making a scanner for all that data related to the exercise
                Scanner sc = new Scanner(e.printExercise(session.getAttribute("Username").toString(), temp.get(0)));
                
                // Getting the id of the exercise
                int tempID = sc.nextInt();  
                
                // Getting the date of the exercise
                String date = sc.next();
                
                // Getting the time of the exercise
                String time = sc.next();
                
                // Getting the name of the exercise
                String exercise = sc.next();
                
                // Setting the exercise into a session Attribute, for later use by other pages
                session.setAttribute("Exercise", exercise);
                                
                // Getting a Date object for the current date 
                Date cD = new Date(Calendar.getInstance().getTime().getTime());
                
                // Getting a Date oject for the date of the exercise
                Date d = Date.valueOf(date);
                
                // Seeing if the date is today's, if the exercise is already printed, and if the exercise has been completed
                if (!printedID.contains(tempID) && d.getTime()/1000000000 == cD.getTime()/1000000000 && !e.getIsComplete(tempID)) {     
                        
                    // Getting the hour of the exercise
                    int h = Integer.parseInt(time.substring(0,2));
                    
                    // Checking if the time of the exercise is AM, or PM
                    String AMPM;
                    if (h < 12) {
                        AMPM = "AM";
                    } else {
                        AMPM = "PM";
                        h = h % 12;
                    }
                    
                    // Creating the String that will be printed onto the page 
                    String output = h + time.substring(2,5) + AMPM + "- " + exercise;
                    
                    // Printing the exercise
                    %> <div class="exercise"> <a class="link" href="Exercise.jsp?value=<%out.println(exercise);%>&id=<%out.println(tempID);%>"> <% out.println(output); %> </a> </div> <%
                    
                    // Sets the session attribute for iteration2 to 1 (first time), for use in later pages
                    // to see if this is the first time the user is visiting the calendar page, and to print 
                    // details accordingly
                    session.setAttribute("Iteration2", 1);
                    
                    // Adding the exercise ID to the list of already printed exercises
                    printedID.add(tempID);
                } 
            }
            
            // Setting the session Attribute for Interation to 2, indicating that the user has already visited this page
            // and to not repeat printing some details
            session.setAttribute("Iteration", 2);
        %>
    </body>
</html>
