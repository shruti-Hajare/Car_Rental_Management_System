import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarRental {

    public void rentCar(int carId, int CustomerID) {
        String rentCarQuery = "UPDATE cars SET status = 'rented' WHERE CarID = ?";
        String insertRentalQuery = "INSERT INTO rentals (CarID, CustomerID, RentalDate) VALUES (?, ?, NOW())";

        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false); // Start transaction

            try (PreparedStatement rentCarStmt = connection.prepareStatement(rentCarQuery);
                 PreparedStatement insertRentalStmt = connection.prepareStatement(insertRentalQuery)) {

                // Update car status to rented
                rentCarStmt.setInt(1, carId);
                rentCarStmt.executeUpdate();

                // Insert rental record
                insertRentalStmt.setInt(1, carId);
                insertRentalStmt.setInt(2, CustomerID);
                insertRentalStmt.executeUpdate();

                connection.commit(); // Commit transaction
                System.out.println("Car rented successfully.");
            } catch (SQLException e) {
                connection.rollback(); // Rollback in case of error
                System.out.println("Rental failed: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }

    public void returnCar(int CarID) {
        String returnCarQuery = "UPDATE cars SET status = 'Available' WHERE CarID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement returnCarStmt = connection.prepareStatement(returnCarQuery)) {

            returnCarStmt.setInt(1, CarID);
            returnCarStmt.executeUpdate();
            System.out.println("Car returned successfully.");
        } catch (SQLException e) {
            System.out.println("Return failed: " + e.getMessage());
        }
    }

    public void listAvailableCars() {
        String query = "SELECT * FROM cars WHERE status = 'available'";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("Available Cars:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("CarID") +
                        ", Make: " + rs.getString("Make") +
                        ", Model: " + rs.getString("Model") +
                        ", Year: " + rs.getInt("Year"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving cars: " + e.getMessage());
        }
    }
}
