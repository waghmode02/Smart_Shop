package controller;

import java.util.Scanner;

public class AppController {

	private final Scanner scanner = new Scanner(System.in);
	private ProductController productController = new ProductController();

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
		System.out.println("Enter your choice >>");
		int choice = scanner.nextInt();
		switch (choice) {
		case 1: {
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + choice);
		}
	}

	private void showAdminMenu() {
		while (true) {
			System.out.println("--------------------");
			System.out.println("1. Add Product");
			System.out.println("2. View Product Stock");
			System.out.println("3. Go Back");
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
				return;

			default:
				System.out.println("Invalid Choice :(");

			}

		}
	}
}
