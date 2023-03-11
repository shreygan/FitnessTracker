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
    private static final ArrayList<Integer> idList = new ArrayList<>();
    private static final ArrayList<String> userList = new ArrayList<>();
    private static final ArrayList<Boolean> emailList = new ArrayList<>();

    private static final ArrayList<Integer> indicies = new ArrayList<>();
    
    private static final ArrayList<Integer> isComplete = new ArrayList<>();
    
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
        } catch(SQLException e) {
            // do nothing
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
            
        } catch (SQLException e){
            // do nothing
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
            
        } catch(SQLException e) {
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
            
        } catch(SQLException e) {
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
            
        } catch(SQLException e) {
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
        } catch (SQLException e) {
            // do nothing
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
        } catch (SQLException e) {
            // do nothing
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
        String to = "shrey.gangwar21@gmail.com";

        String from = "a@gmail.com";

        String host = "73.225.150.180";

        Properties properties = System.getProperties();

        properties.setProperty("mail.smtp.host", host);

        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("This is the Subject Line!");
            message.setText("This is actual message");

            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            System.out.println("Send Failed.");
        }
    }
}
