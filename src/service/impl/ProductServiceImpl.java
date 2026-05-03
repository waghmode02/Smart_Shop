package service.impl;

import java.util.List;
import dao.ProductDao;
import dao.impl.ProductDaoImpl;
import model.Product;
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
	
//	Once users feature is enabled, uncomment below changes
//	@Override
//	public List<Users> viewRegisteredUsers() {
//
//		return productDao.getRegisteredUsers();
//
//	}

}
