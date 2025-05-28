package src;
import javax.swing.*;

public class BookFlight extends JFrame {

    public BookFlight() {
        setTitle("Book Flight");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel label = new JLabel("Welcome to the Book Flight Page");
        label.setBounds(80, 30, 250, 30);
        add(label);

        // Sample inputs
        JLabel flightIdLabel = new JLabel("Flight ID:");
        flightIdLabel.setBounds(50, 80, 100, 25);
        add(flightIdLabel);

        JTextField flightIdField = new JTextField();
        flightIdField.setBounds(150, 80, 150, 25);
        add(flightIdField);

        JLabel customerIdLabel = new JLabel("Customer ID:");
        customerIdLabel.setBounds(50, 120, 100, 25);
        add(customerIdLabel);

        JTextField customerIdField = new JTextField();
        customerIdField.setBounds(150, 120, 150, 25);
        add(customerIdField);

        JButton bookBtn = new JButton("Book");
        bookBtn.setBounds(150, 170, 100, 30);
        add(bookBtn);

        bookBtn.addActionListener(e -> {
            String flightId = flightIdField.getText();
            String customerId = customerIdField.getText();
            // Booking logic placeholder
            JOptionPane.showMessageDialog(this, "Flight " + flightId + " booked for customer " + customerId + "!");
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BookFlight().setVisible(true));
    }
}
