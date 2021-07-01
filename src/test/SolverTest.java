package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import logic.data.ListCustomer;
import logic.data.ListDistributionCenter;
import logic.Solution;
import logic.Solver;

public class SolverTest {
	ListCustomer customers;
	ListDistributionCenter centers;

	@Before
	public void initialize() {
		customers = ListCustomer.readJSON("ListCustomer.JSON");

		centers = ListDistributionCenter.readJSON("ListDistributionCenters.JSON");

		centers.calculateTotalCost(customers);

	}

	@Test
	public void solverByCostRightTest() {
		Solver solver = new Solver(centers, (uno, otro) -> uno.getTotalCost() - otro.getTotalCost());
		Solution solution = solver.solve(2);

		assertEquals(solution.getCenter(0).getIdLocal(), "Local1");
		assertEquals(solution.getCenter(1).getIdLocal(), "Local3");
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void solverByCostExceededTest() {
		Solver solver = new Solver(centers, (uno, otro) -> uno.getTotalCost() - otro.getTotalCost());
		Solution solution = solver.solve(10);
	}
	
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void solverByCosLessTest() {
		Solver solver = new Solver(centers, (uno, otro) -> uno.getTotalCost() - otro.getTotalCost());
		Solution solution = solver.solve(0);
	}

}
