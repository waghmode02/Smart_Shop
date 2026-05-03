package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import dao.ProductDao;
import model.Product;
import util.DBConnection;

public class ProductDaoImpl implements ProductDao {

	private static final String insert_query = "insert into products (product_name, description, price, quantity) values (?, ?, ?, ?)";

	@Override
	public boolean save(Product product) {
		try {
			Connection con = null;

			con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(insert_query);

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
}
