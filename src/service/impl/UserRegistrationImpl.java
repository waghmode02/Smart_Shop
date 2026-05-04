package service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import service.UserRegistration;
import util.DBConnection;

import service.impl.UserValidationImpl;

public class UserRegistrationImpl implements UserRegistration {

    @Override
    public void newUserRegistration() {

        Scanner scanner = new Scanner(System.in);
        UserValidationImpl validator = new UserValidationImpl();

        System.out.println("Enter First Name");
        String first_name = scanner.nextLine();   

        System.out.println("Enter Last Name");
        String last_name = scanner.nextLine();

        System.out.println("Enter Username");
        String username = scanner.nextLine();

        if (!validator.isValidUsername(username)) {
            System.out.println("Invalid Username");
            return;
        }

        if (validator.isUsernameExists(username)) {
            System.out.println("Username already exists");
            return;
        }

        System.out.println("Enter Password");
        String password = scanner.nextLine();

        System.out.println("Enter City");
        String city = scanner.nextLine();

        System.out.println("Enter Email");
        String email = scanner.nextLine();

        if (!validator.isValidEmail(email)) {
            System.out.println("Invalid Email Format");
            return;
        }

        if (validator.isEmailExists(email)) {
            System.out.println("Email already registered");
            return;
        }

        System.out.println("Enter Mobile");
        String mobile = scanner.nextLine();

        if (!validator.isValidMobile(mobile)) {
            System.out.println("Invalid Mobile Number");
            return;
        }

        if (validator.isMobileExists(mobile)) {
            System.out.println("Mobile number already registered");
            return;
        }
        
        System.out.println("Enter Role (admin/user): ");
        String role = scanner.nextLine().toLowerCase();

        if (!role.equals("admin") && !role.equals("user")) {
            System.out.println("Invalid role! Defaulting to 'user'");
            role = "user";
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO users (first_name, last_name, username, password, city, email, mobile, role) VALUES (?,?,?,?,?,?,?,?)")) {

            ps.setString(1, first_name);
            ps.setString(2, last_name);
            ps.setString(3, username);
            ps.setString(4, password);
            ps.setString(5, city);
            ps.setString(6, email);
            ps.setString(7, mobile);
            ps.setString(8, role);

            int res = ps.executeUpdate();

            if (res > 0) {
                System.out.println("Registration Successful!");
            } else {
                System.out.println("Registration Failed");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}