package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import util.DBConnection;

public class Login {
	private static final String loginQuery ="select * from users where username=? and password=?";
	public static void userLogin() {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter Username:");
		String username=scanner.next();
		System.out.println("Enter Password:");
		String password=scanner.next();
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps=conn.prepareStatement(loginQuery);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if (rs.next()) {
                System.out.println("Login Successful..! ");
            } else {
                System.out.println("Invalid Username or Password ");
            }
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: "+e.getMessage());
		}
		//scanner.close();
		
	}
}
