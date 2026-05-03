package controller;

import java.util.Scanner;
import model.Product;
import service.impl.ProductServiceImpl;

public class ProductController {

	private final Scanner scanner = new Scanner(System.in);

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

		ProductServiceImpl productService = new ProductServiceImpl();
		productService.addProduct(product);
	}

}
