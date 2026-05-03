package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.ProductDao;
import model.Product;
import util.DBConnection;

public class ProductDaoImpl implements ProductDao {

	private static final String INSERT_QUERY = "insert into products (product_name, description, price, quantity) values (?, ?, ?, ?)";
	private static final String SELECT_QUERY = "select quantity from products where product_id = ?";
	private static final String USERS_QUERY = "select user_id, first_name, last_name, username, city, email, mobile, role from users";

	@Override
	public boolean save(Product product) {
		try {
			Connection con = null;

			con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(INSERT_QUERY);

			ps.setString(1, product.getName());
			ps.setString(2, product.getDescription());
			ps.setDouble(3, product.getPrice());
			ps.setInt(4, product.getQuantity());

			int rows = ps.executeUpdate();

			return rows > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int getProductStock(int id) {
		int quantity = 0;
		Connection con = null;

		try {
			con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_QUERY);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				quantity = rs.getInt("quantity");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return quantity;
	}

//	Once users feature is enabled, uncomment below changes
//	@Override
//	public List<Users> getRegisteredUsers() {
//
//		List<Users> list = new ArrayList<Users>();
//		Connection con = null;
//
//		try {
//			con = DBConnection.getConnection();
//			PreparedStatement ps = con.prepareStatement(USERS_QUERY);
//
//			ResultSet rs = ps.executeQuery();
//
//			while (rs.next()) {
//				Users users = new Users();
//				users.setId(rs.getInt(1));
//				users.setFirstname(rs.getString(2));
//				users.setLastname(rs.getString(3));
//				users.setUsername(rs.getString(4));
//				users.setCity(rs.getString(5));
//				users.setEmail(rs.getString(6));
//				users.setMobile(rs.getString(7));
//				users.setRole(rs.getString(8));
//				list.add(users);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
}
