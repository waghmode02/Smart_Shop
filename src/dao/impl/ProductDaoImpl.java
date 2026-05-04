package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.ProductDao;
import model.Product;
import model.User;
import util.DBConnection;

public class ProductDaoImpl implements ProductDao {

	private static final String INSERT_QUERY = "insert into products (product_name, description, price, quantity) values (?, ?, ?, ?)";
	private static final String SELECT_QUERY = "select quantity from products where product_id = ?";
	private static final String USERS_QUERY = "select user_id, first_name, last_name, username, city, email, mobile, role from users";
	private static final String PURCHASE_HISTORY_QUERY = "SELECT products.product_id, products.product_name, products.description, products.price, purchases.quantity "
			+ "FROM purchases " + "INNER JOIN users ON purchases.user_id = users.user_id "
			+ "INNER JOIN products ON purchases.product_id = products.product_id " + "WHERE users.username = ?";

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

	@Override
	public List<User> getRegisteredUsers() {

		List<User> list = new ArrayList<User>();
		Connection con = null;

		try {
			con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(USERS_QUERY);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt(1));
				user.setFirstName(rs.getString(2));
				user.setLastName(rs.getString(3));
				user.setUsername(rs.getString(4));
				user.setCity(rs.getString(5));
				user.setEmail(rs.getString(6));
				user.setMobile(rs.getString(7));
				user.setRole(rs.getString(8));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Product> getPurchaseHistory(String username) {
		List<Product> list = new ArrayList<Product>();
		Connection con = null;

		try {
			con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(PURCHASE_HISTORY_QUERY);

			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Product product = new Product(rs.getString("product_name"), rs.getString("description"),
						rs.getDouble("price"), rs.getInt("price"));
				product.setId(rs.getInt("product_id"));

				list.add(product);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void updateProductDetails(int id, String name, String description, double price, int quantity) {
		
		String query = "";

	    if (name != null) {
	        query = "UPDATE products SET product_name = ? WHERE product_id = ?";
	    } else if (description != null) {
	        query = "UPDATE products SET description = ? WHERE product_id = ?";
	    } else if (price != -1) {
	        query = "UPDATE products SET price = ? WHERE product_id = ?";
	    } else if (quantity != -1) {
	        query = "UPDATE products SET quantity = ? WHERE product_id = ?";
	    } else {
	        System.out.println("No valid field to update.");
	        return;
	    }
	    
		Connection con = null;
		try {
			con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			
			if(name!=null)
				ps.setString(1, name);
			else if (description != null)
				ps.setString(1, description);
			else if(price < 0 )
				ps.setDouble(1, price);
			else if(quantity < 0)
				ps.setInt(1, quantity);
			
			ps.setInt(2, id);
			
			int rows = ps.executeUpdate();

	        if (rows > 0)
	            System.out.println("Product updated successfully!");
	        else
	            System.out.println("Product not found.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
