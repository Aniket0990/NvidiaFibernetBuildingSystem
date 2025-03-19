package projectnvidia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class dbAdmin {
    
    public static boolean validateAdmin(String username, String password) {
        boolean isValid = false;

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Database Connection Details
            String url = "jdbc:mysql://localhost:3306/nvidiafibernet";
            String dbUser = "root";
            String dbPassword = "root";

            // Establish JDBC Connection
            Connection con = DriverManager.getConnection(url, dbUser, dbPassword);
            
            // Query to validate user
            String query = "SELECT * FROM admin_sign_in WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, username);  // User-provided username
            preparedStatement.setString(2, password);  // User-provided password

            // Execute query
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isValid = true; // User credentials are valid
            }

            // Close connection
            con.close(); 

        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Make sure you add the driver to your project.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error occurred while validating Admin.");
            e.printStackTrace();
        }

        return isValid;
    }
}


