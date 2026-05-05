package productBrowsing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import util.DBConnection;

public class ProductBrowsingImpl implements ProductBrowsing {

	public static final String QUANTITY_CHECK_QUERY = "select quantity from products where product_id = ?";
	public static final String INSERT_QUERY = "insert into purchases (user_id, product_id, quantity) values (?,?,?)";
	public static final String UPDATE_QUERY = "update products set quantity = quantity - ? where product_id = ?";
	public static final String FETCH_CART_ITEMS = "select p.product_name, p.price, pu.quantity " + "from purchases pu "
			+ "inner join products p on pu.product_id = p.product_id " + "where pu.user_id = ?";
	private static final String PURCHASE_HISTORY_QUERY = "select products.product_id, products.product_name, products.description, products.price, purchases.quantity,purchases.purchase_date "
			+ "from purchases " + "inner join users on purchases.user_id = users.user_id "
			+ "inner join products ON purchases.product_id = products.product_id " + "where users.user_id = ?";
	public static final String FETCH_PRODUCT_BY_ID = "select * from products where product_id=?";

	private static final Scanner scanner = new Scanner(System.in);

	@Override
	public void viewAllProduct() {
		System.out.println("Displaying all products:");
		try {
			Connection conn = DBConnection.getConnection();
			String query = "select * from productss order by product_name";
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
			System.out.println("Error: " + e.getMessage());
		}
	}

	@Override
	public void searchProductsbyName() {
		System.out.println("Enter product name to search >>");
		String keyword = scanner.next();

		try {
			Connection conn = DBConnection.getConnection();
			String query = "select * from products where product_name like ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, "%" + keyword + "%");

			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				System.out.println("Products not found..!");
				return;
			}
			System.out.println("Product ID | Name | Description | Price | Quantity");

			while (rs.next()) {
				System.out.println(rs.getInt("product_id") + " | " + rs.getString("product_name") + " | "
						+ rs.getString("description") + " | " + rs.getBigDecimal("price") + " | "
						+ rs.getInt("quantity"));
			}

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	@Override
	public void viewPurchaseHistory() {
		Connection con = null;
		int id = 0;
		try {
			System.out.println("Enter the user id: ");
			id = scanner.nextInt();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Please enter correct user id");
			return;
		}
		System.out.println("Fetching your complete purchase history...");
		System.out.println("Date | Product Name | Quantity | Price | Total");
		System.out.println("----------------------------------------------");

		try {
			con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(PURCHASE_HISTORY_QUERY);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			boolean found = false;

			while (rs.next()) {

				found = true;

				String name = rs.getString("product_name");
				int quantity = rs.getInt("quantity");
				double price = rs.getDouble("price");
				String date = rs.getString("purchase_date");

				double total = quantity * price;

				System.out.println(date + "|" + name + " | " + quantity + " | " + price + " | " + total);

				if (!found) {
					System.out.println("No purchase history found!");
				}
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

	@Override
	public void addToCart() {
		int userId = 0;
		int productId = 0;
		int quantity = 0;
		try {
			System.out.println("Enter the user ID: ");
			userId = scanner.nextInt();

			System.out.println("Enter the product ID: ");
			productId = scanner.nextInt();

			System.out.println("Enter Quantity: ");
			quantity = scanner.nextInt();

			if (quantity <= 0) {
				System.out.println("Quantity must be greater than 0!");
				return;
			}
		} catch (InputMismatchException e) {
			// TODO: handle exception
			System.out.println("Please enter correct details");
		}

		Connection con = null;

		try {
			con = DBConnection.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement(QUANTITY_CHECK_QUERY);
			ps.setInt(1, productId);
			ResultSet rs = ps.executeQuery();

			if (!rs.next()) {
				System.out.println("Product not found!");
				con.rollback();
				return;
			}

			int availableQuantity = rs.getInt("quantity");

			if (quantity > availableQuantity) {
				System.out.println("Stock Unavailable!");
				con.rollback();
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

			int updateResult = ps2.executeUpdate();

			if (purchaseResult > 0 && updateResult > 0) {
				con.commit();
				System.out.println("Product purchased successfully!");
			} else {
				con.rollback();
				System.out.println("Failed to add to cart!");
			}

		} catch (SQLException e) {
			try {
				if (con != null) {
					con.rollback();
				}
			} catch (SQLException ex) {
				System.out.println("Error:" + e.getMessage());
			}
			e.printStackTrace();

		} finally {
			try {
				if (con != null) {
					con.setAutoCommit(true);
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("Error:" + e.getMessage());
			}
			scanner.next();
		}
	}

	@Override
	public void viewAllCartItem() {

		System.out.println("Fetching your cart/purchased items...");
		int userId = 0;
		try {
			System.out.println("Enter your user ID: ");
			userId = scanner.nextInt();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("please enter correct user ID");
			return;
		}

		boolean found = false;
		double totalAmount = 0;

		System.out.println("Product Name | Quantity | Price | Subtotal");
		System.out.println("-------------------------------------------");

		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(FETCH_CART_ITEMS);

			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				found = true;

				String name = rs.getString("product_name");
				double price = rs.getDouble("price");
				int quantity = rs.getInt("quantity");

				double subtotal = price * quantity;
				totalAmount += subtotal;

				System.out.println(name + " | " + quantity + " | " + price + " | " + subtotal);
			}

			if (!found) {
				System.out.println("No items in your cart!");
			} else {
				System.out.println("-------------------------------------------");
				System.out.println("Total Amount >> " + totalAmount);
			}

		} catch (Exception e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	@Override
	public void viewProductDetailsByID() {
		// TODO Auto-generated method stub
		int productId = 0;
		try {
			System.out.println("Enter product id");
			productId = scanner.nextInt();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Please enter correct product Id");
		}
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(FETCH_PRODUCT_BY_ID);
			ps.setInt(1, productId);
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				System.out.println("Products not found..!");
				return;
			}
			System.out.println("Product ID | Name | Description | Price | Quantity");

			while (rs.next()) {
				System.out.println(rs.getInt("product_id") + " | " + rs.getString("product_name") + " | "
						+ rs.getString("description") + " | " + rs.getBigDecimal("price") + " | "
						+ rs.getInt("quantity"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error:" + e.getMessage());
		}

	}
}
