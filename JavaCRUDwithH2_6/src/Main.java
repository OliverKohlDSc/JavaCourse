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
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		List<Customer> customers = new ArrayList<>();

		customers.add(new Customer(100, "Herbert", 33, "Braunschweig", 1200, LocalDate.now(ZoneId.of("Europe/Berlin"))));
		customers.add(new Customer(101, "Alfred", 21, "Braunschweig", 1300, LocalDate.now(ZoneId.of("Europe/Berlin"))));
		customers.add(new Customer(102, "Leonie", 98, "Braunschweig", 2000, LocalDate.now(ZoneId.of("Europe/Berlin"))));
		customers.add(new Customer(103, "Sabine", 77, "Braunschweig", 1750, LocalDate.now(ZoneId.of("Europe/Berlin"))));
		
		try
		{
			String sql = "INSERT INTO Customer (id,name,age,salary,address,start_date) VALUES (?,?,?,?,?,?)";
			
			for (Customer customer : customers) {
				
				PreparedStatement prepStatement = connection.prepareStatement(sql);
				
				prepStatement.setInt(1, customer.getId());
				prepStatement.setString(2,  customer.getName());
				prepStatement.setInt(3, customer.getAge());
				prepStatement.setInt(4, customer.getSalary());
				prepStatement.setString(5, customer.getAddress());
				prepStatement.setObject(6, customer.getStartDate());
				
				prepStatement.executeUpdate();
				
				if (prepStatement != null)
				try {
					prepStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}