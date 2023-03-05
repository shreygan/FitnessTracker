package JavaClasses;

/*
 * @author shrey
 */

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Time;

import java.util.ArrayList;
import java.util.Calendar;

public class CreateExercises extends Exercise{
    
    private static Statement stmt;
    private static PreparedStatement pstmt;
    private static ResultSet rs;
    
    private static String exercise;
    private static int exerciseID;
    
    private static String date;
    
    private static String[] allAspects;    
    private static ArrayList<String> eAspects;    
    
    private static  Exercise e = new Exercise();
    
    public static String getExercise() {
        return exercise;
    }
    
    public static void setExerciseID(int exerciseID) {
        CreateExercises.exerciseID = exerciseID;
    }
    
    public static void setAllAspects() {
        allAspects = new String[10];
        
        allAspects[0] = "distance";
        allAspects[1] = "duration";
        allAspects[2] = "weight";
        allAspects[3] = "reps";
        allAspects[4] = "sets";
        allAspects[5] = "typeOfWL";
        allAspects[6] = "typeOfSW";
        allAspects[7] = "typeOfC";
        allAspects[8] = "typeOfMA";
        allAspects[9] = "other";  
    }
    
    public static void setExercise(String e) {
                  
        setAllAspects();
        eAspects = new ArrayList<>();
        
        exercise = e;
        
        if (e.equals("Running") || e.equals("Walking") || e.equals("Hiking")) { // distance, time
            eAspects.add(allAspects[0]);
            eAspects.add(allAspects[1]);
            eAspects.add(allAspects[9]);
        } else if (e.equals("Weightlifting")) { // weight, reps, sets, type of weightlifting
            eAspects.add(allAspects[5]);
            eAspects.add(allAspects[2]);
            eAspects.add(allAspects[3]);
            eAspects.add(allAspects[4]);
            eAspects.add(allAspects[9]);
        } else if (e.equals("Swimming")) { // distance, time, type of swimming
            eAspects.add(allAspects[0]);
            eAspects.add(allAspects[1]);
            eAspects.add(allAspects[6]);
            eAspects.add(allAspects[9]);
        } else if (e.equals("Basketball") || e.equals("Yoga") || e.equals("Soccer") || e.equals("Football") || e.equals("Baseball") || e.equals("Tennis") || e.equals("Badminton")) {
            eAspects.add(allAspects[1]);
            eAspects.add(allAspects[9]);
        } else if (e.equals("Cycling")) {
            eAspects.add(allAspects[0]);
            eAspects.add(allAspects[1]);
            eAspects.add(allAspects[7]);
            eAspects.add(allAspects[9]);
        } else if (e.equals("Martial Arts")) {
            eAspects.add(allAspects[1]);
            eAspects.add(allAspects[8]);
            eAspects.add(allAspects[9]);
        } else if (e.equals("Other")) {
            eAspects.add(allAspects[0]);
            eAspects.add(allAspects[1]);
            eAspects.add(allAspects[2]);
            eAspects.add(allAspects[3]);
            eAspects.add(allAspects[4]);
            eAspects.add(allAspects[9]);
        }
    }
    
    public static ArrayList<String> getEAspects() {
        return eAspects;
    }
    
    public static int checkInitialData(String date, String time, String email, String exercise) {
        
        if (date.equals("") || time.equals("") || email == null || exercise == null) {
            return 1;
        }
        
        if (date.charAt(0) != '2' || date.charAt(4) != '-'  || date.charAt(7) != '-') {
            return 2;
        }
        
        CreateExercises.exercise = exercise;
        CreateExercises.date = date;
        
        return 0;
    }
    
    public static String getError1() {
        return "Please Enter All Data";
    }
    
    public static String getError2() {
        return "Please Enter a Valid Date";
    }
    
    public static int getExerciseID() {
        return exerciseID;
    }
    
    public static void setInitialData(String date, String time, String sEmail, String exercise, String user) throws SQLException {
                        
        // Create a date object with the date of the exercise
        Date d = Date.valueOf(date);
        
        // Add milliseconds to the time, to match the format of a Time object
        time += ":00";
        
        // Create a time object with the time of the exercise
        Time t = Time.valueOf(time);
                
        boolean bEmail;
        
        // Setting the boolean value for SQL
        if (sEmail.equals("yes")) {
            bEmail = true;
        } else {
            bEmail = false;
        }
        try {
            
            // Checks if there is already a connection to the SQL database
            if (SQLCon.getIsConn() == false) {
                SQLCon.Connect();
            }

            // Creating a query for entering the data into the database
            String query1 = "INSERT INTO Exercises"
                    + " (ExerciseDate, ExerciseTime, Email, Exercise, Username)"
                    + " values (?,?,?,?,?)";
            
            // Using PreparedStatements to prevent SQL injections
            pstmt = SQLCon.getConn().prepareStatement(query1);
                                
            // Setting the PreparedStatment values
            pstmt.setDate(1, d);
            pstmt.setTime(2, t);
            pstmt.setBoolean(3, bEmail);
            pstmt.setString(4, exercise);
            pstmt.setString(5, user); 

            // Executing the PreparedStatement
            pstmt.executeUpdate();
            
            // Getting the ID for the exercise that was entered
            String query2 = "SELECT ID FROM Exercises ORDER BY ID DESC FETCH FIRST ROW ONLY";
                                    
            // Using a Statement and ResultSet to get the ID
            stmt = SQLCon.getConn().createStatement();
            rs = stmt.executeQuery(query2);
            rs.next();
            
            exerciseID = rs.getInt("id");
            
            // Adding the exercise, username, and email preference in the Exercise class
            e.addExercise(exerciseID, user, bEmail);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        
    }

    public static boolean isDatePast() {
//        java.sql.Date cD = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                        
        Date cD = new Date(Calendar.getInstance().getTime().getTime());
                
        Date d = Date.valueOf(date);
                
        if (d.equals(cD)) {
            return false;
        } else {
            return d.after(cD);
        }        
    }
    
    public static int checkData(String distance, String duration, String weight, String reps, String sets, String typeOfWL, String typeOfSW, String typeOfC, String typeOfMA) {
        if (exercise.equals("Running") || exercise.equals("Walking") || exercise.equals("Hiking")) {
            if (distance == null || duration == null) {
                return 3;
            }
        } else if (exercise.equals("Swimming")) {
            if (distance == null || duration == null || typeOfSW == null) {
                return 3;
            }
        } else if (exercise.equals("Weightlifting")) {
            if (reps == null || sets == null || weight == null || typeOfWL == null) {
                return 3;
            }
        } else if (exercise.equals("Cycling")) {
            if (distance == null || duration == null || typeOfC == null) {
                return 3;
            }
        } else if (exercise.equals("Martial Arts")) {
            if (distance == null || typeOfMA == null) {
                return 3;
            }
        } else if (exercise.equals("Other")) {} else {
            if (duration == null) {
                return 3;
            }
        }
        return 0;
    }
    
    public static String getError3() {
        return "Please Enter All Data";
    }

    // Running, Walking, Hiking
    public static void setData(int distance, int duration, String notes) throws SQLException {
        if (!SQLCon.getIsConn()) {
            SQLCon.Connect();
        }
        
        String query = "UPDATE Exercises SET Distance = " + distance + ", Duration = " + duration + ", Other = '" 
                + notes + "' WHERE id = " + exerciseID;
                
        try {
            stmt = SQLCon.getConn().createStatement();
           
            stmt.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    // Swimming
    public static void setData(int distance, int duration, String typeOfSW, String notes) throws SQLException {
        if (!SQLCon.getIsConn()) {
            SQLCon.Connect();
        }
        
        String query = "UPDATE Exercises SET Distance = " + distance + ", Duration = " + duration + ", TypeOfSW = '" 
                + typeOfSW + "', Other = '" + notes + "' WHERE id = " + exerciseID;
                
        try {
            stmt = SQLCon.getConn().createStatement();
           
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Weightlifting
    public static void setData(int reps, int sets, int weight, String typeOfWL, String notes) throws SQLException {
        if (!SQLCon.getIsConn()) {
            SQLCon.Connect();
        }
        
        String query = "UPDATE Exercises SET Reps = " + reps + ", E_Sets = " + sets + ", Weight = " 
                + weight + ", TypeOfWL = '" + typeOfWL + "', Other = '" + notes + "' WHERE id = " + exerciseID;
                
        try {
            stmt = SQLCon.getConn().createStatement();
           
            stmt.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    // Cycling
    public static void setData(int distance, int duration, String typeOfC, String notes, int extra) throws SQLException {
        if (!SQLCon.getIsConn()) {
            SQLCon.Connect();
        }
        
        String query = "UPDATE Exercises SET Distance = " + distance + ", Duration = " + duration + ", TypeOfC = '" 
                + typeOfC + "', Other = '" + notes + "' WHERE id = " + exerciseID;
                
        try {
            stmt = SQLCon.getConn().createStatement();
           
            stmt.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    public static void setData(int duration, String notes) throws SQLException {
        if (!SQLCon.getIsConn()) {
            SQLCon.Connect();
        }
        
        String query = "UPDATE Exercises SET Duration  = " + duration + " WHERE id = " + exerciseID;
                
        try {
            stmt = SQLCon.getConn().createStatement();
           
            stmt.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    // Martial Arts 
    public static void setData(int duration, String typeOfMA, String notes) throws SQLException {
        if (!SQLCon.getIsConn()) {
            SQLCon.Connect();
        }
        
        String query = "UPDATE Exercises SET Duration = " + duration + ", TypeOfMA = '" + typeOfMA + "', Other = '" 
                + notes + "' WHERE id = " + exerciseID;
                
        try {
            stmt = SQLCon.getConn().createStatement();
           
            stmt.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
        
    // Other
    public static void setData(int distance, int duration, int weight, int reps, int sets, String notes) throws SQLException {
        if (!SQLCon.getIsConn()) {
            SQLCon.Connect();
        }
        
        String query = "UPDATE Exercises SET Distance = " + distance + ", Duration = " + duration + ", Weight = " 
                + weight + ", Reps = " + reps + ", Sets = " + sets + ", Other = '" + notes + "' WHERE id = " + exerciseID;
                
        try {
            stmt = SQLCon.getConn().createStatement();
           
            stmt.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    // Future
    public static void setData(String notes) throws SQLException {
        if (!SQLCon.getIsConn()) {
            SQLCon.Connect();
        }
        
        String query = "UPDATE Exercises SET Other = '" + notes + "'  WHERE id = " + exerciseID;
                
        try {
            stmt = SQLCon.getConn().createStatement();
           
            stmt.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}