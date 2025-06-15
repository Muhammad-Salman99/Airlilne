import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class AddCustomer extends JFrame {

private JTextField nameField, passportField, nationalityField, contactField, emailField;
private JComboBox<String> genderBox;
private JFormattedTextField dobField;

public AddCustomer() {
	setTitle("Add New Customer");
	setSize(600, 450);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setLocationRelativeTo(null);

	JPanel panel = new JPanel(new BorderLayout());
	panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	JLabel titleLabel = new JLabel("Add New Customer", SwingConstants.CENTER);
	titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
	panel.add(titleLabel, BorderLayout.NORTH);

	JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));

	nameField = new JTextField();
	passportField = new JTextField();
	genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
	nationalityField = new JTextField();

	dobField = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));
	dobField.setToolTipText("Format: yyyy-mm-dd");

	contactField = new JTextField();
	emailField = new JTextField();

	formPanel.add(createLabel("Full Name:"));
	formPanel.add(nameField);
	formPanel.add(createLabel("Passport Number:"));
	formPanel.add(passportField);
	formPanel.add(createLabel("Gender:"));
	formPanel.add(genderBox);
	formPanel.add(createLabel("Nationality:"));
	formPanel.add(nationalityField);
	formPanel.add(createLabel("Date of Birth (yyyy-mm-dd):"));
	formPanel.add(dobField);
	formPanel.add(createLabel("Contact Number:"));
	formPanel.add(contactField);
	formPanel.add(createLabel("Email:"));
	formPanel.add(emailField);

	panel.add(formPanel, BorderLayout.CENTER);

	JPanel buttonPanel = new JPanel();
	JButton saveBtn = new JButton("Save");
	saveBtn.addActionListener(e -> saveCustomer());
	JButton cancelBtn = new JButton("Cancel");
	cancelBtn.addActionListener(e -> dispose());

	buttonPanel.add(saveBtn);
	buttonPanel.add(cancelBtn);
	panel.add(buttonPanel, BorderLayout.SOUTH);

	add(panel);
}

private JLabel createLabel(String text) {
	JLabel label = new JLabel(text);
	label.setFont(new Font("Arial", Font.PLAIN, 14));
	return label;
}

private void saveCustomer() {
	String name = nameField.getText().trim();
	String passport = passportField.getText().trim();
	String gender = (String) genderBox.getSelectedItem();
	String nationality = nationalityField.getText().trim();
	String dob = dobField.getText().trim();
	String contact = contactField.getText().trim();
	String email = emailField.getText().trim();

	if (name.isEmpty() || passport.isEmpty() || dob.isEmpty()) {
		JOptionPane.showMessageDialog(this, "Name, Passport No, and DOB are required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
		return;
	}

	try (Connection conn = DBConnection.getConnection()) {
		String sql = "INSERT INTO customers (name, passport_number, gender, nationality, date_of_birth, contact_number, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, name);
		stmt.setString(2, passport);
		stmt.setString(3, gender);
		stmt.setString(4, nationality);
		stmt.setDate(5, Date.valueOf(dob)); // yyyy-MM-dd format
		stmt.setString(6, contact);
		stmt.setString(7, email);

		int rows = stmt.executeUpdate();
		if (rows > 0) {
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				JOptionPane.showMessageDialog(this, "Customer added! ID: " + id, "Success", JOptionPane.INFORMATION_MESSAGE);
				clearForm();
			}
		}
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
	} catch (IllegalArgumentException ex) {
		JOptionPane.showMessageDialog(this, "Invalid date format. Use yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
	}
}

private void clearForm() {
	nameField.setText("");
	passportField.setText("");
	nationalityField.setText("");
	dobField.setText("");
	contactField.setText("");
	emailField.setText("");
	genderBox.setSelectedIndex(0);
}
}
