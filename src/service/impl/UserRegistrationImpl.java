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
        String fname = scanner.nextLine();   

        System.out.println("Enter Last Name");
        String lname = scanner.nextLine();

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

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO users (fname, lname, username, password, city, email, mobile) VALUES (?,?,?,?,?,?,?)")) {

            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, username);
            ps.setString(4, password);
            ps.setString(5, city);
            ps.setString(6, email);
            ps.setString(7, mobile);

            int res = ps.executeUpdate();

            if (res > 0) {
                System.out.println("Registration Successful!");
            } else {
                System.out.println("Registration Failed");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close(); 
    }
}