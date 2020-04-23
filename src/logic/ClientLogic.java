package logic;

import java.util.ArrayList;
/**
 * ClientLogic face parte din nivelul de business logic si face legatura dintre
 * nivelul data access si presentation.Contine metode pentru logica operatiilor asupra
 * tabelei client.
 */

import dao.ClientDAO;
import model.Client;

public class ClientLogic {
	/**
	 * Metoda cauta un client dupa id apeland metoda findById din ClientDAO
	 * @param id id-ul clientului
	 * @return obiect de tip Client care este null daca nu exista clientul cautat
	 */
	public static Client findClientById(int id) {
		Client c = ClientDAO.findById(id);
		return c;
	}

	/**
	 * Metoda insereaza un client in tabel
	 * Se genereaza id-uri random pana cand se gaseste un id care nu exista in tabel
	 * Se seteaza id-ul clientului
	 * @param client obiect de tip Client care nu contine id
	 * @return returneaza -1 daca inserarea nu a avut succes
	 */
	public static int insertClient(Client client) {
		int id=100 + (int)(Math.random() * 100);
		
		Client f=ClientDAO.findById(id);
		while(f!=null)
		{
			id =100 + (int)(Math.random() * 100);
			f=ClientDAO.findById(id);
		}
		client.setId(id);
		return ClientDAO.insert(client);
	}
	/** 
	 * Metoda selecteaza toate inregistrarile din tabela prin metoda select() din ClientDAO
	 * @return returneaza un ArrayList de Object care contine toti clientii
	 */
	
	public static ArrayList<Object> select()
	{
		ArrayList<Object> clients=new ArrayList<Object>();
		clients=ClientDAO.select();
		return clients;
	}
	/**
	 * Metoda sterge un client din tabel
	 * Se verifica daca clientul cu id-ul primit ca si parametru exista
	 * Daca exista, se sterge folosind metoda deleteById din ClientDAO
	 * @param clientId id-ul clientului
	 * @return -1 daca clientul nu exista sau daca nu s-a putut face stergerea
	 */
	public static int deleteById(int clientId)
	{
		Client c=ClientLogic.findClientById(clientId);
		if (c!=null)
		{
			return ClientDAO.deleteById(clientId);
		}
		else
			return -1;
	}
	/**
	 * Metoda actualizeaza un client dupa id
	 * Se verifica daca clientul exista, apoi se verific daca utilizatorul a schimbat anumite
	 * valori.Daca nu le-a schimbat acestea raman la fel.
	 * @param clientId id-ul clientului
	 * @param name  numele clientului
	 * @param address  adresa clientului
	 * @param email  email-ul clientului
	 * @return returneaza -1 daca clientul nu exista sau daca actualizarea nu a avut succes
	 */
	public static int update(int clientId,String name,String address,String email)
	{
		Client c=ClientDAO.findById(clientId);
		if (c!=null)
		{
		if (name.length()==0)
			name=c.getName();
		if (address.length()==0)
			address=c.getAddress();
		if (email.length()==0)
			email=c.getEmail();
		return ClientDAO.update(clientId, name, address, email);
		}
		else return -1;
	}

}

