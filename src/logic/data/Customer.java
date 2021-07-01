package logic.data;

public class Customer {

	private double _latitude;
	private double _longitude;

	public Customer(double latitude, double longitude) {

		_latitude = latitude;
		_longitude = longitude;
	}

	public double getLatitude() {
		return _latitude;
	}

	public double getLongitude() {
		return _longitude;
	}

}
