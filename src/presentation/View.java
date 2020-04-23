package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;
/**
 * View reprezinta vizualizarea datelor intr-o interfata grafica.
 * @author Toshiba
 *
 */
public class View {
	
	JButton clients=new JButton("Clients  ");
	JButton products=new JButton("Products  ");
	JButton orders=new JButton("Orders     ");
	JButton makeOrder=new JButton("Make Order");
	JButton createOrder=new JButton("Create Order");
	
	JButton createBill=new JButton("Create Bill");
	
	JFrame frame=new JFrame();
	JTable table;
	
	JButton addClient=new JButton("Add new Client");
	JButton addProduct=new JButton("Add new Product");
	JButton editClient=new JButton("Edit Client        ");
	JButton editProduct=new JButton("Edit Product         ");
	JButton deleteClient=new JButton("Delete Client    ");
	JButton deleteProduct=new JButton("Delete Product     ");
	JTextField idC=new JTextField(50);
	JTextField idP=new JTextField(50);
	JTextField nameC=new JTextField(50);
	JTextField addressC= new JTextField(50);
	JTextField emailC=new JTextField(50);
	

	
	JButton addC=new JButton("Add");
	JButton editC=new JButton("Edit");
	JButton editP=new JButton("Edit");
	JTextField nameP=new JTextField(50);
	JTextField priceP= new JTextField(50);
	JTextField cantP=new JTextField(50);
	
	JButton addP=new JButton("Add");

	
	/**
	 * Se construieste frame-ul principal cu toate optiunile.
	 */
	public View()
	{
		JFrame f=new JFrame("Warehouse Management");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(new Dimension(600,350));

		JPanel p=new JPanel();
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		
		
		JPanel p3=new JPanel();
		JLabel title=new JLabel("Warehouse management");
		title.setForeground(new Color(255,255,255));
		title.setFont(new Font("Tahoma",Font.BOLD,20));
		
		p3.setLayout(new BoxLayout(p3,BoxLayout.Y_AXIS));
		addClient.setBackground(new Color(255, 204, 204));
		addClient.setFont(new Font("Tahoma", Font.BOLD, 14));
		deleteClient.setBackground(new Color(255, 204, 204));
		deleteClient.setFont(new Font("Tahoma", Font.BOLD, 14));
		editClient.setBackground(new Color(255, 204, 204));
		editClient.setFont(new Font("Tahoma", Font.BOLD, 14));
	
		p3.add(addClient);
		p3.add(deleteClient);
		p3.add(editClient);
	
		
        JPanel p4=new JPanel();
		
		p4.setLayout(new BoxLayout(p4,BoxLayout.Y_AXIS));
		addProduct.setBackground(new Color(255, 204, 204));
		addProduct.setFont(new Font("Tahoma", Font.BOLD, 14));
		deleteProduct.setBackground(new Color(255, 204, 204));
		deleteProduct.setFont(new Font("Tahoma", Font.BOLD, 14));
		editProduct.setBackground(new Color(255, 204, 204));
		editProduct.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		p4.add(addProduct);
		p4.add(deleteProduct);
		p4.add(editProduct);
		
		JPanel pp=new JPanel();
		pp.setLayout(new BoxLayout(pp,BoxLayout.Y_AXIS));
		pp.setBackground( new Color(102, 0, 51) );
		pp.add(title);
		pp.add(makeOrder);
		pp.add(createBill);
		p2.setLayout(new BorderLayout(10,100));
		p1.setLayout(new BoxLayout(p1,BoxLayout.X_AXIS));
		p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
		p.add(clients);
		p.add(products);
		p.add(orders);
		
		
		clients.setBackground(new Color(255,255,255));
		products.setBackground(new Color(255,255,255));
		orders.setBackground(new Color(255,255,255));
		clients.setFont(new Font("Tahoma", Font.BOLD, 14));
	    products.setFont(new Font("Tahoma", Font.BOLD, 14));
	    orders.setFont(new Font("Tahoma", Font.BOLD, 14));
	    makeOrder.setBackground(new Color(255, 255, 255));
		makeOrder.setFont(new Font("Tahoma", Font.BOLD, 14));
		createOrder.setBackground(new Color(255, 255, 255));
		createOrder.setFont(new Font("Tahoma", Font.BOLD, 14));
		createBill.setBackground(new Color(255, 255, 255));
		createBill.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		p1.setBackground( new Color(102, 0, 51) );
		p3.setBackground( new Color(102, 0, 51) );
		p4.setBackground( new Color(102, 0, 51) );
		p.setBackground( new Color(102, 0, 51) );
		p2.add(p,BorderLayout.CENTER);
		p2.add(p3,BorderLayout.LINE_START);
		p2.add(p4,BorderLayout.LINE_END);
		p2.add(pp,BorderLayout.PAGE_START);
		
		nameC.setBackground(new Color(255, 204, 204));
		addressC.setBackground(new Color(255, 204, 204));
		emailC.setBackground(new Color(255, 204, 204));
		idC.setBackground(new Color(255, 204, 204));
	
		nameP.setBackground(new Color(255, 204, 204));
		idP.setBackground(new Color(255, 204, 204));
		priceP.setBackground(new Color(255, 204, 204));
		cantP.setBackground(new Color(255, 204, 204));
		
		addC.setBackground(new Color(255,255,255));
		addC.setFont(new Font("Tahoma", Font.BOLD, 14));
		editC.setBackground(new Color(255,255,255));
		editC.setFont(new Font("Tahoma", Font.BOLD, 14));
		addP.setBackground(new Color(255,255,255));
		addP.setFont(new Font("Tahoma", Font.BOLD, 14));
		editP.setBackground(new Color(255,255,255));
		editP.setFont(new Font("Tahoma", Font.BOLD, 14));
		f.setContentPane(p2);
		f.getContentPane().setBackground( new Color(102, 0, 51) );
		f.setVisible(true);
		
	}
	/**
	 * Metoda creaza un tabel folosind tehnica Reflection .
	 * Pentru primul obiect din lista am extras toate numele 
	 * atributelor care reprezinta header-ul tabelului.
	 * Apoi, pentru fiecare obiect din lista am extras valorile 
	 * atributelor si le-am inserat intr-o linie din tabel. 
	 * @param objects ArrayList de Object
	 * @return returneaza un obiect de tip JTable
	 */
	public JTable createTable(ArrayList<Object> objects)
	{
		JTable table;
		Object first=objects.get(0);
		ArrayList<String> columnNames=new ArrayList<String>();
		
		for (Field field: first.getClass().getDeclaredFields())
		{
			field.setAccessible(true);
		
				try {
		    String name=field.getName();
		    columnNames.add(name);
			}catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
			
		}
		Object[] columns=columnNames.toArray();
		Object [][] data=new Object[20][20];
		int i=0;
		for (Object obj:objects)
		{
			int k=0;
			for (Field field :obj.getClass().getDeclaredFields())
			{
				
				field.setAccessible(true);
				try
				{
					Object value=field.get(obj);
					data[i][k]=value;
				}catch (IllegalArgumentException e) {
					e.printStackTrace();
				}catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				k++;	
			}
			i++;
		}
	
		table=new JTable(data,columns);
		return table;
	}
	/**
	 * Metoda creeaza componenta JTable apeland metoda createTable
	 * si creeaza un JScrollPane care va fi suprascris in frame-ul this.
	 * @param objects ArrayList de Object
	 */
	public void createFrameTable(ArrayList<Object> objects)
	{

		JTable table=createTable(objects);
	    table.setBackground(new Color(255, 204, 204));
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		JTableHeader h = table.getTableHeader();
	    h.setBackground(new Color(102, 0, 51));
	    h.setForeground(new Color(255,255,255));
	    h.setFont(new Font("Tahoma",Font.BOLD,14));
		JScrollPane p=new JScrollPane(table);
		this.table=table;
	
		setFrame(p);

		
	}
	/**
	 * Metoda suprascrie frame-ul this adaugand texfield-uri
	 * pentru introducerea datelor ce se doresc inserate.
	 */
	public void createFrameInsertClient()
	{
		JPanel p=new JPanel();
		p.setLayout(new GridLayout(4,2));
		p.setBackground(new Color(102, 0, 51));
		JLabel name=new JLabel("Name");
		name.setForeground(new Color(255,255,255));
		name.setFont(new Font("Tahoma",Font.BOLD,14));
		JLabel address=new JLabel("Address");
		address.setFont(new Font("Tahoma",Font.BOLD,14));
		address.setForeground(new Color(255,255,255));
		JLabel email=new JLabel("Email");
		email.setFont(new Font("Tahoma",Font.BOLD,14));
		email.setForeground(new Color(255,255,255));
		p.add(name);
		p.add(nameC);
		p.add(address);
		p.add(addressC);
		p.add(email);
		p.add(emailC);
		p.add(addC);
		setFrame2(p);
	}
	/**
	 * Metoda suprascrie frame-ul this prin adaugarea tabelului de clienti
	 * si texfield-uri pentru actualizarea datelor unui client
	 * @param objects ArrayList de Object
	 */
	public void createFrameEditClient(ArrayList<Object> objects)
	{
		JPanel p=new JPanel();
		p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
		p.setBackground(new Color(102, 0, 51));
		
		JTable table=createTable(objects);
	    table.setBackground(new Color(255, 204, 204));
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		JTableHeader h = table.getTableHeader();
	    h.setBackground(new Color(102, 0, 51));
	    h.setForeground(new Color(255,255,255));
	    h.setFont(new Font("Tahoma",Font.BOLD,14));
		JScrollPane ps=new JScrollPane(table);
	
		this.table=table;
		
		p.add(ps);
		
		JPanel p1=new JPanel();
		p1.setLayout(new GridLayout(5,2));
		p1.setPreferredSize(new Dimension(300,150));
		p1.setBackground(new Color(102, 0, 51));
		JLabel id=new JLabel ("id");
		id.setForeground(new Color(255,255,255));
		id.setFont(new Font("Tahoma",Font.BOLD,14));
		JLabel name=new JLabel("Name");
		name.setForeground(new Color(255,255,255));
		name.setFont(new Font("Tahoma",Font.BOLD,14));
		JLabel address=new JLabel("Address");
		address.setFont(new Font("Tahoma",Font.BOLD,14));
		address.setForeground(new Color(255,255,255));
		JLabel email=new JLabel("Email");
		email.setFont(new Font("Tahoma",Font.BOLD,14));
		email.setForeground(new Color(255,255,255));
		
		p1.add(id);
		p1.add(idC);
		p1.add(name);
		p1.add(nameC);
		p1.add(address);
		p1.add(addressC);
		p1.add(email);
		p1.add(emailC);
		p1.add(editC);
		
		p.add(p1);
		
		setFrame2(p);
		
		
		
	}
	       
	/**
	 * Metoda suprascrie frame-ul this prin adaugarea tabelului 
	 * de produse si de textfield-uri pentru actualizarea datelor unui produs
	 * @param objects ArrayList de Object
	 */
	public void createFrameEditProduct(ArrayList<Object> objects)
	{
		JPanel p=new JPanel();
		p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
		p.setBackground(new Color(102, 0, 51));
		
		JTable table=createTable(objects);
	    table.setBackground(new Color(255, 204, 204));
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		JTableHeader h = table.getTableHeader();
	    h.setBackground(new Color(102, 0, 51));
	    h.setForeground(new Color(255,255,255));
	    h.setFont(new Font("Tahoma",Font.BOLD,14));
		JScrollPane ps=new JScrollPane(table);
		this.table=table;
		
		p.add(ps);
		
		JPanel p1=new JPanel();
		p1.setLayout(new GridLayout(5,2));
		p1.setPreferredSize(new Dimension(300,150));
		p1.setBackground(new Color(102, 0, 51));
		JLabel id=new JLabel ("id");
		id.setForeground(new Color(255,255,255));
		id.setFont(new Font("Tahoma",Font.BOLD,14));
		JLabel name=new JLabel("Name");
		name.setForeground(new Color(255,255,255));
		name.setFont(new Font("Tahoma",Font.BOLD,14));
		JLabel price=new JLabel("Price");
		price.setFont(new Font("Tahoma",Font.BOLD,14));
		price.setForeground(new Color(255,255,255));
		JLabel cant=new JLabel("Cantity");
		cant.setFont(new Font("Tahoma",Font.BOLD,14));
		cant.setForeground(new Color(255,255,255));
		
		p1.add(id);
		p1.add(idP);
		p1.add(name);
		p1.add(nameP);
		p1.add(price);
		p1.add(priceP);
		p1.add(cant);
		p1.add(cantP);
		p1.add(editP);
		
		p.add(p1);
		
		setFrame2(p);		
	}

	/**
	 * Metoda suprascrie frame-ul this prin adaugarea unor textfield-uri
	 * pentru introducerea datelor ce trebuie inserate
	 */
	public void createFrameInsertProduct()
	{
		JPanel p=new JPanel();
		p.setLayout(new GridLayout(4,2));
		p.setBackground(new Color(102, 0, 51));
		
		JLabel name=new JLabel("Name");
		name.setForeground(new Color(255,255,255));
		name.setFont(new Font("Tahoma",Font.BOLD,14));
		JLabel price=new JLabel("Price");
		price.setFont(new Font("Tahoma",Font.BOLD,14));
		price.setForeground(new Color(255,255,255));
		JLabel cant=new JLabel("Cantity");
		cant.setFont(new Font("Tahoma",Font.BOLD,14));
		cant.setForeground(new Color(255,255,255));
		
		p.add(name);
		p.add(nameP);
		p.add(price);
		p.add(priceP);
		p.add(cant);
		p.add(cantP);
		p.add(addP);
		
		setFrame2(p);
	}
	/**
	 * Metoda seteaza frame-ul this cu un JPanel
	 * @param p obiect de tip JPanel
	 */
	public void setFrame2(JPanel p)
	{
		this.frame.setSize(new Dimension(600,350));
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLocation(600, 100);
		this.frame.setContentPane(p);
		this.frame.setVisible(true);
		
	}
	/**
	 * Metoda seteaza frame-ul this cu un JScrollPane
	 * @param p obiect de tip JScrollPane
	 */
	public void setFrame(JScrollPane p)
	{
		this.frame.setSize(new Dimension(600,350));
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLocation(600, 100);
		this.frame.setContentPane(p);
		this.frame.setVisible(true);
		
	}
	/**
	 * Metoda suprascrie frame-ul this prin adaugarea de textfield-uri
	 * pentru introducerea datelor necesare la crearea unei comenzi
	 */
	public void createFrameOrder()
	{
		
		JPanel p=new JPanel();
		p.setLayout(new GridLayout(4,2));
		p.setBackground(new Color(102, 0, 51));
		
		JLabel clientId=new JLabel("ClientId");
		clientId.setForeground(new Color(255,255,255));
		clientId.setFont(new Font("Tahoma",Font.BOLD,14));
		JLabel productId=new JLabel("ProductId");
		productId.setFont(new Font("Tahoma",Font.BOLD,14));
		productId.setForeground(new Color(255,255,255));
		JLabel cant=new JLabel("Cantity");
		cant.setFont(new Font("Tahoma",Font.BOLD,14));
		cant.setForeground(new Color(255,255,255));
		
		p.add(clientId);
		p.add(idC);
		p.add(productId);
		p.add(idP);
		p.add(cant);
		p.add(cantP);
		p.add(createOrder);
		
		setFrame2(p);
		
		
	}
	public  void showMessage(String message)
	{
		JOptionPane.showMessageDialog(null,message);
	}
	public  String inputDialog(String message)
	{
		return JOptionPane.showInputDialog(null,message);
	}
	public JButton getCreateBill()
	{
		return createBill;
	}
	public JButton getMakeOrder() {
		return makeOrder;
	}
	public JButton getCreateOrder()
	{
		return createOrder;
	}
	public JButton getClients() 
	{
		return clients;
	}
	public JButton getProducts()
	{
		return products;
	}
	public JButton getOrders()
	{
		return orders;
	}
	public JButton getAddClient()
	{
		return addClient;
	}
	public JButton getEditClient()
	{
		return editClient;
	}
	public JButton getDeleteClient()
	{
		return deleteClient;
	}
	public JButton getEditProduct()
	{
		return editProduct;
	}
	public JButton getDeleteProduct()
	{
		return deleteProduct;
	}
	public JButton getAddProduct()
	{
		return addProduct;
	}
	public JButton getAddC()
	{
		return addC;
	}
	public JButton getAddP()
	{
		return addP;
	}
	public JButton getEditC()
	{
		return editC;
	}
	public JButton getEditP()
	{
		return editP;
	}
	public String getClientId()
	{
		return idC.getText();
	}
	public String getProductId()
	{
		return idP.getText();
	}
	public String getClientName()
	{
		return nameC.getText();
	}
	public String getClientAddress()
	{
		return addressC.getText();
	}
	public String getClientEmail()
	{
		return emailC.getText();
	}
	public String getProductName()
	{
		return nameP.getText();
	}
	public String getProductPrice()
	{
		return priceP.getText();
	}
	public String getProductCant()
	{
		return cantP.getText();
	}
	     /**
	      * Metoda adauga action listeners pentru butoane.
	      * @param listen obiect de tip ActionListener
	      */
    void addListener(ActionListener listen)
	
	{

		clients.addActionListener(listen);
		products.addActionListener(listen);
		orders.addActionListener(listen);
		addClient.addActionListener(listen);
		addC.addActionListener(listen);
		addP.addActionListener(listen);
		addProduct.addActionListener(listen);
		deleteClient.addActionListener(listen);
		deleteProduct.addActionListener(listen);
		editClient.addActionListener(listen);
		editProduct.addActionListener(listen);
		editC.addActionListener(listen);
		editP.addActionListener(listen);
		makeOrder.addActionListener(listen);
		createOrder.addActionListener(listen);
		createBill.addActionListener(listen);
		
	}
	
	
}
