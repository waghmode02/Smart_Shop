package controller;

import java.util.Scanner;

import login.Login;
import productBrowsing.ProductBrowsingImpl;
import service.impl.UserRegistrationImpl;

public class AppController {

<<<<<<< HEAD
    private final Scanner scanner = new Scanner(System.in);
    private ProductController productController = new ProductController();
    private ProductBrowsingImpl productBrowsingImpl = new ProductBrowsingImpl();
=======
	private final Scanner scanner = new Scanner(System.in);
	private ProductController productController = new ProductController();
	private ProductBrowsingImpl productBrowsingImpl = new ProductBrowsingImpl();

	public void startApp() {
		//showAdminMenu();
>>>>>>> 9d811bd3c8217ce15ebbbfac488fccb87812d48e

    public void startApp() {

<<<<<<< HEAD
        MenuHandler menuHandler = new MenuHandler();
        menuHandler.menu();

        boolean flag = false;
=======
		MenuHandler menuHandler = new MenuHandler();
		menuHandler.menu();

		Scanner scanner = new Scanner(System.in);
		boolean flag = false;
		while (!flag) {
			System.out.println("Enter your choice >>");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				UserRegistrationImpl userRegistrationImpl = new UserRegistrationImpl();
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
				flag = true;
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
			System.out.println("4. View Purchase History");
			System.out.println("5. Update Product Details");
			System.out.println("6. Delete the product from inventory");
			System.out.println("7. Go Back");
			System.out.println("Enter your choice: ");
>>>>>>> 9d811bd3c8217ce15ebbbfac488fccb87812d48e

        while (!flag) {

            System.out.println("Enter your choice >>");
            int choice = scanner.nextInt();

            switch (choice) {

            case 1:
                new UserRegistrationImpl().newUserRegistration();
                break;

<<<<<<< HEAD
            case 2:
                String role = Login.userLogin();
=======
			case 4:
				productController.viewPurchaseHistory();
				break;

			case 5:
				productController.updateProductDetails();
				break;

			case 6:
				productController.deleteProduct();
				break;

			case 7:
				return;
>>>>>>> 9d811bd3c8217ce15ebbbfac488fccb87812d48e

                if ("admin".equalsIgnoreCase(role)) {
                    System.out.println("Welcome Admin!");
                    showAdminMenu();
                } else if ("user".equalsIgnoreCase(role)) {
                    System.out.println("Welcome User!");
                } else {
                    System.out.println("Login Failed!");
                }
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
                flag = true;
                break;

            default:
                System.out.println("Invalid Choice!");
            }
        }
    }

    private void showAdminMenu() {

        while (true) {

            System.out.println("\n------ ADMIN MENU ------");
            System.out.println("1. Add Product");
            System.out.println("2. View Product Stock");
            System.out.println("3. View Registered Users");
            System.out.println("4. View Purchase History");
            System.out.println("5. Update Product Details");
            System.out.println("6. Go Back");

            System.out.print("Enter your choice: ");
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
                productController.viewPurchaseHistory();
                break;

            case 5:
                productController.updateProductDetails();
                break;

            case 6:
                return;

            default:
                System.out.println("Invalid Choice!");
            }
        }
    }
}