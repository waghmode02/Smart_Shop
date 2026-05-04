package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import util.DBConnection;

public class Login {

    private static final String loginQuery =
        "SELECT role FROM users WHERE username=? AND password=?";

    public static String userLogin() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Username: ");
        String username = scanner.next();

        System.out.print("Enter Password: ");
        String password = scanner.next();

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(loginQuery);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role"); // admin / user
                System.out.println("Login Successful!");
                return role;
            } else {
                System.out.println("Invalid Username or Password");
                return null;
            }
<<<<<<< HEAD

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
=======
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: "+e.getMessage());
		}
		//scanner.close();
		
	}
}
>>>>>>> 9d811bd3c8217ce15ebbbfac488fccb87812d48e
