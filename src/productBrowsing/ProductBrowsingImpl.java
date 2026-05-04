package productBrowsing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import util.DBConnection;

public class ProductBrowsingImpl implements ProductBrowsing {

	public static final String QUANTITY_CHECK_QUERY = "SELECT quantity FROM products WHERE product_id = ?";
	public static final String INSERT_QUERY = "insert into purchases (user_id, product_id, quantity) values (?,?,?)";
	public static final String UPDATE_QUERY = "update products set quantity =? where product_id=?";

	@Override
	public void viewAllProduct() {
		// TODO Auto-generated method stub
		System.out.println("Displaying all products in sorted order:");
		try {
			Connection conn = DBConnection.getConnection();
			String query = "select * from products";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			System.out.println("Product ID | Name | Description | Price | Quantity");
			while (rs.next()) {
				System.out.println(rs.getInt("product_id") + " |" + rs.getString("product_name") + " | "
						+ rs.getString("description") + " |" + rs.getBigDecimal("price") + " |"
						+ rs.getInt("quantity"));
			}
			System.out.println("---------------------------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void searchProductsbyName() {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter product name to search >>");
		String keyword = scanner.next();
		try {
			boolean found = false;
			Connection conn = DBConnection.getConnection();
			String query = "select * from product where product_name like ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, "%" + keyword + "%");
			ResultSet rs = ps.executeQuery();
			System.out.println("Product ID | Name | Description | Price | Quantity");
			while (rs.next()) {
				found = true;
				System.out.println(rs.getInt("product_id") + " |" + rs.getString("product_name") + " | "
						+ rs.getString("descriptions") + " |" + rs.getBigDecimal("price") + " |"
						+ rs.getInt("quntity"));
			}
			if (!found) {
				System.out.println("products are not found..!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Override
	public void viewPurchaseHistory() {
		// TODO Auto-generated method stub

	}

	@Override
	public void viewAllCartItem() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addToCart() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter the user ID: ");
		int userId = scanner.nextInt();

		System.out.println("Enter the product ID: ");
		int productId = scanner.nextInt();

		System.out.println("Enter Quantity: ");
		int quantity = scanner.nextInt();

		Connection con = null;

		try {
			con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(QUANTITY_CHECK_QUERY);

			ps.setInt(1, productId);

			ResultSet rs = ps.executeQuery();

			if (!rs.next()) {
				System.out.println("Product not found!");
				return;
			}

			int availableQuantity = rs.getInt("quantity");

			if (quantity > availableQuantity) {
				System.out.println("Stock Unavaialable!");
				return;
			}

			PreparedStatement ps1 = con.prepareStatement(INSERT_QUERY);
			ps1.setInt(1, userId);
			ps1.setInt(2, productId);
			ps1.setInt(3, quantity);

			int purchaseResult = ps1.executeUpdate();
			PreparedStatement ps2 = con.prepareStatement(UPDATE_QUERY);
			ps2.setInt(1, quantity);
			ps2.setInt(2, productId);

			int result = ps2.executeUpdate();

			if (purchaseResult > 0 && result > 0) {
				System.out.println("Product added to cart successfully!");
			} else {
				System.out.println("Failed to add to cart. Retry!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
