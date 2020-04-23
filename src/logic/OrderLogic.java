package logic;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import dao.ClientDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import model.Order;
import model.Product;
/**
 * OrderLogic face parte din nivelul business logic si face legatura intre
 * nivelul data access si presentation.Contine metode pentru logica operatiilor
 * asupra tabelei comenzi.
 * @author Toshiba
 *
 */

public class OrderLogic {
	/**
	 * Metoda cauta o comanada dupa id apeland metoda findById din OrderDAO
	 * @param id id-ul comenzii
	 * @return obiect de tip Order care este null daca nu exista comanda cautata
	 */
	public static Order findOrderById(int id) {
		Order o= OrderDAO.findById(id);
		return o;
	}

	/**
	 * Metoda insereaza o comanda in tabel.
	 * Genereaza un id random pana cand acesta nu exista in tabel.
	 * Se seteaza id-ul comenzii
	 * Se insereaza comanda cu metoda insert din OrderDAO
	 * @param order obiect de tipul Order
	 * @return returneaza -1 daca inserarea nu a avut succes
	 */
	public static int insertOrder(Order order) {
        int id=100 + (int)(Math.random() * 100);
		
		Order f=OrderDAO.findById(id);
		while(f!=null)
		{
			id =100 + (int)(Math.random() * 100);
			f=OrderDAO.findById(id);
		}
		order.setId(id);
		
		return OrderDAO.insert(order);
		
	}
	/**
	 * Metoda selecteaza toata inregistrarile din tabel
	 * @return returneaza un ArrayList de Object care contine toate comenzile 
	 */
	public static ArrayList<Object> select()
	{
		ArrayList<Object> orders=new ArrayList<Object>();
		orders=OrderDAO.select();
		return orders;
	}

}
