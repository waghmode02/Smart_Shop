package productBrowsing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Cart;
import util.DBConnection;

public class ProductBrowsingImpl implements ProductBrowsing {

	public static final String QUANTITY_CHECK_QUERY = "SELECT quantity FROM products WHERE product_id = ?";
	public static final String INSERT_QUERY = "insert into purchases (user_id, product_id, quantity) values (?,?,?)";
	public static final String UPDATE_QUERY = "update products set quantity = quantity - ? where product_id=?";
	private static List<Cart> cartList = new ArrayList<>();
	private static final Scanner scanner = new Scanner(System.in);

	@Override
	public void viewAllProduct() {
		System.out.println("Displaying all products:");
		try {
			Connection conn = DBConnection.getConnection();
			String query = "select * from products";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			System.out.println("Product ID | Name | Description | Price | Quantity");

			while (rs.next()) {
				System.out.println(rs.getInt("product_id") + " | " + rs.getString("product_name") + " | "
						+ rs.getString("description") + " | " + rs.getBigDecimal("price") + " | "
						+ rs.getInt("quantity"));
			}

			System.out.println("---------------------------------------------------------");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void searchProductsbyName() {
		System.out.println("Enter product name to search >>");
		String keyword = scanner.next();

		try {
			boolean found = false;
			Connection conn = DBConnection.getConnection();
			String query = "select * from products where product_name like ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, "%" + keyword + "%");

			ResultSet rs = ps.executeQuery();

			System.out.println("Product ID | Name | Description | Price | Quantity");

			while (rs.next()) {
				found = true;
				System.out.println(rs.getInt("product_id") + " | " + rs.getString("product_name") + " | "
						+ rs.getString("description") + " | " + rs.getBigDecimal("price") + " | "
						+ rs.getInt("quantity"));
			}

			if (!found) {
				System.out.println("Products not found!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void viewPurchaseHistory() {
		// TODO
	}

	@Override
	public void addToCart() {

		System.out.println("Enter the user ID: ");
		int userId = scanner.nextInt();

		System.out.println("Enter the product ID: ");
		int productId = scanner.nextInt();

		System.out.println("Enter Quantity: ");
		int quantity = scanner.nextInt();

		try {
			Connection con = DBConnection.getConnection();

			// Check stock
			PreparedStatement ps = con.prepareStatement(QUANTITY_CHECK_QUERY);
			ps.setInt(1, productId);

			ResultSet rs = ps.executeQuery();

			if (!rs.next()) {
				System.out.println("Product not found!");
				return;
			}

			int availableQuantity = rs.getInt("quantity");

			if (quantity > availableQuantity) {
				System.out.println("Stock Unavailable!");
				return;
			}

//            // Insert purchase
//            PreparedStatement ps1 = con.prepareStatement(INSERT_QUERY);
//            ps1.setInt(1, userId);
//            ps1.setInt(2, productId);
//            ps1.setInt(3, quantity);
//
//            int purchaseResult = ps1.executeUpdate();
//
//            // Update stock
//            PreparedStatement ps2 = con.prepareStatement(UPDATE_QUERY);
//            ps2.setInt(1, quantity);
//            ps2.setInt(2, productId);
//
//            int updateResult = ps2.executeUpdate();
//
//            if (purchaseResult > 0 && updateResult > 0) {
//                System.out.println("Product added to cart successfully!");
//            } else {
//                System.out.println("Failed to add to cart!");
//            }

			cartList.add(new Cart(userId, productId, quantity));

			System.out.println("Product added to cart successfully!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void viewAllCartItem() {

		System.out.println("Enter your user ID: ");
		int userId = scanner.nextInt();

		if (cartList.isEmpty()) {
			System.out.println("Cart is empty!");
			return;
		}

		boolean found = false;

		System.out.println("Product ID | Quantity");

		for (Cart item : cartList) {

			if (item.getUserId() == userId) {
				found = true;

				System.out.println(item.getProductId() + " | " + item.getQuantity());
			}
		}

		if (!found) {
			System.out.println("No items in your cart!");
		}
	}

}
