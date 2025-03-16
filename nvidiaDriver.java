package projectnvidia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class nvidiaDriver {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/nvidiafibernet";
			String user = "root";
			String password = "root";

			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println(con);
			System.out.println("Connection is created");
			Statement st = con.createStatement();
			String query = "SELECT * FROM sign_in";
			st.execute(query);
			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				String user1 = rs.getString(1);
				System.out.println(user1);
			}
			st.close();
			con.close();
			System.out.println("Connection Closed");

		} catch (ClassNotFoundException | SQLException s) {
			s.printStackTrace();
		}

	}

}
