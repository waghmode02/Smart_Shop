package service.impl;

import java.util.List;
import dao.ProductDao;
import dao.impl.ProductDaoImpl;
import model.Product;
import model.User;
import service.ProductService;

public class ProductServiceImpl implements ProductService {

	private final ProductDao productDao = new ProductDaoImpl();

	@Override
	public void addProduct(Product product) {

		if (product.getName() == null || product.getName().isEmpty()) {
			throw new IllegalArgumentException("Product name required");
		}

		boolean saved = productDao.save(product);

		if (saved)
			System.out.println("Product saved successfully!!");
		else
			System.out.println("Failed to save the product.");
	}

	@Override
	public int viewProductStock(int id) {

		return productDao.getProductStock(id);

	}

	@Override
	public List<User> viewRegisteredUsers() {

		return productDao.getRegisteredUsers();
		
	}

	@Override
	public List<Product> viewPurchaseHistory(String username) {
		return productDao.getPurchaseHistory(username);
	}

	@Override
	public void updateProductDetails(int id, String name, String description, double price, int quantity) {
		productDao.updateProductDetails(id, name, description, price, quantity);
	}
	

}
