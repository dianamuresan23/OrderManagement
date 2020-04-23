package logic;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import dao.ClientDAO;
import dao.ProductDAO;
import model.Product;

/**
 * ProductLogic face parte din nivelul business logic si face legatura intre
 * nivelul data access si presentation.Contine metode pentru logica operatiilor 
 * asupra tabelei product.
 * @author Toshiba
 *
 */
public class ProductLogic {

	public static Product findProductById(int id) {
		Product p= ProductDAO.findById(id);
		return p;
	}

	public static int insertProduct(Product product) {
		int id=100 + (int)(Math.random() * 100);
		
		Product f=ProductDAO.findById(id);
		while(f!=null)
		{
			id =100 + (int)(Math.random() * 100);
			f=ProductDAO.findById(id);
		}
		product.setId(id);
		
		return ProductDAO.insert(product);
	}
	
	public static ArrayList<Object> select()
	{
		ArrayList<Object> products=new ArrayList<Object>();
		products=ProductDAO.select();
		return products;
	}
	public static int deleteById(int productId)
	{
		Product c=ProductDAO.findById(productId);
		if (c!=null)
		{
			return ProductDAO.deleteById(productId);
		}
		else return -1;
	}
	public static int update(int productId,String name,String cant,String price)
	{
		Product p=ProductDAO.findById(productId);
		if (p!=null)
		{
		if (name.length()==0)
			name=p.getName();
		if (cant.length()==0)
			cant=String.valueOf(p.getCant());
		if (price.length()==0)
			price=String.valueOf(p.getPrice());
		float pr=Float.parseFloat(price);
		int cnt=Integer.parseInt(cant);
		return ProductDAO.update(productId, name, cnt, pr);
		}
		else return -1;
	}
}
