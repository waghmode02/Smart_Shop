package controller;

import java.util.Scanner;

public class AppController {
	public  void startApp() {
		MenuHandler menuHandler=new MenuHandler();
		menuHandler.menu();
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter your choice >>");
		int choice=scanner.nextInt();
		switch (choice) {
		case 1: {
		}
		default:
			throw new IllegalArgumentException("Unexpected value: "+choice);
		}
	}
}

