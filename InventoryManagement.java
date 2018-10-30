package com.sidgs.Inventory;

import java.util.ArrayList;

public interface InventoryManagement {
	public ArrayList<String> create(String itemName, Double costPrice, Double sellingPrice);

	public boolean delete(String itemName);

	public boolean updateBuy(String itemName, int quantity);

	public boolean updateSell(String itemName, int quantity);

	public void report();
}
