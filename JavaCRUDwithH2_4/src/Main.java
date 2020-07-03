import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class Main {

	private static final String DB_USER = "sa";
	private static final String DB_PASS = "1234";
	private static final String CONNECTIONSTRING = "jdbc:h2:~/h2demo;AUTO_SERVER=TRUE";
	private static final String DRIVER = "org.h2.Driver";
	
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement prepStatement = null;
		
		try
		{
			Class.forName(DRIVER);
			System.out.println("SQL driver for H2 loaded.");
			
			connection = DriverManager.getConnection(CONNECTIONSTRING, DB_USER, DB_PASS);
			System.out.println("Opened connection to DB server.");
			
			String sql = "INSERT INTO Customer (id,name,age,salary,address,start_date) VALUES (?,?,?,?,?,?)";
			prepStatement = connection.prepareStatement(sql);
			System.out.println("Created prepared statement.");
			
			prepStatement.setInt(1, 8);
			prepStatement.setString(2,  "Einstein");
			prepStatement.setInt(3, 33);
			prepStatement.setInt(4, 4000);
			prepStatement.setString(5, "Hessen");
			prepStatement.setDate(6, new Date(2020, 6, 18));
			
			if (prepStatement.executeUpdate() == 1) {
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
			if (prepStatement != null)
				try {
					prepStatement.close();
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