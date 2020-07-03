import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Protect our class from being inherited
public final class ConnectionString {
	private static ConnectionString connectionString = null;
	private static Connection connection = null;
	
	// This should be loaded from a configuration file
	private static final String DRIVER = "org.h2.Driver";
	private static final String DB_USER = "sa";
	private static final String DB_PASS = "1234";
	private static final String CONNECTIONSTRING = "jdbc:h2:~/h2demo;AUTO_SERVER=TRUE";
	
	// Protect our class by declaring a private constructor
	// This prevents the user from using our class in the following way:
	// ConnectionString myConnectionString = new ConnectionString();
	private ConnectionString() {
		
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	// Return our Singleton
	public static ConnectionString getInstance() throws ClassNotFoundException, SQLException {
		// check if there's never been any other instance
		if (connectionString == null) {
			// create a new instance
			connectionString = new ConnectionString();
			
			// Load our SQL driver
			Class.forName(DRIVER);
			System.out.println("SQL driver for H2 loaded.");
			
			// Open the connection to the database
			connection = DriverManager.getConnection(CONNECTIONSTRING, DB_USER, DB_PASS);
			
			System.out.println("Opened connection to DB server.");
		}
		
		// return our Singleton
		return connectionString;
	}
}