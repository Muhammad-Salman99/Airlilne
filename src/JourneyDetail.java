import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class JourneyDetail extends JFrame {
private JTextField bookingIdField;
private JTextArea detailsArea;

public JourneyDetail() {
	setTitle("Journey Details");
	setSize(600, 500);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setLocationRelativeTo(null);

	JPanel panel = new JPanel(new BorderLayout());
	panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	// Header
	JLabel titleLabel = new JLabel("Journey Details", SwingConstants.CENTER);
	titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
	panel.add(titleLabel, BorderLayout.NORTH);

	// Search Panel
	JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
	JLabel bookingIdLabel = new JLabel("Booking ID:");
	bookingIdLabel.setFont(new Font("Arial", Font.PLAIN, 14));

	bookingIdField = new JTextField(15);
	bookingIdField.setFont(new Font("Arial", Font.PLAIN, 14));

	JButton searchBtn = new JButton("Search");
	searchBtn.setFont(new Font("Arial", Font.PLAIN, 14));
	searchBtn.addActionListener(e -> loadJourneyDetails());

	searchPanel.add(bookingIdLabel);
	searchPanel.add(bookingIdField);
	searchPanel.add(searchBtn);
	panel.add(searchPanel, BorderLayout.NORTH);

	// Details Area
	detailsArea = new JTextArea();
	detailsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
	detailsArea.setEditable(false);
	detailsArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

	JScrollPane scrollPane = new JScrollPane(detailsArea);
	panel.add(scrollPane, BorderLayout.CENTER);

	add(panel);
}

private void loadJourneyDetails() {
	String bookingIdStr = bookingIdField.getText().trim();

	if (bookingIdStr.isEmpty()) {
		JOptionPane.showMessageDialog(this, "Please enter a Booking ID",
				"Error", JOptionPane.ERROR_MESSAGE);
		return;
	}

	try {
		int bookingId = Integer.parseInt(bookingIdStr);

		try (Connection conn = DBConnection.getConnection()) {
			String sql = "SELECT b.booking_id, b.booking_date, b.status, " +
					"c.name, c.passport_number, " +
					"f.flight_number, f.departure, f.destination, " +
					"f.departure_time, f.arrival_time, f.price " +
					"FROM bookings b " +
					"JOIN customers c ON b.customer_id = c.customer_id " +
					"JOIN flights f ON b.flight_id = f.flight_id " +
					"WHERE b.booking_id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, bookingId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm");

				StringBuilder details = new StringBuilder();
				details.append("=== JOURNEY DETAILS ===\n\n");
				details.append(String.format("%-15s: %s\n", "Booking ID", rs.getInt("booking_id")));
				details.append(String.format("%-15s: %s\n", "Status", rs.getString("status")));
				details.append(String.format("%-15s: %s\n", "Booking Date",
						dateFormat.format(rs.getTimestamp("booking_date"))));

				details.append("\n=== PASSENGER DETAILS ===\n\n");
				details.append(String.format("%-15s: %s\n", "Name", rs.getString("name")));
				details.append(String.format("%-15s: %s\n", "Passport", rs.getString("passport_number")));

				details.append("\n=== FLIGHT DETAILS ===\n\n");
				details.append(String.format("%-15s: %s\n", "Flight No", rs.getString("flight_number")));
				details.append(String.format("%-15s: %s\n", "From", rs.getString("departure")));
				details.append(String.format("%-15s: %s\n", "To", rs.getString("destination")));
				details.append(String.format("%-15s: %s\n", "Departure",
						dateFormat.format(rs.getTimestamp("departure_time"))));
				details.append(String.format("%-15s: %s\n", "Arrival",
						dateFormat.format(rs.getTimestamp("arrival_time"))));
				details.append(String.format("%-15s: $%.2f\n", "Price", rs.getDouble("price")));

				detailsArea.setText(details.toString());
			} else {
				JOptionPane.showMessageDialog(this, "No booking found with ID: " + bookingId,
						"Not Found", JOptionPane.INFORMATION_MESSAGE);
				detailsArea.setText("");
			}
		}
	} catch (NumberFormatException e) {
		JOptionPane.showMessageDialog(this, "Please enter a valid Booking ID (number)",
				"Error", JOptionPane.ERROR_MESSAGE);
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(),
				"Error", JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
	}
}
}
