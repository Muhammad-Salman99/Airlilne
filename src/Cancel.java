import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Cancel extends JFrame { // Sixth

    private JTextField textField, textField_1, textField_2, textField_4;

    public static void main(String[] args) {
        // Run GUI on Event Dispatch Thread (best practice)
        SwingUtilities.invokeLater(() -> new Cancel());
    }

    public Cancel() {
        initialize();
    }

    private void initialize() {
        setTitle("CANCELLATION");
        getContentPane().setBackground(Color.WHITE);
        setBounds(100, 100, 801, 472);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel Cancellation = new JLabel("CANCELLATION");
        Cancellation.setFont(new Font("Tahoma", Font.PLAIN, 31));
        Cancellation.setBounds(185, 24, 259, 38);
        add(Cancellation);

        // If you want to load image, place icon/cancel.png inside your project folder
        ImageIcon i1 = new ImageIcon("icon/cancel.png"); // simpler relative path
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel NewLabel = new JLabel(i3);
        NewLabel.setBounds(470, 100, 250, 250);
        add(NewLabel);

        JLabel PassengerNo = new JLabel("PASSENGER NO");
        PassengerNo.setFont(new Font("Tahoma", Font.PLAIN, 17));
        PassengerNo.setBounds(60, 100, 132, 26);
        add(PassengerNo);

        JLabel CancellationNo = new JLabel("CANCELLATION NO");
        CancellationNo.setFont(new Font("Tahoma", Font.PLAIN, 17));
        CancellationNo.setBounds(60, 150, 150, 27);
        add(CancellationNo);

        JLabel CancellationDate = new JLabel("CANCELLATION DATE");
        CancellationDate.setFont(new Font("Tahoma", Font.PLAIN, 17));
        CancellationDate.setBounds(60, 200, 180, 27);
        add(CancellationDate);

        JLabel Flightcode = new JLabel("FLIGHT_CODE");
        Flightcode.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Flightcode.setBounds(60, 300, 150, 27);
        add(Flightcode);

        JButton Cancel = new JButton("CANCEL");
        Cancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        Cancel.setBackground(Color.BLACK);
        Cancel.setForeground(Color.WHITE);
        Cancel.setBounds(250, 350, 150, 30);
        add(Cancel);

        textField = new JTextField();
        textField.setBounds(250, 100, 150, 27);
        add(textField);

        textField_1 = new JTextField();
        textField_1.setBounds(250, 150, 150, 27);
        add(textField_1);

        textField_2 = new JTextField();
        textField_2.setBounds(250, 200, 150, 27);
        add(textField_2);

        textField_4 = new JTextField();
        textField_4.setBounds(250, 300, 150, 27);
        add(textField_4);

        Cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String passenger_no = textField.getText();
                String cancellation_no = textField_1.getText();
                String cancellation_date = textField_2.getText();
                String flight_code = textField_4.getText();

                // Example: just show input in a dialog
                JOptionPane.showMessageDialog(null,
                        "Passenger No: " + passenger_no + "\n" +
                        "Cancellation No: " + cancellation_no + "\n" +
                        "Cancellation Date: " + cancellation_date + "\n" +
                        "Flight Code: " + flight_code);
            }
        });

        setSize(860, 500);
        setVisible(true);
        setLocationRelativeTo(null); // center on screen
    }
}

