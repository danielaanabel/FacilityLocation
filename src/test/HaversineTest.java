package test;

import static org.junit.Assert.*;

import org.junit.Test;
import logic.Haversine;

public class HaversineTest {

	@Test
	public void calculateDistanceTest() {
		double lat1 = -34.489209;
		double lon1 = -58.726490;
		double lat2 = -34.4975727;
		double lon2 = -58.7286474;
		int distance = Haversine.calculateDistance(lon1, lat1, lon2, lat2);

		assertEquals(950, distance);
	}

}
