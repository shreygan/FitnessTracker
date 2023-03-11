package JavaClasses;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.jasypt.util.text.BasicTextEncryptor;

public class User {
    
    private final BasicTextEncryptor ENC = new BasicTextEncryptor();  
    
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet rs;
    
    private String encPass;
    
    public void addUser(String FName, String LName, String Email, String User, String Pass) {                
        ENC.setPassword("bruh");        
        encPass = ENC.encrypt(Pass);
        
        try {            
            if (SQLCon.getIsConn() == false) {
                SQLCon.Connect();
            }
                                    
            // Creating the SQL query to insert data into users
            String SQL = "INSERT INTO USERS"
                    + " (FirstName, LastName, Email, Username, Password)"
                    + " values (?,?,?,?,?)";

            stmt = SQLCon.getConn().createStatement();
            pstmt = SQLCon.getConn().prepareStatement(SQL);
            
            pstmt.setString(1, FName);
            pstmt.setString(2, LName);
            pstmt.setString(3, Email);
            pstmt.setString(4, User);
            pstmt.setString(5, encPass);

            pstmt.executeUpdate();
            
        } catch (SQLException exc) {
            System.out.println("SQL Error");
        }
    }
    
    public int checkUser(String user) {
        try {            
            if (SQLCon.getIsConn() == false) {
                SQLCon.Connect();
            }
            
            // Creating the SQL query to gather data from users if the username entered is used
            String query = "SELECT * FROM USERS WHERE USERNAME = '" + user + "'";
            
            stmt = SQLCon.getConn().createStatement();
            rs = stmt.executeQuery(query);
            
            rs.next();
            
            try {
                // If result set has a piece of data, it means that the username is already taken
                String temp = rs.getString("Username");
                return 1;
            } catch (SQLException e) {                
                // If it fails on getting another piece of data, the username is unique
                return 0;
            }
            
        } catch (SQLException e) {
            return 1;
        }
    }
    
    public int checkEmail(String email) {
        try {            
            if (SQLCon.getIsConn() == false) {
                SQLCon.Connect();
            }

            // Creating the SQL query to gather data from users if the email entered is used
            String query = "SELECT * FROM USERS WHERE EMAIL = '" + email + "'";
            
            stmt = SQLCon.getConn().createStatement();
            rs = stmt.executeQuery(query);
            
            rs.next();
            
            try {
                // If result set has a piece of data, it means that the username is already taken
                String temp = rs.getString("Username");
                return 1;
            } catch (SQLException e) {                
                // If it fails on getting another piece of data, the username is unique
                return 0;
            }
            
        } catch (SQLException e) {
            return 1;
        }
    }
    
    public int checkNewUser(String FName, String LName, String Email, String User, String Pass1, String Pass2) {
                
        // Checking the user's inputs
        if (FName.equals("") || LName.equals("") || Email.equals("") || User.equals("") || Pass1.equals("") || Pass2.equals("")) {
            return 1; // All data isn't entered
        } else if (!Pass1.equals(Pass2)) {
            return 2; // Password's are differen't
        } else if (!Email.contains("@") || !Email.contains(".")) {
            return 3; // Email isn't valid (doens't contain @ or .)
        } else if (checkEmail(Email) == 1) {         
            return 4; // check if email is already used in database
        } else if (checkUser(User) == 1) {
            return 5; // check if username is already used in database
        }
        return 0;
    }
    
    public static String getError1() {
        return "Please Enter All Data";
    }
    
    public static String getError2() {
        return "Passwords Don't Match";
    }
    
    public static String getError3() {
        return "Please Enter a Valid Email";
    }
    
    public static String getError4() {
        return "Email is Already in Use";
    }
    
    public static String getError5() {
        return "Username is Already in Use";
    }
    
         
    public int checkUser(String User, String Pass) throws SQLException {
        ENC.setPassword("bruh");
        
        if (SQLCon.getIsConn() == false) {
            SQLCon.Connect();
        }
        
        // Creating an SQL query to gather the password from the username that was entered by the user
        String query = "SELECT PASSWORD FROM USERS WHERE USERNAME = '" + User + "'";
        
        String deEncPass;        
        try {
            stmt = SQLCon.getConn().createStatement();
            rs = stmt.executeQuery(query);
            
            rs.next();
            
            // Getting the password that matches with the username entered
            encPass = rs.getString("PASSWORD");
            
            // De-encrypting the password from the database
            deEncPass = ENC.decrypt(encPass);
            
            // If the passwords dont match, return an error
            if (!deEncPass.equals(Pass)) {
                return 6;
            } else {
                return 0;
            }
        } catch (SQLException e) {
            return 6;
        }
    }
    
    public static String getError6() {
        return "Password and Username Don't Match";
    }
    
    public String getName(String User) throws SQLException {        
        if (SQLCon.getIsConn() == false) {
            SQLCon.Connect();
        }
        
        // Creating the SQL query to gather the firstname of the user, by using their username
        String query = "SELECT FIRSTNAME FROM USERS WHERE USERNAME = '" + User + "'";
        
        try {
            stmt = SQLCon.getConn().createStatement();
            rs = stmt.executeQuery(query);

            rs.next();

            // Returns the firstname of the user
            return rs.getString("FIRSTNAME");
        } catch (SQLException e) {
            // If something goes wrong, returns error
            return "error";
        }
    }
    
    public void updateUser(String user, String column, String newData) throws SQLException {        
        ENC.setPassword("bruh");
        
        if (SQLCon.getIsConn() == false) {
            SQLCon.Connect();
        }
        
        // Creating the query to update the users info based on the column, the new data, and the username
        String query = "UPDATE USERS SET " + column + " = " + "'" + newData + "' WHERE USERNAME = '" + user + "'";
        
        // If the user is trying to update their password, it encrypts it for them
        if (column.equals("Password")) {
            encPass = ENC.encrypt(newData);
            query = "UPDATE USERS SET " + column + " = " + "'" + encPass + "' WHERE USERNAME = '" + user + "'";
        }
        
        try {
            stmt = SQLCon.getConn().createStatement();
           
            stmt.executeUpdate(query);
            
        } catch (SQLException e) {
            // do nothing
        }
    }
    
    public void updateMeasurement(String user, String measurement) throws SQLException {        
        if (SQLCon.getIsConn() == false) {
            SQLCon.Connect();
        }
        
        // Creating a SQL query that will update the users prefered measurment system
        String query = "UPDATE USERS SET Measurement = '" + measurement + "' WHERE Username = '" + user + "'";
        
        try {
            stmt = SQLCon.getConn().createStatement();
           
            stmt.executeUpdate(query);
            
        } catch (SQLException e) {
            // do nothing
        }
    }
    
    public String getMeasurement(String user, String type) throws SQLException {        
        if (SQLCon.getIsConn() == false) {
            SQLCon.Connect();
        }
                
        // Defaulting the system to imperial
        String system = "imperial";
        
        try {
            // Creating a SQL query to gather the measurement from the username of the user
            String query = "SELECT Measurement FROM Users WHERE Username = '" + user + "'";
                                    
            stmt = SQLCon.getConn().createStatement();
            rs = stmt.executeQuery(query);
            
            rs.next();
              
            
            system = rs.getString("Measurement");
        } catch (SQLException e) {
            // do nothing
        }
        
        // Returning a String based on what type of measuring is occuring
        if (system.equals("imperial")) {
            switch (type) {
                case "weight":
                    return "lb";
                case "distance":
                    return "miles";
                case "height":
                    return "ft";
                default:
                    return "imperial";
            }
        } else {
            switch (type) {
                case "weight":
                    return "kg";
                case "distance":
                    return "km";
                case "height":
                    return "m";
                default:
                    return "metric";
            }
        }
    }    
}