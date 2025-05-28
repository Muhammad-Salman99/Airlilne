package src;
import javax.swing.*;

public class Home extends JFrame {

    public Home() {
        setTitle("Airline Management - Home");
        setSize(450, 520); // Increased height to fit the new button
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

        JButton bookFlightBtn = new JButton("Book Flight");
        bookFlightBtn.setBounds(120, 230, 200, 30); // New button
        add(bookFlightBtn);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(120, 270, 200, 30); // Shifted down
        add(logoutBtn);

        // Action Listeners
        addCustomerBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Redirecting to Add Customer...");
            new AddCustomer().setVisible(true);
        });

        boardingPassBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Redirecting to Boarding Pass...");
            new BoardingPass().setVisible(true);
        });

        customerFlightsBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Redirecting to Customer Flights...");
            new JourneyDtails().setVisible(true); // Assuming corrected class name
        });

        cancelTicketBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Redirecting to Cancel Ticket...");
            new Cancel().setVisible(true);
        });

        flightInfoBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Redirecting to Flight Info...");
            new FlightInfo().setVisible(true);
        });

        bookFlightBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Redirecting to Book Flight...");
            new BookFlight().setVisible(true); // New frame for booking flight
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
