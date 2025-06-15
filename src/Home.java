import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {
public Home() {
	setTitle("Airline Management System");
	setSize(800, 600);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLocationRelativeTo(null);

	JPanel panel = new JPanel(new BorderLayout());
	panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

	// Header
	JLabel titleLabel = new JLabel("✈️ Airline Management System", SwingConstants.CENTER);
	titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
	titleLabel.setForeground(new Color(25, 50, 112));
	panel.add(titleLabel, BorderLayout.NORTH);

	// Center Panel with buttons
	JPanel centerPanel = new JPanel(new GridBagLayout());
	centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
	GridBagConstraints gbc = new GridBagConstraints();
	gbc.gridwidth = GridBagConstraints.REMAINDER;
	gbc.insets = new Insets(20, 0, 20, 0);

	JButton loginBtn = new JButton("Login");
	styleButton(loginBtn);

	JButton exitBtn = new JButton("Exit");
	styleButton(exitBtn);

	centerPanel.add(loginBtn, gbc);
	centerPanel.add(exitBtn, gbc);

	panel.add(centerPanel, BorderLayout.CENTER);

	// Footer
	JLabel footerLabel = new JLabel("© 2025 Airline Management System", SwingConstants.CENTER);
	footerLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
	footerLabel.setForeground(Color.GRAY);
	panel.add(footerLabel, BorderLayout.SOUTH);

	add(panel);

	// Event Listeners
	loginBtn.addActionListener(e -> {
		new Login().setVisible(true);
		dispose();
	});

	exitBtn.addActionListener(e -> System.exit(0));
}

private void styleButton(JButton button) {
	button.setPreferredSize(new Dimension(220, 45));
	button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	button.setFocusPainted(false);
	button.setBackground(new Color(70, 130, 180));
	button.setForeground(Color.WHITE);
}
}
