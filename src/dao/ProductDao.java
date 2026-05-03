package dao;

import java.util.List;

import model.Product;

public interface ProductDao {

	boolean save(Product product);
	
	int getProductStock(int id);
	
//	Once users feature is enabled, uncomment below changes
//	List<Users> getRegisteredUsers();
}
