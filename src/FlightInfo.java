import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class FlightInfo extends JFrame {
private JTable flightTable;

public FlightInfo() {
	setTitle("Flight Information");
	setSize(900, 600);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setLocationRelativeTo(null);

	JPanel panel = new JPanel(new BorderLayout());
	panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

	// Header
	JLabel titleLabel = new JLabel("Available Flights", SwingConstants.CENTER);
	titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
	titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
	panel.add(titleLabel, BorderLayout.NORTH);

	// Table
	flightTable = new JTable();
	flightTable.setFont(new Font("Arial", Font.PLAIN, 14));
	flightTable.setRowHeight(28);
	flightTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

	JScrollPane scrollPane = new JScrollPane(flightTable);
	panel.add(scrollPane, BorderLayout.CENTER);

	// Refresh Button
	JButton refreshBtn = new JButton("Refresh");
	refreshBtn.setFont(new Font("Arial", Font.PLAIN, 16));
	refreshBtn.addActionListener(e -> loadFlights());

	JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	buttonPanel.add(refreshBtn);
	panel.add(buttonPanel, BorderLayout.SOUTH);

	add(panel);
	loadFlights();
}

private void loadFlights() {
	try (Connection conn = DBConnection.getConnection()) {
		String sql = "SELECT flight_id, flight_number, departure, destination, " +
				"departure_time, arrival_time, price FROM flights";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm");

		DefaultTableModel model = new DefaultTableModel(
				new Object[]{"ID", "Flight No", "From", "To", "Departure", "Arrival", "Price"}, 0);

		while (rs.next()) {
			model.addRow(new Object[]{
					rs.getInt("flight_id"),
					rs.getString("flight_number"),
					rs.getString("departure"),
					rs.getString("destination"),
					sdf.format(rs.getTimestamp("departure_time")),
					sdf.format(rs.getTimestamp("arrival_time")),
					"$" + rs.getDouble("price")
			});
		}

		flightTable.setModel(model);
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(this, "Error loading flights: " + e.getMessage(),
				"Database Error", JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
	}
}
}
