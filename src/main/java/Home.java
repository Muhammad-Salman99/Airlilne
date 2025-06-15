
import javax.swing.*;

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

		JButton customerFlightsBtn = new JButton("Customer Flights");
		customerFlightsBtn.setBounds(120, 110, 200, 30);
		add(customerFlightsBtn);

		JButton cancelTicketBtn = new JButton("Cancel Ticket");
		cancelTicketBtn.setBounds(120, 150, 200, 30);
		add(cancelTicketBtn);

		JButton flightInfoBtn = new JButton("Flight Info");
		flightInfoBtn.setBounds(120, 190, 200, 30);
		add(flightInfoBtn);

		JButton logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(120, 230, 200, 30);
		add(logoutBtn);

		// Action Listeners
		addCustomerBtn.addActionListener(e -> {
			JOptionPane.showMessageDialog(this, "Redirecting to Add Customer...");
			new AddCustomer().setVisible(true);
		});

		boardingPassBtn.addActionListener(e -> {
//			JOptionPane.showMessageDialog(this, "Redirecting to Boarding Pass...");
//			new BoardingPass().setVisible(true);
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
