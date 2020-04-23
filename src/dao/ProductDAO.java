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
import model.Product;

/**
 * ProductDAO este o clasa la nivelul data access si contine interogarile ce se efectueaza asupra
 * tabelei product si metode ce acceseaza baza de date si executa interogarile.
 * @author Toshiba
 *
 */
public class ProductDAO {

	protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO product (id,name,price,cant)"
			+ " VALUES (?,?,?,?)";
	private final static String findStatementString = "SELECT * FROM product where id = ?";
	private final static String deleteStatementString="DELETE FROM product WHERE id= ?";
	private final static String selectStatementString= "SELECT * FROM product";
	private final static String updateStatementString="UPDATE product SET name=?, price=?, cant=? WHERE id=? ";
	
	/**
	 * Metoda primeste ca si parametri datele unui produs si va actualiza produsul cu id-ul corespunzator
	 * @param productId id-ul produsului
	 * @param name  numele produsului
	 * @param cant  cantitatea produsului
	 * @param price  pretul produsului
	 * @return  returneaza -1 daca actualizarea nu a avut succes
	 */
	
      public static int update(int productId,String name,int cant, float price) {
		

		Connection dbConnection = ConnectionFactory.getConnection();
		int updated=-1;
		PreparedStatement updateStatement = null;
		ResultSet rs = null;
		try {
		     updateStatement = dbConnection.prepareStatement(updateStatementString);
			updateStatement.setString(1, name);
			String pr=String.valueOf(price);
			updateStatement.setString(2, pr);

			updateStatement.setInt(3, cant);
			updateStatement.setInt(4,productId);
		updated=updateStatement.executeUpdate();
		
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ClientDAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		return updated;
	}
	
      /**
       * Metoda primeste ca si parametru id-ul unui produs si va executa un query de tip delete
       * care va sterge produsul cu id dorit din tabel
       * @param productId id-ul produsului
       * @return returneaza -1 daca stergerea nu a avut succes
       */
    public static int deleteById(int productId) {
		

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		
		int deleted=-1;
		try {
			deleteStatement = dbConnection.prepareStatement(deleteStatementString);
			deleteStatement.setInt(1, productId );
			deleted = deleteStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ProductDAO:delete " + e.getMessage());
		} finally {
			
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		
		return deleted;
	}
    /**
     * Metoda executa un query de tip select *
     * @return returneaza toate inregistrarile existente in tabel intr-un ArrayList de Object
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
        int productId=rs.getInt("id");
		String name = rs.getString("name");
		String price = rs.getString("price");
	    int cant = rs.getInt("cant");
	    float pr=Float.parseFloat(price);
		Product p = new Product(productId, name, pr, cant);
		Object o=(Object)p;
		toReturn.add(o);
		}
	} catch (SQLException e) {
		LOGGER.log(Level.WARNING,"ProductDAO:select " + e.getMessage());
	} finally {
		ConnectionFactory.close(rs);
		ConnectionFactory.close(selectStatement);
		ConnectionFactory.close(dbConnection);
	}
	
	return toReturn;
}

    /**
     * Metoda executa un query de tip insert si primeste ca si parametru un obiect de tipul Product
     * din care va extrage informatiile ce se vor introduce in tabel
     * @param product obiect de tip Product
     * @return returneaza -1 daca inserarea nu a avut succes
     */

	public static int insert(Product product) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int inserted = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			String price=String.valueOf(product.getPrice());
			int cant=product.getCant();
			String id=String.valueOf(product.getId());
			insertStatement.setString(1, id);
			insertStatement.setString(2, product.getName());
			insertStatement.setString(3, price);
			insertStatement.setInt(4, cant);
			inserted=insertStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return inserted;
	}
	/**
	 * Metoda executa un query de tip select care va alege inregistrarea cu id-ul primit ca parametru
	 * @param productId id-ul produsului
	 * @return returneaza un obiect de tipul Product care reprezinta produsul cautat sau nu-l daca
	 * acesta nu exista
	 */
	public static Product findById(int productId) {
		Product toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1,productId);
			rs = findStatement.executeQuery();
			
			if (rs.next()==false)
				return null;

			String name = rs.getString("name");
			String price=rs.getString("price");
			int cant=rs.getInt("cant");
			float p=Float.parseFloat(price);
			
			
			toReturn = new Product(productId,name, p, cant);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ProductDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

}
