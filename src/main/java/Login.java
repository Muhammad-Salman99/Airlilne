import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Login extends JFrame {
private JTextField usernameField;
private JPasswordField passwordField;

public Login() {
	setTitle("Login - Airline Management System");
	setSize(500, 400);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setLocationRelativeTo(null);

	JPanel panel = new JPanel(new BorderLayout());
	panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	// Title
	JLabel titleLabel = new JLabel("Login", SwingConstants.CENTER);
	titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
	panel.add(titleLabel, BorderLayout.NORTH);

	// Center panel
	JPanel centerPanel = new JPanel(new GridBagLayout());
	GridBagConstraints gbc = new GridBagConstraints();
	gbc.gridwidth = GridBagConstraints.REMAINDER;
	gbc.fill = GridBagConstraints.HORIZONTAL;
	gbc.insets = new Insets(10, 0, 10, 0);

	usernameField = new JTextField(20);
	passwordField = new JPasswordField(20);

	centerPanel.add(new JLabel("Username:"), gbc);
	centerPanel.add(usernameField, gbc);
	centerPanel.add(new JLabel("Password:"), gbc);
	centerPanel.add(passwordField, gbc);

	JButton loginBtn = new JButton("Login");
	JButton backBtn = new JButton("Back");
	loginBtn.setPreferredSize(new Dimension(150, 40));
	backBtn.setPreferredSize(new Dimension(150, 40));

	centerPanel.add(loginBtn, gbc);
	centerPanel.add(backBtn, gbc);
	panel.add(centerPanel, BorderLayout.CENTER);
	add(panel);

	// Event Listeners
	loginBtn.addActionListener(e -> authenticate());
	backBtn.addActionListener(e -> {
		new Home().setVisible(true); // Assuming Home class exists
		dispose();
	});
}

private void authenticate() {
	String username = usernameField.getText().trim();
	String password = new String(passwordField.getPassword());

	if (username.isEmpty() || password.isEmpty()) {
		JOptionPane.showMessageDialog(this, "Please enter both username and password",
				"Input Error", JOptionPane.ERROR_MESSAGE);
		return;
	}

	try (Connection conn = DBConnection.getConnection()) {
		String sql = "SELECT * FROM login WHERE username=? AND password=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, username);
		stmt.setString(2, password); // If you hash passwords, change here accordingly

		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			JOptionPane.showMessageDialog(this, "Login successful!",
					"Success", JOptionPane.INFORMATION_MESSAGE);
			String role = rs.getString("role"); // optional
			showDashboard(role);
		} else {
			JOptionPane.showMessageDialog(this, "Invalid credentials!",
					"Login Failed", JOptionPane.ERROR_MESSAGE);
		}

	} catch (SQLException e) {
		JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(),
				"Error", JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
	}
}

private void showDashboard(String role) {
	new Dashboard(role).setVisible(true);
	dispose();
}

// For testing
public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
		new Login().setVisible(true);
	});
}
}
