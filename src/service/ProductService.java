package service;

import model.Product;

public interface ProductService {

	void addProduct(Product product);
	
	int viewProductStock(int id);

}
