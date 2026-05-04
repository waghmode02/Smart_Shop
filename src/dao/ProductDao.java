package dao;

import java.util.List;

import model.Product;
import model.User;

public interface ProductDao {

	boolean save(Product product);
	
	int getProductStock(int id);
	
	List<User> getRegisteredUsers();
	
	List<Product> getPurchaseHistory(String username);
	
	void updateProductDetails(int id, String name, String description, double price, int quantity);
}
