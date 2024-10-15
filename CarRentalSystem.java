
import java.util.Scanner;

public class CarRentalSystem {
    public CarRentalSystem() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CarRental carRental = new CarRental();

        while(true) {
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. List Available Cars");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Car ID: ");
                    int CarID = scanner.nextInt();
                    System.out.print("Enter Customer ID: ");
                    int customerId = scanner.nextInt();
                    carRental.rentCar(CarID, customerId);
                    break;
                case 2:
                    System.out.print("Enter Car ID to return: ");
                    int returnCarId = scanner.nextInt();
                    carRental.returnCar(returnCarId);
                    break;
                case 3:
                    carRental.listAvailableCars();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
