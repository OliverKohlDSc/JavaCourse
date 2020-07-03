
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Main {

	static final String JDBC_DRIVER = "org.h2.Driver";
	static final String DB_URL = "jdbc:h2:~/h2demo";
	static final String USER = "sa";
	static final String PASSWORD = "1234";
	
	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		List<Customer> customers = new ArrayList<>();
		
		try {
			
			// SCHRITT 1: Registrieren des JDBC-Treibers.
			Class.forName(JDBC_DRIVER);
			System.out.println("JDBC-Treiber wurde geladen.");
			
			// SCHRITT 2: Mache eine Verbindung zur DB.
			connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			System.out.println("Connected to database.");
			
			// SCHRITT 3: Führe die Query aus (SQL-Abfrage)
			statement = connection.createStatement();
			String sql = "DELETE FROM CUSTOMER WHERE id = 7;";
			statement.executeUpdate(sql);
			
			sql = "SELECT * FROM CUSTOMER;";
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("Name");
				int age = rs.getInt("age");
				int salary = rs.getInt("salary");
				String address = rs.getString("Address");
				Date startDate = rs.getDate("Start_Date");
				
				customers.add(new Customer(id, name, age, address, salary, startDate));
			}
		}
		catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			System.out.println("Closed connection!");
		}
		
		customers.stream().forEach(System.out::println);
	}
}
