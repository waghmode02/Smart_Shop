package controller;

import java.util.Scanner;

import login.Login;
import productBrowsing.ProductBrowsingImpl;
import service.impl.UserRegistrationImpl;

public class AppController {

	private final Scanner scanner = new Scanner(System.in);
	private ProductController productController = new ProductController();
	private ProductBrowsingImpl productBrowsingImpl=new ProductBrowsingImpl();
	public void startApp() {
		showAdminMenu();

//		if ("ADMIN".equalsIgnoreCase(user.getRole())) {
//			System.out.println("Welcome Admin!");
//			showAdminMenu();
//		} else {
//			System.out.println("Welcome User!");
//			showUserMenu();
//		}

		MenuHandler menuHandler = new MenuHandler();
		menuHandler.menu();
		
		Scanner scanner = new Scanner(System.in);
		boolean flag=false;
		while(!flag) {
			System.out.println("Enter your choice >>");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				UserRegistrationImpl userRegistrationImpl=new UserRegistrationImpl();
				userRegistrationImpl.newUserRegistration();
				break;
			case 2:
				Login.userLogin();
				break;
			case 3:
				productBrowsingImpl.viewAllProduct();
				break;
			case 4:
				productBrowsingImpl.searchProductsbyName();
				break;
			case 5:
				productBrowsingImpl.addToCart();
				break;
			case 6:
				productBrowsingImpl.viewAllCartItem();
				break;
			case 7:
				productBrowsingImpl.viewPurchaseHistory();
				break;
			case 8:
				System.out.println("Thank you..!");
				flag=true;
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + choice);
			}
		}
	}

	private void showAdminMenu() {
		while (true) {
			System.out.println("--------------------");
			System.out.println("1. Add Product");
			System.out.println("2. View Product Stock");
			System.out.println("3. View Registered Users");
			System.out.println("4. Go Back");
			System.out.println("Enter your choice: ");

			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				productController.addProduct();
				break;

			case 2:
				productController.viewProductStock();
				break;

			case 3:
				productController.viewRegisteredUsers();
				break;

			case 4:
				return;

			default:
				System.out.println("Invalid Choice :(");

			}

		}
	}
}
