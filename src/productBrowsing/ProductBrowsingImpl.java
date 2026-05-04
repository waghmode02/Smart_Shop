package productBrowsing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import util.DBConnection;

public class ProductBrowsingImpl implements ProductBrowsing{

	@Override
	public void viewAllProduct() {
		// TODO Auto-generated method stub
		System.out.println("Displaying all products in sorted order:");
		try {
			Connection conn=DBConnection.getConnection();
			String query="select * from products";
			PreparedStatement ps=conn.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			System.out.println("Product ID | Name | Description | Price | Quantity");
			while (rs.next()) {
				System.out.println(rs.getInt("product_id")+" |"+rs.getString("product_name")+" | "+
			rs.getString("description")+" |"+rs.getBigDecimal("price")+" |"+rs.getInt("quantity"));
			}
			System.out.println("---------------------------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void searchProductsbyName() {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter product name to search >>");
		String keyword=scanner.next();
		try {
			boolean found=false;
			Connection conn=DBConnection.getConnection();
			String query="select * from products where product_name like ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, "%" + keyword+ "%");
			ResultSet rs = ps.executeQuery();
			System.out.println("Product ID | Name | Description | Price | Quantity");
			while (rs.next()) {
				found=true;
				System.out.println(rs.getInt("product_id")+" |"+rs.getString("product_name")+" | "+
			rs.getString("description")+" |"+rs.getBigDecimal("price")+" |"+rs.getInt("quantity"));
			}
			if(!found) {
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
		// TODO Auto-generated method stub
		
	}

}
