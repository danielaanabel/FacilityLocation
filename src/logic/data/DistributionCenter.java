package logic.data;

import logic.Haversine;

public class DistributionCenter {
	private String _idLocal;
	private double _latitude;
	private double _longitude;
	private int _totalCost;

	public DistributionCenter(String idLocal, double latitude, double longitude) {
		_idLocal = idLocal;
		_latitude = latitude;
		_longitude = longitude;
	}

	public void assignTotalCost(ListCustomer customers) {
		for (Customer customer : customers.getCustomers()) {
			int distance = Haversine.calculateDistance(getLongitude(), getLatitude(), customer.getLongitude(), customer.getLatitude());
			setTotalCost(getTotalCost() + distance);
		}
	}

	public String getIdLocal() {
		return _idLocal;
	}

	public double getLatitude() {
		return _latitude;
	}

	public double getLongitude() {
		return _longitude;
	}

	public int getTotalCost() {
		return _totalCost;
	}

	public void setTotalCost(int totalCost) {
		_totalCost = totalCost;
	}

}
