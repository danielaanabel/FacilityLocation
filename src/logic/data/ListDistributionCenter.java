package logic.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ListDistributionCenter {

	ArrayList<DistributionCenter> _centers;

	public ListDistributionCenter() {
		this._centers = new ArrayList<DistributionCenter>();
	}

	public void addDistributionCenter(String idLocal, double latitude, double longitude) {
		_centers.add(new DistributionCenter(idLocal, latitude, longitude));
	}

	public void calculateTotalCost(ListCustomer customers) {
		for (DistributionCenter center : _centers)
			center.assignTotalCost(customers);
	}

	public DistributionCenter getCenter(int c) {
		return _centers.get(c);
	}

	public int size() {
		return _centers.size();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<DistributionCenter> getCenters() {
		return (ArrayList<DistributionCenter>) _centers.clone();
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

	public static ListDistributionCenter readJSON(String archivo) {
		Gson gson = new Gson();
		ListDistributionCenter ret = null;

		try {
			BufferedReader br = new BufferedReader(new FileReader(archivo));
			ret = gson.fromJson(br, ListDistributionCenter.class);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

}
