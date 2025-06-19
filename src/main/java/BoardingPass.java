import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class BoardingPass extends JFrame {

	public BoardingPass(int flightId, int customerId) {
		setTitle("Boarding Pass");
		setSize(500, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		// Main panel
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// Header
		JLabel titleLabel = new JLabel("BOARDING PASS", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		panel.add(titleLabel, BorderLayout.NORTH);

		// Details panel
		JPanel detailsPanel = new JPanel(new GridLayout(0, 1, 10, 10));
		detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		try (Connection conn = DBConnection.getConnection()) {
			String sql = "SELECT c.name, c.passport_number, f.flight_number, " +
					"f.departure, f.destination, f.departure_time, f.arrival_time " +
					"FROM bookings b " +
					"JOIN customers c ON b.customer_id = c.customer_id " +
					"JOIN flights f ON b.flight_id = f.flight_id " +
					"WHERE b.flight_id = ? AND b.customer_id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, flightId);
			stmt.setInt(2, customerId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm");

				addDetail(detailsPanel, "Passenger", rs.getString("name"));
				addDetail(detailsPanel, "Passport", rs.getString("passport_number"));
				addDetail(detailsPanel, "Flight", rs.getString("flight_number"));
				addDetail(detailsPanel, "From", rs.getString("departure"));
				addDetail(detailsPanel, "To", rs.getString("destination"));
				addDetail(detailsPanel, "Departure", dateFormat.format(rs.getTimestamp("departure_time")));
				addDetail(detailsPanel, "Arrival", dateFormat.format(rs.getTimestamp("arrival_time")));

				// Placeholder for seat and gate - these should ideally come from DB
				addDetail(detailsPanel, "Seat", "A12"); // To be fetched dynamically
				addDetail(detailsPanel, "Boarding Time",
						dateFormat.format(new Timestamp(rs.getTimestamp("departure_time").getTime() - 3600000)));
				addDetail(detailsPanel, "Gate", "B5"); // To be fetched dynamically

				// Barcode placeholder
				JLabel barcodeLabel = new JLabel("|| || || | | || | | | ||", SwingConstants.CENTER);
				barcodeLabel.setFont(new Font("Courier New", Font.BOLD, 20));
				barcodeLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
				detailsPanel.add(barcodeLabel);
			} else {
				JOptionPane.showMessageDialog(this,
						"No booking found for the provided customer and flight.",
						"Data Not Found", JOptionPane.WARNING_MESSAGE);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this,
					"Error generating boarding pass: " + e.getMessage(),
					"Database Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		panel.add(detailsPanel, BorderLayout.CENTER);

		// Print button
		JButton printBtn = new JButton("Print");
		printBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		printBtn.setToolTipText("Send boarding pass to printer");
		printBtn.addActionListener(e -> JOptionPane.showMessageDialog(this,
				"Boarding pass sent to printer.", "Print", JOptionPane.INFORMATION_MESSAGE));

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(printBtn);
		panel.add(buttonPanel, BorderLayout.SOUTH);

		add(panel);
	}

	private void addDetail(JPanel panel, String label, String value) {
		JPanel detailPanel = new JPanel(new BorderLayout());

		JLabel labelLbl = new JLabel(label + ": ");
		labelLbl.setFont(new Font("Arial", Font.BOLD, 14));
		labelLbl.setPreferredSize(new Dimension(120, 25));  // Fixed width for alignment

		JLabel valueLbl = new JLabel(value);
		valueLbl.setFont(new Font("Arial", Font.PLAIN, 14));

		detailPanel.add(labelLbl, BorderLayout.WEST);
		detailPanel.add(valueLbl, BorderLayout.CENTER);

		panel.add(detailPanel);
	}
}
