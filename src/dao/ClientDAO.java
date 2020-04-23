package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Statement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionFactory;
import model.Client;

/** 
 * ClientDAO este o clasa la nivelul data access si contine interogarile ce se efectueaza
 * asupra tabelei client si metodele care acceseaza baza de date si executa interogarile.
 * @author Toshiba
 *
 */
public class ClientDAO {

	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO client (id,name,address,email)"
			+ " VALUES (?,?,?,?)";
	private final static String findStatementString = "SELECT * FROM client where id = ?";
	private final static String selectStatementString= "SELECT * FROM client";
	private final static String deleteStatementString="DELETE FROM client WHERE id= ?";
	private final static String updateStatementString="UPDATE client set name=?, address=?, email=? WHERE id=? ";
	
	/**
	 * Metoda primeste ca parametri datele unui client, si executa query-ul de update dupa id
	 * @param clientId id-ul clientului
	 * @param name   numele clientului 
	 * @param address  adresa clientului
	 * @param email    email-ul clientului
	 * @return    returneaza -1 daca interogarea nu s-a executat cu succes
	 */
	
	public static int update(int clientId,String name,String address, String email) {
		

		Connection dbConnection = ConnectionFactory.getConnection();
		int updated=-1;
		PreparedStatement updateStatement = null;
		ResultSet rs = null;
		try {
		     updateStatement = dbConnection.prepareStatement(updateStatementString);
			updateStatement.setString(1, name);
			updateStatement.setString(2, address);
			updateStatement.setString(3, email);
			updateStatement.setInt(4,clientId);
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
	 * Metoda executa un query de tipul delete dupa id-ul clientului
	 * @param clientId id-ul clientului
	 * @return  returneaza -1 daca clientul nu a fost sters
	 */
	
	public static int deleteById(int clientId) {
		

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		
		int deleted=-1;
		try {
			deleteStatement = dbConnection.prepareStatement(deleteStatementString);
			deleteStatement.setInt(1, clientId );
			deleted = deleteStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ClientDAO:delete " + e.getMessage());
		} finally {
			
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		
		return deleted;
	}
	/**
	 * Metoda executa un query de tipul select * 
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
            int clientId=rs.getInt("id");
			String name = rs.getString("name");
			String address = rs.getString("address");
			String email = rs.getString("email");
			Client c = new Client(clientId, name, email, address);
			Object o=(Object)c;
			toReturn.add(o);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ClientDAO:select " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return toReturn;
	}
	/**
	 * Metoda primeste ca si parametru id-ul clientului si selecteaza inregistrarea care corespunde cu acest id
	 * @param clientId id-ul clientului
	 * @return   returneaza un obiect de tipul Client ce reprezinta clientul cu id-ul cautat sau
	 * null daca nu exista
	 */
    
	public static Client findById(int clientId) {
		Client toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, clientId);
			rs = findStatement.executeQuery();
			
            if (rs.next()==false)
            {
            	return null;
            }
			String name = rs.getString("name");
			String address = rs.getString("address");
			String email = rs.getString("email");
			toReturn = new Client(clientId, name, email,address);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ClientDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
    /**
     * Metoda executa un query de tipul insert si primeste ca parametru un obiect de tipul Client 
     * din care vor fi extrase informatiile ce trebuie introduse in tabel
     * @param client  obiect de tipul Client
     * @return   returneaza -1 daca inserarea nu s-a executat cu succes
     */
	public static int insert(Client client) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			String id=String.valueOf(client.getId());
			insertStatement.setString(1,id);
			insertStatement.setString(2, client.getName());
			insertStatement.setString(3, client.getAddress());
			insertStatement.setString(4, client.getEmail());
			insertedId=insertStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
}
