package logic.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ListCustomer {
	ArrayList<Customer> _customers;

	public ListCustomer() {
		this._customers = new ArrayList<Customer>();
	}

	public void addCustomer(double latitude, double longitude) {
		_customers.add(new Customer(latitude, longitude));
	}

	public int size() {
		return _customers.size();
	}

	public Customer getCustomer(int c) {
		return _customers.get(c);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Customer> getCustomers() {
		return (ArrayList<Customer>) _customers.clone();
	}

	// metodos JSON
	public String generateJSONPretty() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(this);

		return json;
	}

	public void saveJSON(String jsonParaGuardar, String archivoDestino) {
		try {
			FileWriter writer = new FileWriter(archivoDestino);
			writer.write(jsonParaGuardar);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ListCustomer readJSON(String archivo) {
		Gson gson = new Gson();
		ListCustomer ret = null;

		try {
			BufferedReader br = new BufferedReader(new FileReader(archivo));
			ret = gson.fromJson(br, ListCustomer.class);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

}
