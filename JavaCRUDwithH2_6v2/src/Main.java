import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

public class Main {
	
	public static void main(String[] args) {
		
		try {
			CreateCustomerSql.execute();
			System.out.println("The CREATE IF NOT EXISTS on the customer table has been executed successfully.");
			
		} catch (ClassNotFoundException e) {
			System.err.println("Unable to load the driver file: " +  e.getMessage());
		} catch (SQLException e) {
			System.err.println("Unable to execute CREATE IF NOT EXISTS for table Customer: " + e.getMessage());
		}
		
		try {
			int rowsAffeced = InsertIntoCustomerSql.executeUpdate();
			System.out.println(rowsAffeced + " customers have been inserted.");
		} catch (ClassNotFoundException e) {
			System.err.println("Unable to load the driver file: " +  e.getMessage());
		} catch (SQLException e) {
			System.err.println("Unable to insert customers into the databse: " + e.getMessage());
		}
	}
}