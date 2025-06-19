
import javax.swing.*;
import java.awt.GridLayout;
public class Home extends JFrame {

	public Home() {
		setTitle("Airline Management - Home");
		setSize(450, 470);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);

		// Welcome message
		JOptionPane.showMessageDialog(this, "Welcome to the Airline Management System Home Page!");

		// Buttons
		JButton addCustomerBtn = new JButton("Add Customer");
		addCustomerBtn.setBounds(120, 30, 200, 30);
		add(addCustomerBtn);

		JButton boardingPassBtn = new JButton("Boarding Pass");
		boardingPassBtn.setBounds(120, 70, 200, 30);
		add(boardingPassBtn);

		JButton bookflightBtn = new JButton("Book Flight");
		bookflightBtn.setBounds(120, 110, 200, 30);
		add(bookflightBtn);

		JButton customerFlightsBtn = new JButton("Customer Flights");
		customerFlightsBtn.setBounds(120, 150, 200, 30);
		add(customerFlightsBtn);

		JButton cancelTicketBtn = new JButton("Cancel Ticket");
		cancelTicketBtn.setBounds(120, 190, 200, 30);
		add(cancelTicketBtn);

		JButton flightInfoBtn = new JButton("Flight Info");
		flightInfoBtn.setBounds(120, 230, 200, 30);
		add(flightInfoBtn);

		JButton logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(120, 270, 200, 30);
		add(logoutBtn);

		// Action Listeners
		addCustomerBtn.addActionListener(e -> {
			JOptionPane.showMessageDialog(this, "Redirecting to Add Customer...");
			new AddCustomer().setVisible(true);
		});

		boardingPassBtn.addActionListener(e -> {
			// Create input dialog to get flight ID and customer ID
			JPanel inputPanel = new JPanel(new GridLayout(2, 2));

			JTextField flightIdField = new JTextField();
			JTextField customerIdField = new JTextField();

			inputPanel.add(new JLabel("Flight ID:"));
			inputPanel.add(flightIdField);
			inputPanel.add(new JLabel("Customer ID:"));
			inputPanel.add(customerIdField);

			int result = JOptionPane.showConfirmDialog(
					this,
					inputPanel,
					"Enter Booking Details",
					JOptionPane.OK_CANCEL_OPTION
			);

			if (result == JOptionPane.OK_OPTION) {
				try {
					int flightId = Integer.parseInt(flightIdField.getText());
					int customerId = Integer.parseInt(customerIdField.getText());

					new BoardingPass(flightId, customerId).setVisible(true);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(
							this,
							"Please enter valid numbers for Flight ID and Customer ID",
							"Input Error",
							JOptionPane.ERROR_MESSAGE
					);
				}
			}
		});

		bookflightBtn.addActionListener(e -> {
			JOptionPane.showMessageDialog(this, "Redirecting to Book flight...");
			new BookFlight().setVisible(true);
		});

		customerFlightsBtn.addActionListener(e -> {
			JOptionPane.showMessageDialog(this, "Redirecting to Customer Flights...");
			new JourneyDetail().setVisible(true); // fixed class name
		});

		cancelTicketBtn.addActionListener(e -> {
			JOptionPane.showMessageDialog(this, "Redirecting to Cancel Ticket...");
			new Cancel().setVisible(true);
		});

		flightInfoBtn.addActionListener(e -> {
			JOptionPane.showMessageDialog(this, "Redirecting to Flight Info...");
			new FlightInfo().setVisible(true);
		});

		logoutBtn.addActionListener(e -> {
			JOptionPane.showMessageDialog(this, "You have been logged out.");
			new Login().setVisible(true);
			this.dispose();
		});
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Home().setVisible(true));
	}
}
