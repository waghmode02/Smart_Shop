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
		int id=0;
		try {
			System.out.println("Enter the product id: ");
			 id = scanner.nextInt();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Please enetr correct product id");
			return;
		}
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

		for (User user : users) {
			System.out.println("Registered Users are: " + user);
		}

	}
	
	public void viewPurchaseHistory() {
		System.out.println("Enter the username: ");
		String username = scanner.nextLine();
		
		List<Product> products = productService.viewPurchaseHistory(username);
		
		if (products.isEmpty()) {
	        System.out.println("No purchase history found.");
	    } else {
	        System.out.println("Purchase History for this user: ");
	        for (Product product : products) {
	            System.out.println(product);
	        }
	    }
	}
	
	public void updateProductDetails() {
		System.out.println("Enter the product ID to update: ");
		int id =0;
		try {
			 id = scanner.nextInt();
			scanner.nextLine();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Please enter correct product id");
			return;
		}
		
		String name = null;
	    String description = null;
	    double price = -1;
	    int quantity = -1;

		    System.out.println("What do you want to update?");
		    System.out.println("1. Name");
		    System.out.println("2. Description");
		    System.out.println("3. Price");
		    System.out.println("4. Quantity");
		    int choice=0;
		    try {
		    	choice = scanner.nextInt();
			    scanner.nextLine();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Please enter correct choice..!");
			}
		    
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
		        try {
		        	System.out.println("Enter new price:");
			        price = scanner.nextDouble();
			        
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Please enter number only..!");
					return;
				}
		        scanner.nextLine();
		        break;

		    case 4:
		        try {
		        	System.out.println("Enter new quantity:");
			        quantity = scanner.nextInt();
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Please enter number only..!");
					return;
				}
		        scanner.nextLine();
		        break;

		    default:
		        System.out.println("Invalid choice");
		        return;
		    }

		    productService.updateProductDetails(id, name, description, price, quantity);
		}
	
	public void deleteProduct() {
		int id=0;
		try {
			System.out.println("Enter the product ID: ");
			 id= scanner.nextInt();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Please enter correct product id");
		}
		scanner.nextLine();
		
		productService.deleteProduct(id);
	}

}
