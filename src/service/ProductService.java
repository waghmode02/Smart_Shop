package service;

import java.util.List;

import model.Product;

public interface ProductService {

	void addProduct(Product product);
	
	int viewProductStock(int id);
	
//	Once users feature is enabled, uncomment below changes
//	List<Users> viewRegisteredUsers();

}
