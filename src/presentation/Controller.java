package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import logic.ClientLogic;
import logic.OrderLogic;
import logic.ProductLogic;
import model.Client;
import model.Order;
import model.Product;

/**
 * Controller actualizeaza frame-ul atunci cand se alege o anumita operatie.
 * @author Toshiba
 *
 */
public class Controller {
	private View view;
	/**
	 * Se adauga listeners pentru butoanele din view
	 * @param view obiect View
	 */
	public Controller(View view)
	{
		this.view=view;
		this.view.addListener(new Listener());
	}
	/**
	 * Metoda scrie intr-un fisier factura unei comenzi 
	 * @param fileContent obiect fileContent
	 * @throws IOException IOException
	 */
	public void writeBill(String fileContent) throws IOException
	{
	    String[] words=fileContent.split(" ");
	    
	    BufferedWriter writer = new BufferedWriter(new FileWriter("bill.txt"));
	    
	    for (String w:words)
	    {
	    writer.write(w);
	    writer.newLine();
	    }
	    
	    writer.close();
	}

	class Listener implements ActionListener
	{
		/**
		 *Se decide ce trebuie sa faca aplicatia la
		 *apasarea unui anumit buton.
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource()==view.getClients())
			{
				ArrayList<Object> objects=new ArrayList<Object>();
				objects=ClientLogic.select();
				view.createFrameTable(objects);
				
			}
			if (e.getSource()==view.getProducts())
			{
				ArrayList<Object> objects=new ArrayList<Object>();
				objects=ProductLogic.select();
				view.createFrameTable(objects);
			}
			if (e.getSource()==view.getOrders())
			{
				ArrayList<Object> objects=new ArrayList<Object>();
				objects=OrderLogic.select();
				view.createFrameTable(objects);
				
			}
			if (e.getSource()==view.getAddClient())
			{
				view.createFrameInsertClient();
			}
			if (e.getSource()==view.getAddC())
			{
				String name=view.getClientName();
				String address=view.getClientAddress();
				String email=view.getClientEmail();
				Client c=new Client(name,email,address);
				int inserted=ClientLogic.insertClient(c);			
				if (inserted==-1)
				{
					view.showMessage("Client could not be inserted");
				}
			}
			if (e.getSource()==view.getAddProduct())
			{
				view.createFrameInsertProduct();
			}
			if (e.getSource()==view.getAddP())
			{
				String name=view.getProductName();
				String price=view.getProductPrice();
				String cant=view.getProductCant();
				float p=Float.parseFloat(price);
				int cnt=Integer.parseInt(cant);
				Product c=new Product(name,p,cnt);
				int inserted=ProductLogic.insertProduct(c);
				if (inserted==-1)
				{
					view.showMessage("Product could not be inserted");
				}
			}
			if (e.getSource()==view.getDeleteClient())
			{
				String id=view.inputDialog("Introduceti id-ul clientului!");
				int i=Integer.parseInt(id);
				int deleted=ClientLogic.deleteById(i);
				if (deleted==-1)
				{
					view.showMessage("Client could not be deleted");
				}
				
			}
			if (e.getSource()==view.getDeleteProduct())
			{
				String id=view.inputDialog("Introduceti id-ul produsului!");
				int i=Integer.parseInt(id);
				int deleted=ProductLogic.deleteById(i);
				if (deleted==-1)
				{
					view.showMessage("Product could not be deleted");
				}
				
			}
			if (e.getSource()==view.getCreateBill())
			{
				String id=view.inputDialog("Introduceti id-ul comenzii!");
				int i=Integer.parseInt(id);
				Order ord=OrderLogic.findOrderById(i);
				Product prod=ProductLogic.findProductById(ord.getProductId());
				Client client=ClientLogic.findClientById(ord.getClientId());
				if (ord==null || prod==null || client ==null)
				{
					view.showMessage("Could not create bill");
				}
				else {
				String content = "Order_number:" + ord.getId()+" \n\n\n"+
				                 "Name:"+client.getName()+ "\n"
				                 +" Address:"+client.getAddress()+"\n"
				                 +" Product:"+prod.getName()+"\n"
				                 +" Price:"+ord.getSum()+"\n";
				
				try {
					writeBill(content);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				
			}
			if (e.getSource()==view.getEditClient())
			{
				ArrayList<Object> objects=new ArrayList<Object>();
				objects=ClientLogic.select();
				view.createFrameEditClient(objects);
				
			}
			if (e.getSource()==view.getEditC())
			{
				String idc=view.getClientId();
				int id=Integer.parseInt(idc);
				String name=view.getClientName();
				String address=view.getClientAddress();
				String email=view.getClientEmail();
				int updated=ClientLogic.update(id, name, address, email);	
				if (updated==-1)
				{
					view.showMessage("Could not update client");
				}
			}
			if (e.getSource()==view.getEditProduct())
			{
				ArrayList<Object> objects=new ArrayList<Object>();
				objects=ProductLogic.select();
				view.createFrameEditProduct(objects);
			}
			if (e.getSource()==view.getEditP())
			{
				String idP=view.getProductId();
				int id=Integer.parseInt(idP);
				String name=view.getProductName();
				String cant =view.getProductCant();
				String price=view.getProductPrice();
				int updated=ProductLogic.update(id, name, cant,price);
				if (updated==-1)
				{
					view.showMessage("Could not update client");
				}
						
			}
			if (e.getSource()==view.getMakeOrder())
			{

				view.createFrameOrder();
				
			}
			if (e.getSource()==view.getCreateOrder())
			{
				String clientId=view.getClientId();
				String productId=view.getProductId();
				String cantity=view.getProductCant();
				int idC=Integer.parseInt(clientId);
				int cant=Integer.parseInt(cantity);
				int idP=Integer.parseInt(productId);
				Client c=ClientLogic.findClientById(idC);
				Product p=ProductLogic.findProductById(idP);
				if (p==null || c==null)
				{
					view.showMessage("Could not create order");
					return;
				}
				if (p.getCant()<cant)
					view.showMessage("Not enough products");
				else
				{
					float sum=cant*p.getPrice();
					Order ord=new Order(idP,cant,idC,sum);
					int inserted=OrderLogic.insertOrder(ord);
					int newcant=p.getCant()-cant;
					String cnt=String.valueOf(newcant);
					String pr=String.valueOf(p.getPrice());
					int updated=ProductLogic.update(idP, p.getName(), cnt,pr);
					
				}
			}
		}
	}
	
	

}
