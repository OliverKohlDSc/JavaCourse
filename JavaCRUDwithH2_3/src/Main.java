import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	private static final String DB_USER = "sa";
	private static final String DB_PASS = "1234";
	private static final String CONNECTIONSTRING = "jdbc:h2:~/h2demo;AUTO_SERVER=TRUE";
	private static final String DRIVER = "org.h2.Driver";
	
	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		
		try
		{
			Class.forName(DRIVER);
			System.out.println("SQL driver for H2 loaded.");
			
			connection = DriverManager.getConnection(CONNECTIONSTRING, DB_USER, DB_PASS);
			System.out.println("Opened connection to DB server.");
			
			statement = connection.createStatement();
			System.out.println("Created statement.");
			String sql = "INSERT INTO Customer (id,name,age,salary,address,start_date) VALUES (7, 'Abdul', 45, 1320, 'Köln', null)";
			if (statement.executeUpdate(sql) == 1) {
				System.out.println("Executed statement.");
			}
			else
			{
				System.out.println("Executed statement with errors.");
			}
			
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}
