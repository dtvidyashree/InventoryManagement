package com.sidgs.Inventory;

import java.util.ArrayList;

public class InventoryManagementImpl implements InventoryManagement {
	ArrayList<String> products = new ArrayList<String>();

	public ArrayList<String> create(String itemName, Double costPrice, Double sellingPrice) {
		products.add("Book01");
		products.add("Food01");
		products.add("Med01");
		products.add("Tab01");
		return products;

	}

	public boolean delete(String itemName) {
		return true;
	}

	public boolean updateBuy(String itemName, int quantity) {
		return true;

	}

	public boolean updateSell(String itemName, int quantity) {
		return true;

	}

	public void report() {

	}

}
