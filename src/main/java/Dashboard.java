import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

public Dashboard(String role) {
	setTitle("Dashboard - Airline Management System");
	setSize(800, 600);
	setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	JPanel mainPanel = new JPanel(new BorderLayout());

	// Menu Bar
	JMenuBar menuBar = new JMenuBar();

	JMenu flightMenu = new JMenu("Flights");
	JMenuItem viewFlightsItem = new JMenuItem("View Flights");
	viewFlightsItem.addActionListener(e -> new FlightInfo().setVisible(true)); // Your FlightInfo class
	flightMenu.add(viewFlightsItem);

	JMenu bookingMenu = new JMenu("Bookings");
	JMenuItem newBookingItem = new JMenuItem("New Booking");
	newBookingItem.addActionListener(e -> new BookFlight().setVisible(true)); // Your BookFlight class
	bookingMenu.add(newBookingItem);

	JMenuItem viewBookingsItem = new JMenuItem("View Bookings");
	viewBookingsItem.addActionListener(e -> new JourneyDetail().setVisible(true)); // Your JourneyDetail class
	bookingMenu.add(viewBookingsItem);

	JMenuItem cancelBookingItem = new JMenuItem("Cancel Booking");
	cancelBookingItem.addActionListener(e -> new Cancel().setVisible(true)); // Your Cancel class
	bookingMenu.add(cancelBookingItem);

	JMenu customerMenu = new JMenu("Customers");
	JMenuItem addCustomerItem = new JMenuItem("Add Customer");
	addCustomerItem.addActionListener(e -> new AddCustomer().setVisible(true)); // Your AddCustomer class
	customerMenu.add(addCustomerItem);

	JMenu helpMenu = new JMenu("Help");
	JMenuItem aboutItem = new JMenuItem("About");
	aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(this,
			"Airline Management System\nVersion 1.0\n© 2025", "About", JOptionPane.INFORMATION_MESSAGE));
	helpMenu.add(aboutItem);

	JMenu accountMenu = new JMenu("Account");
	JMenuItem logoutItem = new JMenuItem("Logout");
	logoutItem.addActionListener(e -> {
		dispose();
		new Login().setVisible(true);
	});
	accountMenu.add(logoutItem);

	// Add all menus
	menuBar.add(flightMenu);
	menuBar.add(bookingMenu);
	menuBar.add(customerMenu);
	menuBar.add(helpMenu);
	menuBar.add(accountMenu);

	setJMenuBar(menuBar);

	// Center panel with welcome label and image
	JPanel centerPanel = new JPanel(new BorderLayout());

	JLabel welcomeLabel = new JLabel("Welcome to Airline Management System", SwingConstants.CENTER);
	welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
	welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
	centerPanel.add(welcomeLabel, BorderLayout.NORTH);

	// Load image - replace "/dashboard.jpg" with your image path
	ImageIcon icon = null;
	try {
		icon = new ImageIcon("C:\\Users\\Abdul Samad Khattak\\OneDrive\\Desktop\\ChatGPT Image Jun 5, 2025, 11_58_41 PM.png");
	} catch (Exception e) {
		System.err.println("Image not found, skipping image display.");
	}


	if (icon != null) {
		JLabel imageLabel = new JLabel(icon);
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(imageLabel, BorderLayout.CENTER);
	}

	mainPanel.add(centerPanel, BorderLayout.CENTER);

	add(mainPanel);
}
}
