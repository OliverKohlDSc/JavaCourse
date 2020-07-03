import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public final class CreateCustomerSql {
	
	private CreateCustomerSql() {
		
	}
	
	public static boolean execute() throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionString.getInstance().getConnection();
		
		Statement statement = connection.createStatement();
		
		String sql = "CREATE TABLE IF NOT EXISTS CUSTOMER" +
					 "(" +
					 "ID INT PRIMARY KEY," +
					 //"ID INT," +
					 "NAME VARCHAR(20)," +
					 "AGE NUMBER," +
					 "ADDRESS VARCHAR(20)," +
					 "SALARY NUMBER, " +
					 "START_DATE DATE" +
					 ")";
		
		return statement.execute(sql);
	}
}
