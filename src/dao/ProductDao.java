package dao;

import model.Product;

public interface ProductDao {

	boolean save(Product product);
	
	int getProductStock(int id);
}
