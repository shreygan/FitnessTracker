package JavaClasses;

/*
 * @author shrey
 */

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import java.util.ArrayList;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Exercise {
        
    private static String username;
    private static ArrayList<Integer> idList = new ArrayList<>();
    private static ArrayList<String> userList = new ArrayList<>();
    private static ArrayList<Boolean> emailList = new ArrayList<>();

    private static ArrayList<Integer> indicies = new ArrayList<>();
    
    private static ArrayList<Integer> isComplete = new ArrayList<>();
    
    private static Statement stmt;
    private static ResultSet rs;

    public static void setUsername(String username) {
        Exercise.username = username;
    }    
    
    public static ArrayList<Integer> getIdList() {
        return idList;
    }
    
    public static void addIsComplete(int id) {
        isComplete.add(id);
    }
    
    public static boolean getIsComplete(int id) {
        return isComplete.contains(id);
    }
    
    public static void addExercise(int id, String user, boolean email) {
        idList.add(id);
        userList.add(user);
        emailList.add(email);
    }
    
    public static void addExistingData() {
        try {
            if (SQLCon.getIsConn() == false) {
                SQLCon.Connect();
            }
            
            String query = "SELECT ID, EMAIL FROM Exercises WHERE Username = '" + username + "'";
            
            stmt = SQLCon.getConn().createStatement();
            rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                idList.add(rs.getInt("id"));
                userList.add(username);
                emailList.add(rs.getBoolean("EMAIL"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static ArrayList<Integer> getIndiciesList() {
        
        int count = 0;
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).equals(username)) {
                indicies.add(count, idList.get(i));
                count++;
            }            
        }
        return indicies;
    }
    
    public static String printExercise(String user, int id) {
                     
        String s = "";
        
        try {
            if (SQLCon.getIsConn() == false) {
                SQLCon.Connect();
            }
                        
            String query = "SELECT * FROM Exercises WHERE id = " + id;
                        
            indicies.remove(0);
            
            stmt = SQLCon.getConn().createStatement();
            rs = stmt.executeQuery(query);
            
            rs.next();
              
            s = id + " ";
            s += rs.getDate("Exercisedate").toString() + " ";
            s += rs.getTime("Exercisetime").toString() + " ";
            s += rs.getString("Exercise");
            
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return s;
    }
    
    public static String getExercise(int id) {
        try {
            if (SQLCon.getIsConn() == false) {
                SQLCon.Connect();
            }
            
            String query = "SELECT Exercise FROM EXERCISES WHERE id = " + id;
            
            stmt = SQLCon.getConn().createStatement();
            rs = stmt.executeQuery(query);
            
            rs.next();
            
            return rs.getString("Exercise");
            
        } catch(Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    
    public static Date getDate(int id) {
        try {
            if (SQLCon.getIsConn() == false) {
                SQLCon.Connect();
            }
            
            String query = "SELECT ExerciseDate FROM EXERCISES WHERE id = " + id;
            
            stmt = SQLCon.getConn().createStatement();
            rs = stmt.executeQuery(query);
            
            rs.next();
            
            return rs.getDate("ExerciseDate");
            
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Time getTime(int id) {
        try {
            if (SQLCon.getIsConn() == false) {
                SQLCon.Connect();
            }
            
            String query = "SELECT ExerciseTime FROM EXERCISES WHERE id = " + id;
            
            stmt = SQLCon.getConn().createStatement();
            rs = stmt.executeQuery(query);
            
            rs.next();
            
            return rs.getTime("ExerciseTime");
            
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static int checkDateTime(String date, String time) {
        if (date.equals("") && time.equals("")) {
            return 4; 
        }
        return 0;
    }
    
    public static void changeDateTime(String date, String time, int id) throws SQLException {
        if (!SQLCon.getIsConn()) {
            SQLCon.Connect();
        }
        
        String query = "";
        if (date.equals("") &&  !time.equals("")) {
            time += ":00";
            Time t = Time.valueOf(time);
            query = "UPDATE Exercises SET ExerciseTime = '" + t + "' WHERE id = " + id;
        } else if (time.equals("") && !date.equals("")) {
            Date d = Date.valueOf(date);
            query = "UPDATE Exercises SET ExerciseDate = '" + d + "' WHERE id = " + id;
        } else if (time.equals("") && date.equals("")){
        } else {
            time += ":00";
            Time t = Time.valueOf(time);
            Date d = Date.valueOf(date);
            query = "UPDATE Exercises SET ExerciseTime = '" + t + "', ExerciseDate + '" + d +  "' WHERE id = " + id;
        }
                
        try {
            stmt = SQLCon.getConn().createStatement();
           
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static int checkRemoval(String yes, String no) {
        if (yes.equals("") && no.equals("")) {
            return 5; 
        }
        return 0;
    }
    
    public static void remove(String yes, String no, int id) throws SQLException {
        if (!SQLCon.getIsConn()) {
            SQLCon.Connect();
        }

        String query = "";
        if (yes.equals("") && !no.equals("")) {
        } else if (!yes.equals("") && !no.equals("")) {
        } else if (!yes.equals("") && no.equals("")) {
            query = "DELETE FROM Exercises WHERE id = " + id;
        }

        try {
            stmt = SQLCon.getConn().createStatement();

            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String getError4() {
        return "Please Enter Either Time or Date";
    }
    
    public static String getError5() {
        return "Please Choose an Option";
    }
    
        
    
    
    
    
    
    
    
    
    
//    public static void main(String[] args) {
//        sendEmail();
//    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void sendEmail() {
        // Recipient's email ID needs to be mentioned.
        String to = "shrey.gangwar21@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "a@gmail.com";

        // Assuming you are sending email from localhost
        String host = "73.225.150.180";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setText("This is actual message");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
