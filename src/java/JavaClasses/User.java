package JavaClasses;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Importing jasypt, the password encryptor
import org.jasypt.util.text.BasicTextEncryptor;

public class User {
    
    // Creating a encryptor object for password encryption
    private BasicTextEncryptor ENC = new BasicTextEncryptor();  
    
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet rs;
    
    // Encrypted password
    private String encPass;
    
    public void addUser(String FName, String LName, String Email, String User, String Pass) throws SQLException {
                
        // Setting the encryptors password
        ENC.setPassword("bruh");
        
        // Encrypting the passsword the user entered
        encPass = ENC.encrypt(Pass);
        
        try {
            
            // Connecting the the database if not already done 
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
            
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
    
    public int checkUser(String user) {
        try {
            
            // Connecting the the database if not already done 
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
            } catch (Exception e) {
                e.printStackTrace();
                
                // If it fails on getting another piece of data, the username is unique
                return 0;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
    
    public int checkEmail(String email) {
        try {
            
            // Connecting the the database if not already done 
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
            } catch (Exception e) {
                e.printStackTrace();
                
                // If it fails on getting another piece of data, the username is unique
                return 0;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
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
        
        // Setting a password for the encryptor
        ENC.setPassword("bruh");
        
        // Connecting to the database if not already connected
        if (SQLCon.getIsConn() == false) {
            SQLCon.Connect();
        }
        
        // Creating an SQL query to gather the password from the username that was entered by the user
        String query = "SELECT PASSWORD FROM USERS WHERE USERNAME = '" + User + "'";
        
        // Initializing the String which will keep the de-encrypted password
        String deEncPass = "";
        
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
        } catch (Exception e) {
            e.printStackTrace();
            return 6;
        }
    }
    
    public static String getError6() {
        return "Password and Username Don't Match";
    }
    
    public String getName(String User) throws SQLException {
        
        
        // Connecting to the database, if not already connected
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
        } catch (Exception e) {
            
            // If something goes wrong, returns error
            return "error";
        }
    }
    
    public void updateUser(String user, String column, String newData) throws SQLException {
        
        // Setting the password for the encryptor
        ENC.setPassword("bruh");
        
        // Connecting to the database if not already connected
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
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateMeasurement(String user, String measurement) throws SQLException {
        
        // Connecting to the database if not already connected
        if (SQLCon.getIsConn() == false) {
            SQLCon.Connect();
        }
        
        // Creating a SQL query that will update the users prefered measurment system
        String query = "UPDATE USERS SET Measurement = '" + measurement + "' WHERE Username = '" + user + "'";
        
        try {
            stmt = SQLCon.getConn().createStatement();
           
            stmt.executeUpdate(query);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getMeasurement(String user, String type) throws SQLException {
        
        // Connecting to the database if not already done so
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Returning a String based on what type of measuring is occuring
        if (system.equals("imperial")) {
            if (type.equals("weight")) {
                return "lb";
            } else if (type.equals("distance")) {
                return "miles";
            } else if (type.equals("height")) {
                return "ft";
            } else {
                return "imperial";
            }
        } else {
            if (type.equals("weight")) {
                return "kg";
            } else if (type.equals("distance")) {
                return "km";
            } else if (type.equals("height")) {
                return "m";
            } else {
                return "metric";
            }
        }
    }    
}