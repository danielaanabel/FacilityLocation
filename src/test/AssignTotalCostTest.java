package test;

import static org.junit.Assert.*;

import org.junit.Test;
import logic.data.DistributionCenter;
import logic.data.ListCustomer;

public class AssignTotalCostTest {

	@Test
	public void calculateTotalCostTest() {
		DistributionCenter center = new DistributionCenter("Local1", -34.5222325, -58.7009527);
		center.assignTotalCost(customers());
		assertEquals(4568, center.getTotalCost());
	}

	public ListCustomer customers() {
		ListCustomer ret = new ListCustomer();
		ret.addCustomer(-34.520514, -58.679575);
		ret.addCustomer(-34.526697, -58.706885);
		ret.addCustomer(-34.539007, -58.700938);
		return ret;
	}

}
