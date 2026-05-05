package service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import service.UserValidation;
import util.DBConnection;

public class UserValidationImpl implements UserValidation {

    @Override
    public boolean isUsernameExists(String username) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT 1 FROM users WHERE username=?")) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isEmailExists(String email) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT 1 FROM users WHERE email=?")) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isMobileExists(String mobile) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT 1 FROM users WHERE mobile=?")) {

            ps.setString(1, mobile);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isValidMobile(String mobile) {
        return mobile != null && mobile.matches("^[6-9][0-9]{9}$");
    }

    @Override
    public boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    @Override
    public boolean isValidUsername(String username) {
        return username != null && username.matches("^[a-zA-Z0-9_]{4,15}$");
    }

	@Override
	public boolean isNameAndLastName(String name) {
		// TODO Auto-generated method stub
		return name != null && name.matches("^[A-Za-z]+$");
	}
}