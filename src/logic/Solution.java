package logic;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import logic.data.DistributionCenter;

public class Solution {
	// esta seria la solucion final que se mostraria
	private ArrayList<DistributionCenter> _centers;

	public Solution() {
		_centers = new ArrayList<DistributionCenter>();
	}

	public void addCenter(DistributionCenter center) {
		_centers.add(center);
	}

	public DistributionCenter getCenter(int c) {
		return _centers.get(c);
	}

	public int size() {
		return _centers.size();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<DistributionCenter> getListCenters() {
		return (ArrayList<DistributionCenter>) _centers.clone();
	}

	// Metodos JSON
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

}
