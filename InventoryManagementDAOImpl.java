package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sidgs.Inventory.InventoryManagementImpl;

import dto.InventoryManagementDTO;

public class InventoryManagementDAOImpl {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/inventory?allowPublicKeyRetrieval=true&useSSL=false\"";

	static final String USER = "root";
	static final String PASSWORD = "root";

	static double previous_profit = 0.00;

	InventoryManagementImpl inventory = new InventoryManagementImpl();
	InventoryManagementDTO dto = new InventoryManagementDTO();

	public InventoryManagementDTO insertIntoInventory(String itemName, Double costPrice, Double sellingPrice)
			throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		int max_inv_id = 1;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			stmt = conn.createStatement();

			String max_id_query = "SELECT max(inventory_id) FROM inventory.inventory_items";
			ResultSet max_id = stmt.executeQuery(max_id_query);
			while (max_id.next()) {

				max_inv_id = max_id.getInt(1) + 1;
			}
			String insert_query = "INSERT INTO inventory.inventory_items (inventory_id, item_name, item_sell_price, item_buy_price) values ('"
					+ max_inv_id + "','" + itemName + "'," + sellingPrice + "," + costPrice + ")";
			stmt.executeUpdate(insert_query);
			dto.setActive(true);
			max_id.close();

			// Clean-up environment
			stmt.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return dto;
	}

	// To Delete the item from the inventory
	// @args itemName
	public InventoryManagementDTO deleteFromInventory(String itemName) throws SQLException {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			stmt = conn.createStatement();
			String delete_query = "UPDATE inventory.inventory_items SET active = 0 WHERE (item_name = '" + itemName
					+ "')";
			int rs = stmt.executeUpdate(delete_query);
			dto.setActive(false);

			// Clean-up environment
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return dto;
	}

	// To Update quantity with how many products bought
	// @args itemName and quantity
	public InventoryManagementDTO updateBuy(String itemName, int quantity) {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			stmt = conn.createStatement();
			String update_query = "UPDATE `inventory`.`inventory_items` SET `item_quantity` = " + quantity
					+ "  WHERE (`item_name` = '" + itemName + "')";
			int rs = stmt.executeUpdate(update_query);
			dto.setActive(true);

			// Clean-up environment
			stmt.close();
			conn.close();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return dto;
	}

	// To Update quantity with how many products sold
	// @args itemName and quantity
	public InventoryManagementDTO updateSell(String itemName, int quantity) {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			stmt = conn.createStatement();
			String getItemQuantityQuery = "Select item_quantity from inventory_items where item_name = '" + itemName
					+ "'";
			ResultSet result_quantity = stmt.executeQuery(getItemQuantityQuery);
			int avaliable_Quantity = 0;
			while (result_quantity.next()) {
				avaliable_Quantity = result_quantity.getInt("item_quantity");
			}
			int updatedQuantity = avaliable_Quantity - quantity;

			String getSoldQuantityQuery = "Select sold_quantity from inventory_items where item_name = '" + itemName
					+ "'";
			ResultSet result_sold_quantity = stmt.executeQuery(getSoldQuantityQuery);
			int sold_Quantity = 0;
			while (result_sold_quantity.next()) {
				sold_Quantity = result_sold_quantity.getInt("sold_quantity");
			}
			int updatedSoldQuantity = sold_Quantity + quantity;

			String updateSellQuery = "UPDATE inventory.inventory_items SET item_quantity = " + updatedQuantity
					+ ", sold_quantity = " + updatedSoldQuantity + " WHERE (item_name = '" + itemName + "')";
			int rs = stmt.executeUpdate(updateSellQuery);

			// Clean-up environment
			stmt.close();
			conn.close();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return dto;
	}

	// to generate report
	// Display all the items in the inventory
	public void report() {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			stmt = conn.createStatement();
			String q = "SELECT item_name, item_buy_price, item_sell_price, item_quantity, sold_quantity from inventory_items where active = 1 order by item_name asc";

			ResultSet rs = stmt.executeQuery(q);
			String rep = "Inventory Report\n";
			rep += "Item Name\tBought At\tSold At\tAvailableQty\tValue\n";
			rep += "---------\t---------\t-------\t------------\t-----\n";
			double total_value = 0.00;
			double profit = 0.00;
			while (rs.next()) {
				rep += rs.getString("item_name") + "\t" + rs.getDouble("item_buy_price") + "\t"
						+ rs.getDouble("item_sell_price") + "\t" + rs.getInt("item_quantity") + "\t"
						+ rs.getDouble("item_buy_price") * rs.getInt("item_quantity") + "\n";
				total_value = total_value + (rs.getDouble("item_buy_price") * rs.getInt("item_quantity"));
				profit = profit + ((rs.getDouble("item_sell_price") - rs.getDouble("item_buy_price"))
						* rs.getInt("sold_quantity"));
			}
			rep += "Total value \t " + total_value + "\n";
			rep += "Profit Since previous report \t" + (profit - InventoryManagementDAOImpl.previous_profit) + "\n";
			InventoryManagementDAOImpl.previous_profit = (profit - InventoryManagementDAOImpl.previous_profit);
			System.out.println(rep);
			dto.setActive(true);

			// Clean-up environment
			stmt.close();
			conn.close();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

}
