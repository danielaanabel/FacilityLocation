package test;

import static org.junit.Assert.*;
import org.junit.Test;
import logic.Solution;
import logic.data.DistributionCenter;

public class SolutionTest {

	@Test
	public void sizeTest() {
		Solution solution = new Solution();
		solution.addCenter(new DistributionCenter("Local15", -34.511366, -58.688722));
		solution.addCenter(new DistributionCenter("Local6", -34.555415, -58.733772));
		solution.addCenter(new DistributionCenter("Local9", -34.503010, -58.633109));

		assertEquals(3, solution.size());
	}

	@Test
	public void getLatitudeTest() {
		Solution solution = new Solution();
		solution.addCenter(new DistributionCenter("Local15", -34.511366, -58.688722));
		assertEquals(-34.511366, solution.getCenter(0).getLatitude(), 0);
	}

	@Test
	public void getLongitudeTest() {
		Solution solution = new Solution();
		solution.addCenter(new DistributionCenter("Local15", -34.511366, -58.688722));

		assertEquals(-58.688722, solution.getCenter(0).getLongitude(), 0);
	}

}
