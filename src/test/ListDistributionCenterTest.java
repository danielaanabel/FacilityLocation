package test;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.data.ListDistributionCenter;

public class ListDistributionCenterTest {

	@Test
	public void sizeTest() {

		ListDistributionCenter centers = new ListDistributionCenter();

		centers.addDistributionCenter("Local3", -34.551848, -58.690279);
		centers.addDistributionCenter("Local4", -34.504476, -58.659699);
		centers.addDistributionCenter("Local5", -34.474245, -58.655236);
		assertEquals(3, centers.size());
	}

	@Test
	public void getCenterRightTest() {

		ListDistributionCenter centers = new ListDistributionCenter();

		centers.addDistributionCenter("Local3", -34.551848, -58.690279);
		centers.addDistributionCenter("Local4", -34.504476, -58.659699);
		centers.addDistributionCenter("Local5", -34.474245, -58.655236);
		assertEquals("Local4", centers.getCenter(1).getIdLocal());
	}

}
