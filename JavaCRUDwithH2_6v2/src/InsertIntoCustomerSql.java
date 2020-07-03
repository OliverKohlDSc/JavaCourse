import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public final class InsertIntoCustomerSql {
	
	// Protect our class
	private InsertIntoCustomerSql() {
		// Instantiation not allowed
	}
	
	public static int executeUpdate() throws ClassNotFoundException, SQLException {
		int rowsAffeced = 0;
		List<Customer> customers = new ArrayList<>();

		customers.add(new Customer(100, "Herbert", 33, "Braunschweig", 1200, LocalDate.now(ZoneId.of("Europe/Berlin"))));
		customers.add(new Customer(101, "Alfred", 21, "Köln", 1300, LocalDate.now(ZoneId.of("Europe/Berlin"))));
		customers.add(new Customer(102, "Leonie", 98, "Hannover", 2000, LocalDate.now(ZoneId.of("Europe/Berlin"))));
		customers.add(new Customer(103, "Sabine", 77, "Berlin", 1750, LocalDate.of(2020, 6, 18)));
		
		Connection connection = ConnectionString.getInstance().getConnection();
	
		String sql = "INSERT INTO Customer (id,name,age,salary,address,start_date) VALUES (?,?,?,?,?,?)";
		
		for (Customer customer : customers) {
			
			PreparedStatement prepStatement = connection.prepareStatement(sql);
			
			prepStatement.setInt(1, customer.getId());
			prepStatement.setString(2,  customer.getName());
			prepStatement.setInt(3, customer.getAge());
			prepStatement.setInt(4, customer.getSalary());
			prepStatement.setString(5, customer.getAddress());
			prepStatement.setObject(6, customer.getStartDate());
			
			rowsAffeced += prepStatement.executeUpdate();
			
			if (prepStatement != null) {
				try {
					prepStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return rowsAffeced;
	}
}