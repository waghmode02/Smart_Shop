package controller;

import java.util.Scanner;
import model.Product;
import service.impl.ProductServiceImpl;

public class ProductController {

	private final Scanner scanner = new Scanner(System.in);
	private ProductServiceImpl productService = new ProductServiceImpl();

	public void addProduct() {

		System.out.println("Enter the product name: ");
		String name = scanner.nextLine();

		System.out.println("Enter product description: ");
		String description = scanner.nextLine();

		System.out.println("Enter the product price: ");
		double price = scanner.nextDouble();

		System.out.println("Enter the product quantity: ");
		int quantity = scanner.nextInt();

		Product product = new Product(name, description, price, quantity);

		productService.addProduct(product);
	}

	public void viewProductStock() {

		System.out.println("Enter the product id: ");
		int id = scanner.nextInt();

		int stock = productService.viewProductStock(id);

		if (stock > 0)
			System.out.println("Available quantity of this product: " + stock);
		else
			System.out.println("Product not found.");
	}

}
