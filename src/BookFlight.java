import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.HashMap;

public class BookFlight extends JFrame {

private JComboBox<String> flightCombo;
private JComboBox<Integer> customerIdCombo;
private JButton bookBtn;

// Maps displayed flight info to flight ID
private HashMap<String, Integer> flightMap = new HashMap<>();

public BookFlight() {
	setTitle("Book Flight");
	setSize(600, 400);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setLocationRelativeTo(null);

	JPanel panel = new JPanel(new BorderLayout());
	panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	JLabel titleLabel = new JLabel("Book a Flight", SwingConstants.CENTER);
	titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
	panel.add(titleLabel, BorderLayout.NORTH);

	JPanel formPanel = new JPanel(new GridLayout(4, 2, 15, 15));
	formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

	JLabel flightLabel = new JLabel("Select Flight:");
	flightLabel.setFont(new Font("Arial", Font.PLAIN, 16));
	flightCombo = new JComboBox<>();
	flightCombo.setFont(new Font("Arial", Font.PLAIN, 16));

	JLabel customerLabel = new JLabel("Select Customer:");
	customerLabel.setFont(new Font("Arial", Font.PLAIN, 16));
	customerIdCombo = new JComboBox<>();
	customerIdCombo.setFont(new Font("Arial", Font.PLAIN, 16));

	formPanel.add(flightLabel);
	formPanel.add(flightCombo);
	formPanel.add(customerLabel);
	formPanel.add(customerIdCombo);

	panel.add(formPanel, BorderLayout.CENTER);

	JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

	bookBtn = new JButton("Book Flight");
	bookBtn.setFont(new Font("Arial", Font.PLAIN, 16));
	bookBtn.addActionListener(e -> bookFlight());

	JButton cancelBtn = new JButton("Cancel");
	cancelBtn.setFont(new Font("Arial", Font.PLAIN, 16));
	cancelBtn.addActionListener(e -> dispose());

	buttonPanel.add(bookBtn);
	buttonPanel.add(cancelBtn);

	panel.add(buttonPanel, BorderLayout.SOUTH);
	add(panel);

	loadFlightDetails();
	loadCustomerIds();
}

private void loadFlightDetails() {
	try (Connection conn = DBConnection.getConnection()) {
		String sql = "SELECT flight_id, flight_number, departure, destination, airline FROM flights";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			int id = rs.getInt("flight_id");
			String display = rs.getString("flight_number") + " (" +
					rs.getString("departure") + " → " +
					rs.getString("destination") + ") - " +
					rs.getString("airline");
			flightMap.put(display, id);
			flightCombo.addItem(display);
		}
	} catch (SQLException e) {
		showError("Error loading flight details: " + e.getMessage());
	}
}

private void loadCustomerIds() {
	try (Connection conn = DBConnection.getConnection()) {
		String sql = "SELECT customer_id FROM customers";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			customerIdCombo.addItem(rs.getInt("customer_id"));
		}
	} catch (SQLException e) {
		showError("Error loading customers: " + e.getMessage());
	}
}

private void bookFlight() {
	String selectedFlight = (String) flightCombo.getSelectedItem();
	Integer flightId = selectedFlight != null ? flightMap.get(selectedFlight) : null;
	Integer customerId = (Integer) customerIdCombo.getSelectedItem();

	if (flightId == null || customerId == null) {
		showError("Please select both flight and customer.");
		return;
	}

	try (Connection conn = DBConnection.getConnection()) {
		String insertSql = "INSERT INTO bookings (flight_id, customer_id, booking_date) VALUES (?, ?, NOW())";
		PreparedStatement insertStmt = conn.prepareStatement(insertSql);
		insertStmt.setInt(1, flightId);
		insertStmt.setInt(2, customerId);
		int rows = insertStmt.executeUpdate();

		if (rows > 0) {
			JOptionPane.showMessageDialog(this, "Booking successful!",
					"Success", JOptionPane.INFORMATION_MESSAGE);
			new BoardingPass(flightId, customerId).setVisible(true);
			dispose();
		}
	} catch (SQLException e) {
		showError("Error creating booking: " + e.getMessage());
	}
}

private void showError(String message) {
	JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
}
}
