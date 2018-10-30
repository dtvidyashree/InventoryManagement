package com.sidgs.Inventory;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import dao.InventoryManagementDAOImpl;
import dto.InventoryManagementDTO;

public class InventoryManagementDAOImplTest {

	InventoryManagementDAOImpl dao;

	@Before
	public void setup() {
		dao = new InventoryManagementDAOImpl();
	}

	@Test
	public void test() throws SQLException {
		InventoryManagementDTO dto = dao.insertIntoInventory("Book01", 10.5, 13.79);
		assertEquals(true, dto.isActive());

		InventoryManagementDTO dto1 = dao.insertIntoInventory("Food01", 1.47, 3.98);
		assertEquals(true, dto1.isActive());

		InventoryManagementDTO dto2 = dao.insertIntoInventory("Med01", 10.5, 13.79);
		assertEquals(true, dto2.isActive());

		InventoryManagementDTO dto3 = dao.insertIntoInventory("Tab01", 57.00, 84.98);
		assertEquals(true, dto3.isActive());
//		
//		InventoryManagementDTO dto4 = dao.deleteFromInventory("Book01");
//		assertEquals(false, dto4.isActive());

//		InventoryManagementDTO dto5 = dao.updateBuy("Food01", 200);
//		assertEquals(true, dto5.isActive());

//		InventoryManagementDTO dto6 = dao.updateSell("Food01", 20);
//		assertEquals(true, dto6.isActive());

//		InventoryManagementDTO dto7 = dao.report();
//		assertEquals(true, dto7.isActive());

//		--------------------------------------------------------------------------------------------------------------------------------
//		without dto class
//		assertEquals(true, dao.insertIntoInventory("Food01", 1.47, 3.98));
//		assertEquals(true, dao.insertIntoInventory("Med01", 10.5, 13.79));
//		assertEquals(true, dao.insertIntoInventory("Tab01", 57.00, 84.98));

//		assertEquals(true, dao.deleteFromInventory("Book01"));
//
//		assertEquals(true, dao.updateBuy("Food01", 200));
//
//		assertEquals(true, dao.updateSell("Food01", 20));
//
//		dao.report();
//
//		assertEquals(true, dao.updateBuy("Tab01", 80));
//		dao.updateSell("Tab01", 2);
//
//		dao.report();
	}

}
