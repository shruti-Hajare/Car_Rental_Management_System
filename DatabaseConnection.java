import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/carschema";
    private static final String USER = "root";
    private static final String PASSWORD = "Shruti@05";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/carschema", "root", "Shruti@05");
    }
}
