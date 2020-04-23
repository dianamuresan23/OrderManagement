package connection;

import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
/**
 * ConnectionFactory stabileste conexiunea la baza de date
 * @author Toshiba
 *
 */
public class ConnectionFactory {
	private static final Logger LOGGER=Logger.getLogger(ConnectionFactory.class.getName());
	private static final String DRIVER="com.mysql.jdbc.Driver";
	private static final String DBURL="jdbc:mysql://127.0.0.1:3306/schooldb?autoReconnect=true&useSSL=false";
	private static final String USER="root";
	private static final String PASS="tematp";
	
	private static ConnectionFactory singleInstance=new ConnectionFactory();
	
	private ConnectionFactory()
	{
		try {
			Class.forName(DRIVER);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private Connection createConnection()
	{
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DBURL, USER, PASS);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
			e.printStackTrace();
		}
		return connection;
		
	}
	public static Connection getConnection()
	{
		return singleInstance.createConnection();
		
	}
	public static void close(Connection conn)
	{
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
			}
		}
		
	}
	public static void close(Statement statement)
	{
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
			}
		}
		
	}
	public static void close(ResultSet resultSet)
	{
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
			}
		}
		
	}
	
	

}

