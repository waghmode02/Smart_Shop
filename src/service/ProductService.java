package service;

import java.util.List;

import model.Product;
import model.User;

public interface ProductService {

	void addProduct(Product product);

	int viewProductStock(int id);

	List<User> viewRegisteredUsers();

	List<Product> viewPurchaseHistory(String username);

	void updateProductDetails(int id, String name, String description, double price, int quantity);

	void deleteProduct(int productId);

}
