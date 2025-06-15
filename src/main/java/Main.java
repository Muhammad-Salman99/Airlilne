//package main.java;

import javax.swing.*;

public class Main {
	public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

			// Optional splash or preload message
			JOptionPane.showMessageDialog(null,
					"Welcome to Airline Management System",
					"Starting Application",
					JOptionPane.INFORMATION_MESSAGE);

			new Home().setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"Failed to start application: " + e.getMessage(),
					"Startup Error",
					JOptionPane.ERROR_MESSAGE);
		}
	});
}
}
