package projectnvidia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class dbBuyConnetion {
		
	
	    private static final String URL = "jdbc:mysql://localhost:3306/nvidiafibernet";
	    private static final String USER = "root";
	    private static final String PASSWORD = "root";

	    public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(URL, USER, PASSWORD);
	    }
	    Connection c;
	    Statement s;

		public dbBuyConnetion() {
			try {
				 Class.forName("com.mysql.cj.jdbc.Driver");
				 c=DriverManager.getConnection(URL,USER,PASSWORD);
				 s=c.createStatement();
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	    
	
}
