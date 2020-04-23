package model;
/**
 * Order contine atributele corespunzatoare tabele comenzi din baza de date, gettere si settere
 * @author Toshiba
 *
 */
public class Order {
	private int id;
	private int productId;
	private int cant;
	private int clientId;
	private float sum;
	
	public Order(int id,int product_id,int cant,int client_id,float sum)
	{
		super();
		this.id=id;
		this.productId=product_id;
		this.cant=cant;
		this.clientId=client_id;
		this.sum=sum;
	}
	public Order(int product_id,int cant,int client_id,float sum)
	{
		super();
		this.productId=product_id;
		this.cant=cant;
		this.clientId=client_id;
		this.sum=sum;
	}
	public int getId()
	{
		return this.id;
	}
	public void setId(int id)
	{
		this.id=id;
	}
	public int getProductId()
	{
		return this.productId;
	}
	public void setProductId(int product_id)
	{
		this.productId=product_id;
	}
	public int getCant()
	{
		return this.cant;
	}
	public void setCant(int cant)
	{
		this.cant=cant;
	}
	
	public int getClientId()
	{
		return this.clientId;
	}
	public void setClientId(int client_id)
	{
		this.clientId=client_id;
	}
	public float getSum()
	{
		return this.sum;
	}
	public void setSum(float sum)
	{
		this.sum=sum;
	}

}
