
//home.class
public class Home {

    public void showMenu(Scanner scanner) {
        int choice;

        do {
            System.out.println("\n=== AIR Pakistan WELCOMES YOU ===");
            System.out.println("1. Add Customer Details");
            System.out.println("2. Flight Details");
            System.out.println("3. Book Flight");
            System.out.println("4. Journey Details");
            System.out.println("5. Cancel Ticket");
            System.out.println("6. Boarding Pass");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println(">> Add Customer Details");
                    break;
                case 2:
                    System.out.println(">> Flight Details");
                    break;
                case 3:
                    System.out.println(">> Book Flight");
                    break;
                case 4:
                    System.out.println(">> Journey Details");
                    break;
                case 5:
                    System.out.println(">> Cancel Ticket");
                    break;
                case 6:
                    System.out.println(">> Boarding Pass");
                    break;
                case 0:
                    System.out.println("Exiting Home Menu. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        new Home().showMenu(scanner);
        scanner.close();
    }
}