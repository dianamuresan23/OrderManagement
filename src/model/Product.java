package model;


/**
 * Product contine atributele tabelei product din baza de date, gettere si settere
 * @author Toshiba
 *
 */

public class Product {
	private int id;
	private String name;
	private float price;
	private int cant;
	
	public Product(int id,String name,float price,int cant)
	{
		super();
		this.id=id;
		this.name=name;
		this.price=price;
		this.cant=cant;
	}
	public Product(String name,float price,int cant)
	{
		super();
		this.name=name;
		this.price=price;
		this.cant=cant;
	}
	
	public int getId()
	{
		return this.id;
	}
	public void setId(int id)
	{
		this.id=id;
	}
	
	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	
	public float getPrice()
	{
		return this.price;
	}
	public void setPrice(float price)
	{
		this.price=price;
	}
	public int getCant()
	{
		return this.cant;
	}
	public void setCant(int cant)
	{
		this.cant=cant;
	}
}
