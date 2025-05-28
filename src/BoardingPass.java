import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

/**
 * BoardingPass JFrame to fetch and display boarding pass details by PNR.
 * Assumes a `Conn` class exists for database connectivity.
 */
public class BoardingPass extends JFrame implements ActionListener {

    private JTextField tfpnr;
    private JLabel tfname, tfnationality, lblsrc, lbldest, labelfname, labelfcode, labeldate;
    private JButton fetchButton;

    public BoardingPass() {
        setTitle("Boarding Pass");
        setSize(1000, 450);
        setLocation(300, 150);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Header
        JLabel heading = new JLabel("AIR INDIA");
        heading.setBounds(380, 10, 450, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        add(heading);

        JLabel subheading = new JLabel("Boarding Pass");
        subheading.setBounds(360, 50, 300, 30);
        subheading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        subheading.setForeground(Color.BLUE);
        add(subheading);

        // PNR input
        JLabel lblaadhar = new JLabel("PNR DETAILS");
        lblaadhar.setBounds(60, 100, 150, 25);
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblaadhar);

        tfpnr = new JTextField();
        tfpnr.setBounds(220, 100, 150, 25);
        add(tfpnr);

        fetchButton = new JButton("Enter");
        fetchButton.setBackground(Color.BLACK);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setBounds(380, 100, 120, 25);
        fetchButton.addActionListener(this);
        add(fetchButton);

        // Labels for displaying fetched data
        tfname = createLabel("", 220, 140);
        tfnationality = createLabel("", 220, 180);
        lblsrc = createLabel("", 220, 220);
        lbldest = createLabel("", 540, 220);
        labelfname = createLabel("", 220, 260);
        labelfcode = createLabel("", 540, 260);
        labeldate = createLabel("", 220, 300);

        // Static labels describing each field
        addLabel("NAME", 60, 140);
        addLabel("NATIONALITY", 60, 180);
        addLabel("SRC", 60, 220);
        addLabel("DEST", 380, 220);
        addLabel("Flight Name", 60, 260);
        addLabel("Flight Code", 380, 260);
        addLabel("Date", 60, 300);

        // Air India logo image on the right (replace path if needed)
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/airindia.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 230, Image.SCALE_SMOOTH);
        JLabel lblimage = new JLabel(new ImageIcon(i2));
        lblimage.setBounds(600, 0, 300, 300);
        add(lblimage);

        setVisible(true);
    }

    /**
     * Helper method to create static labels
     */
    private void addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 150, 25);
        label.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(label);
    }

    /**
     * Helper method to create dynamic labels (for showing fetched data)
     */
    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 150, 25);
        add(label);
        return label;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String pnr = tfpnr.getText().trim();

        if (pnr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a PNR number.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
         
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving data from database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new BoardingPass();
    }
}
