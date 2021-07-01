package interfaz;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import logic.Solution;
import logic.Solver;
import logic.data.Customer;
import logic.data.DistributionCenter;
import logic.data.ListCustomer;
import logic.data.ListDistributionCenter;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Toolkit;

public class Screen extends JFrame {
	private static final long serialVersionUID = 1L;

	int width, height;

	// Hacer todos los news de aca para abajo
	CardLayout layout = new CardLayout();
	JPanel panel = new JPanel();
	JPanel menu = new JPanel();

	JMapViewer map = new JMapViewer();
	Coordinate startingCoordinate = new Coordinate(-34.528079, -58.646740);
	
	ListCustomer customers = new ListCustomer();
	ListDistributionCenter centers = new ListDistributionCenter();
	Solution solution = new Solution();

	JMenuBar menuBar = new JMenuBar();
	JMenu menuList = new JMenu("Acciones");
	JMenuItem loadData = new JMenuItem("Cargar datos");
	JMenuItem runSolver = new JMenuItem("Buscar solución");
	JMenuItem statisticsSol = new JMenuItem("Estadisticas");
	JMenuItem close = new JMenuItem("Cerrar");
	
	Font menuListFont = new Font("Berlin Sans FB", Font.PLAIN, 17);

	public Screen(int width, int height) {
	
		panel.setLayout(layout);
		addButtons();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Screen.class.getResource("/image/globo.png")));
		setSize(1024, 768);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("Tienda Planner");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		requestFocus();
	}

	private void addButtons() {
		// Setteo layouts a null, les agrego background
		menu.setLayout(null);
		menu.setBackground(Color.GRAY);
		panel.add(menu, "Menu");

		getContentPane().add(panel);
		layout.show(panel, "Menu");

		// Luego agrego labels, buttons, list a los diferentes layouts y les setteo
		// font, posicion, etc
		menu.add(map);
		map.setBounds(0, 30, 1010, 700);
		map.setDisplayPosition(startingCoordinate, 12);
		map.setZoomControlsVisible(false);

		menu.add(menuBar);
		menuBar.setBounds(0, 0, 1018, 30);
		
		menuBar.add(menuList);
		menuList.setFont(menuListFont);
		
		menuList.add(loadData);
		loadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customers = ListCustomer.readJSON("ListCustomer.JSON");
				centers = ListDistributionCenter.readJSON("ListDistributionCenters.JSON");
				map.setMapMarkerList(loadMarkers(customers.getCustomers(), centers.getCenters()));
			}
		});
		
		menuList.add(runSolver);
		runSolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (customers.size() <= 0 || centers.size() <= 0)
					JOptionPane.showMessageDialog(null, "Primero debe cargar los datos");
				else {
					centers.calculateTotalCost(customers);
					Solver solver = new Solver(centers, (uno, otro) -> uno.getTotalCost() - otro.getTotalCost());
					int quantityCenters = Integer.parseInt(JOptionPane.showInputDialog("Ingrese cantidad de locales a abrir"));
					while (quantityCenters <= 0 || quantityCenters > centers.size()) {
						JOptionPane.showMessageDialog(null, "Ingrese una cantidad de locales menor a " + centers.size() + " y mayor a cero");
						quantityCenters = Integer.parseInt(JOptionPane.showInputDialog("Ingrese cantidad de locales a abrir"));
					}
					solution = solver.solve(quantityCenters);
					map.setMapMarkerList(loadMarkers(customers.getCustomers(), solution.getListCenters()));
					String solutionJSON = solution.generateJSONPretty();
					solution.saveJSON(solutionJSON, "toBeOpenedCenters.JSON");
					JOptionPane.showMessageDialog(null, "Lista de locales a abrir guardada en archivo");
				}
			}
		});
		
		menuList.add(statisticsSol);
		statisticsSol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (solution.getListCenters().size() <= 0)
					JOptionPane.showMessageDialog(null, "Para calcular las estadisticas primero debe existir una solución");
				else {
					StringBuilder message = new StringBuilder("El promedio de distancia de cada local con los "+customers.size()+" clientes es: \n");
					for (DistributionCenter center : solution.getListCenters()) {
						String aux = ("-" + center.getIdLocal() + ": " + center.getTotalCost()/customers.getCustomers().size() + " metros \n");
						message.append(aux);
					}
					JOptionPane.showMessageDialog(null, message);
				}
			}
		});
		
		menuList.add(close);
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 Window w = SwingUtilities.getWindowAncestor(panel);
			       w.setVisible(false);
			}
		});
		

	}

	private ArrayList<MapMarker> loadMarkers(ArrayList<Customer> customers, ArrayList<DistributionCenter> centers) {
		int counter = 1;
		ArrayList<MapMarker> mapMarkerList = new ArrayList<MapMarker>();
		for (Customer customer : customers) {
			MapMarker markerCustomer = new MapMarkerDot("Cliente " + counter, new Coordinate(customer.getLatitude(), customer.getLongitude()));
			markerCustomer.getStyle().setBackColor(Color.blue);
			markerCustomer.getStyle().setColor(Color.black);
			mapMarkerList.add(markerCustomer);
			counter++;
		}
		
		counter = 1;
		for (DistributionCenter center : centers) {
			MapMarker markerCenter = new MapMarkerDot(center.getIdLocal(), new Coordinate(center.getLatitude(), center.getLongitude()));
			markerCenter.getStyle().setBackColor(Color.red);
			markerCenter.getStyle().setColor(Color.yellow);
			mapMarkerList.add(markerCenter);
			counter++;
		}
		return mapMarkerList;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Screen s = new Screen(800, 600);
	}
}