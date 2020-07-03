import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException {
		// Load the JDBC H2 driver
		Class.forName("org.h2.Driver");
		
		// H2 driver loaded -> we shouldn't get any exception here ...
		System.out.println("Connection to the driver file works!");
		
		Connection con = null;
		
		try {
			con = DriverManager.getConnection("jdbc:h2:~/h2demo", "sa", "1234");
		} catch (SQLException e) {
			System.err.println("Connection to h2demo failed. Check your credentials.");
			return;
		}
		
		Statement statement = null;
		
		try {
			statement = con.createStatement();
		} catch (SQLException e) {
			System.err.println("Unable to create statement for h2 DB. Please check your internal DB rights.");
			return;
		}
		
		String sql = "CREATE TABLE NEWSLETTER" +
					 "(id INTEGER NOT NULL, " +
				     " firstName VARCHAR(255), " + 
				     " lastName VARCHAR(255), " + 
				     " email VARCHAR(50), " +
				     " PRIMARY KEY(id)" +
				     ")";
		try {
			statement.executeUpdate(sql);
			System.out.println("Create table in the h2demo database.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			statement.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("Unable to close connection.");
			return;
		}
		System.out.println("Connection closed successfully!");
	}
}