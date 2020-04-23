package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Statement;

import connection.ConnectionFactory;
import model.Order;

/**
 * OrderDAO este o clasa la nivelul data access si contine interogarile ce se efectueaza asupra
 * tabelei comenzi si metode care acceseaza baza de date si executa interogarile.
 * @author Toshiba
 *
 */
public class OrderDAO {
	protected static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO comenzi (id,productId,cant,clientId,sum)"
			+ " VALUES (?,?,?,?,?)";
	private final static String findStatementString = "SELECT * FROM comenzi where id = ?";
	private final static String selectStatementString= "SELECT * FROM comenzi";
	
	/**
	 * Metoda executa un query de tipul insert si primeste ca parametru un obiect de tipul Order
	 * din care se vor extrage informatiile ce trebuie inserate in tabel
	 * @param order  obiect de tip Order
	 * @return  returneaza -1 daca inserarea nu a avut succes
	 */
	public static int insert(Order order) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			String id=String.valueOf(order.getId());
			String product_id=String.valueOf(order.getProductId());
			String cant=String.valueOf(order.getCant());
			String client_id=String.valueOf(order.getClientId());
			String sum=String.valueOf(order.getSum());
			insertStatement.setString(1, id);
			insertStatement.setString(2, product_id);
			insertStatement.setString(3, cant);
			insertStatement.setString(4, client_id);
			insertStatement.setString(5, sum);
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrderDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;

}
	/**
	 * Metoda primeste ca si parametru un id si va executa un query de tip select ce va alege
	 * inregistrarea cu id-ul cautat
	 * @param orderId id-ul comenzii
	 * @return  returneaza un obiect null daca nu a fost gasita comanda, si daca s-a gasit se returneaza obiectul Order
	 */
	public static Order findById(int orderId) {
		Order toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1,orderId);
			rs = findStatement.executeQuery();
			if (rs.next()==false)
				return null;

			String p=rs.getString("productId");
			String c=rs.getString("cant");
			String cl=rs.getString("clientId");
			String s=rs.getString("sum");
			
			int prod_id=Integer.parseInt(p);
			int cant=Integer.parseInt(c);
			int client_id=Integer.parseInt(cl);
			float sum=Float.parseFloat(s);
			
			toReturn = new Order(orderId,prod_id,cant,client_id,sum);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"OrderDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	/**
	 * Metoda executa un query de tip select*
	 * @return se vor returna toate inregistrarile existente in table intr-un ArrayList de Object
	 */
	 public static ArrayList<Object> select() {
			ArrayList<Object> toReturn = new ArrayList<Object>();

			Connection dbConnection = ConnectionFactory.getConnection();
			PreparedStatement selectStatement = null;
			ResultSet rs = null;
			try {
				selectStatement = dbConnection.prepareStatement(selectStatementString);
				rs = selectStatement.executeQuery();
				
				while(rs.next()) 
				{
		        int orderId=rs.getInt("id");
		        int productId=rs.getInt("productId");
		        int cant=rs.getInt("cant");
		        int clientId= rs.getInt("clientId");
		
				
			    String sum=rs.getString("sum");
			    float s=Float.parseFloat(sum);
				Order p = new Order(orderId,productId,cant,clientId,s);
				Object o=(Object)p;
				toReturn.add(o);
				}
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"OrderDAO:select " + e.getMessage());
			} finally {
				ConnectionFactory.close(rs);
				ConnectionFactory.close(selectStatement);
				ConnectionFactory.close(dbConnection);
			}
			
			return toReturn;
		}

}