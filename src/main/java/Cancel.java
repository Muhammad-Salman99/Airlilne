import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Cancel extends JFrame {
private JTextField bookingIdField;

public Cancel() {
	setTitle("Cancel Booking");
	setSize(500, 300);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setLocationRelativeTo(null);

	JPanel panel = new JPanel(new BorderLayout());
	panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	// Header
	JLabel titleLabel = new JLabel("Cancel Booking", SwingConstants.CENTER);
	titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
	panel.add(titleLabel, BorderLayout.NORTH);

	// Form Panel
	JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));

	JLabel bookingIdLabel = new JLabel("Booking ID:");
	bookingIdLabel.setFont(new Font("Arial", Font.PLAIN, 14));
	bookingIdField = new JTextField();

	formPanel.add(bookingIdLabel);
	formPanel.add(bookingIdField);

	panel.add(formPanel, BorderLayout.CENTER);

	// Button Panel
	JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

	JButton cancelBtn = new JButton("Cancel Booking");
	cancelBtn.setFont(new Font("Arial", Font.PLAIN, 14));
	cancelBtn.addActionListener(e -> cancelBooking());

	JButton backBtn = new JButton("Back");
	backBtn.setFont(new Font("Arial", Font.PLAIN, 14));
	backBtn.addActionListener(e -> dispose());

	buttonPanel.add(cancelBtn);
	buttonPanel.add(backBtn);

	panel.add(buttonPanel, BorderLayout.SOUTH);

	add(panel);
}

private void cancelBooking() {
	String bookingIdStr = bookingIdField.getText().trim();

	if (bookingIdStr.isEmpty()) {
		JOptionPane.showMessageDialog(this, "Please enter a Booking ID",
				"Error", JOptionPane.ERROR_MESSAGE);
		return;
	}

	try {
		int bookingId = Integer.parseInt(bookingIdStr);

		try (Connection conn = DBConnection.getConnection()) {
			// First get flight ID to update seats
			String getFlightSql = "SELECT flight_id FROM bookings WHERE booking_id = ?";
			PreparedStatement getFlightStmt = conn.prepareStatement(getFlightSql);
			getFlightStmt.setInt(1, bookingId);
			ResultSet rs = getFlightStmt.executeQuery();

			if (rs.next()) {
				int flightId = rs.getInt("flight_id");

				// Delete booking
				String deleteSql = "DELETE FROM bookings WHERE booking_id = ?";
				PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
				deleteStmt.setInt(1, bookingId);

				int rows = deleteStmt.executeUpdate();

				if (rows > 0) {
					// Update available seats
					String updateSql = "UPDATE flights SET seats_available = seats_available + 1 WHERE flight_id = ?";
					PreparedStatement updateStmt = conn.prepareStatement(updateSql);
					updateStmt.setInt(1, flightId);
					updateStmt.executeUpdate();

					JOptionPane.showMessageDialog(this,
							"Booking #" + bookingId + " cancelled successfully!",
							"Success", JOptionPane.INFORMATION_MESSAGE);
					bookingIdField.setText("");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Booking not found",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	} catch (NumberFormatException e) {
		JOptionPane.showMessageDialog(this, "Please enter a valid Booking ID",
				"Error", JOptionPane.ERROR_MESSAGE);
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(this, "Error cancelling booking: " + e.getMessage(),
				"Error", JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
	}
}
}