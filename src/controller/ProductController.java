package controller;

import java.util.List;
import java.util.Scanner;
import model.Product;
import model.User;
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
		scanner.nextLine();

		System.out.println("Enter the product quantity: ");
		int quantity = scanner.nextInt();
		scanner.nextLine();

		Product product = new Product(name, description, price, quantity);

		productService.addProduct(product);
	}

	public void viewProductStock() {

		System.out.println("Enter the product id: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		int stock = productService.viewProductStock(id);

		if (stock > 0)
			System.out.println("Available quantity of this product: " + stock);
		else if (stock == 0)
			System.out.println("Out of stock! :(");
		else
			System.out.println("Product not found.");
	}

	public void viewRegisteredUsers() {

		List<User> users = productService.viewRegisteredUsers();

		if (users.isEmpty()) {
			System.out.println("No registered users found.");
			return;
		}
		
		System.out.println("User ID | Name | Username | City | Email | Mobile | Role");
	    System.out.println("-------------------------------------------------------------");
	    
		for (User user : users) {
			System.out.println(user.getUserId() + " | " + user.getFirstName() + " " + user.getLastName() + " | "
					+ user.getUsername() + " | " + user.getCity() + " | " + user.getEmail() + " | " + user.getMobile()
					+ " | " + user.getRole());
		}

	}

	public void viewPurchaseHistory() {
		System.out.println("Enter the username: ");
		String username = scanner.nextLine();

		List<Product> products = productService.viewPurchaseHistory(username);

		if (products.isEmpty()) {
			System.out.println("No purchase history found.");
		} else {
			System.out.println("Date | Product Name | Quantity | Price | Total");
			System.out.println("------------------------------------------------");
			
			for (Product product : products) {
				double total = product.getPrice() * product.getQuantity();
				System.out.println(product.getPurchaseDate() + " | " + product.getName() + " | " + product.getQuantity() + " | " + product.getPrice() + " | " + total);
			}
		}
	}

	public void updateProductDetails() {
		System.out.println("Enter the product ID to update: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		String name = null;
		String description = null;
		double price = -1;
		int quantity = -1;

		System.out.println("What do you want to update?");
		System.out.println("1. Name");
		System.out.println("2. Description");
		System.out.println("3. Price");
		System.out.println("4. Quantity");

		int choice = scanner.nextInt();
		scanner.nextLine();

		switch (choice) {

		case 1:
			System.out.println("Enter new name:");
			name = scanner.nextLine();
			break;

		case 2:
			System.out.println("Enter new description:");
			description = scanner.nextLine();
			break;

		case 3:
			System.out.println("Enter new price:");
			price = scanner.nextDouble();
			scanner.nextLine();
			break;

		case 4:
			System.out.println("Enter new quantity:");
			quantity = scanner.nextInt();
			scanner.nextLine();
			break;

		default:
			System.out.println("Invalid choice");
			return;
		}

		productService.updateProductDetails(id, name, description, price, quantity);
	}

	public void deleteProduct() {
		System.out.println("Enter the product ID: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		productService.deleteProduct(id);
	}

}
