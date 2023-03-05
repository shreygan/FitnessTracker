package JavaClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLCon {
    
    private static Connection conn;
    private static boolean isConn;
    
    public static Connection getConn() {
        return conn;
    }
    
    public static boolean getIsConn() {
        return isConn;
    }

    public static void Connect() throws SQLException {
       
        // Creating the connection the database
        String url = "jdbc:derby://localhost:1527/IA_DB";
        String user = "IAuser";
        String pass = "root";
        String yo = "yo";
        
        try {
            conn = DriverManager.getConnection(url, user, pass);
            
            // Setting the variable for if there is a connection to true
            isConn = true;
        } catch(SQLException e) {
            // Setting the variable for if there is a connection to false
            isConn = false;
            e.printStackTrace();
        }
    }
}
