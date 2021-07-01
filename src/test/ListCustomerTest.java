package test;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.data.ListCustomer;

public class ListCustomerTest {

	@Test
	public void sizeTest() {
		ListCustomer customers = new ListCustomer();

		customers.addCustomer(-34.520514, -58.679575);
		customers.addCustomer(-34.526697, -58.706885);
		assertEquals(2, customers.size());
	}

	@Test
	public void getLatitudeTest() {
		ListCustomer customers = new ListCustomer();
		customers.addCustomer(-34.520514, -58.679575);

		assertEquals(-34.520514, customers.getCustomer(0).getLatitude(), 0);
	}

	@Test
	public void getLongitudeTest() {
		ListCustomer customers = new ListCustomer();
		customers.addCustomer(-34.520514, -58.679575);

		assertEquals(-58.679575, customers.getCustomer(0).getLongitude(), 0);
	}

}
