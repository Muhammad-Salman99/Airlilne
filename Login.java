package airlinemanagementsystem;

import java.util.Scanner;
//login.class
public class Login {

    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "1234";

    public void startLogin() {
        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;

        while (!loggedIn) {
            System.out.println("=== Airline Management System Login ===");
            System.out.print("Enter Username: ");
            String username = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            if (username.equals(VALID_USERNAME) && password.equals(VALID_PASSWORD)) {
                System.out.println("Login successful!\n");
                new Home().showMenu(scanner);  // Simulated next step
                loggedIn = true;
            } else {
                System.out.println("Invalid Username or Password.");
                System.out.print("Try again? (yes/no): ");
                String retry = scanner.nextLine();
                if (!retry.equalsIgnoreCase("yes")) {
                    System.out.println("Exiting login...");
                    break;
                }
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Login().startLogin();
    }
}

