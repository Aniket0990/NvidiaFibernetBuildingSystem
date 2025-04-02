package projectnvidia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class dbAdmin {
    
    public static boolean validateAdmin(String username, String password) {
        boolean isValid = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            String url = "jdbc:mysql://localhost:3306/nvidiafibernet";
            String dbUser = "root";
            String dbPassword = "root";

            Connection con = DriverManager.getConnection(url, dbUser, dbPassword);
            
            String query = "SELECT * FROM admin_sign_in WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, username);  
            preparedStatement.setString(2, password);  

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isValid = true;
            }

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


