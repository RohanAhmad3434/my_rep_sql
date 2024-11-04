import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Mysql {
    // Database URL, Username, and Password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/sakila";
    private static final String USER = "Rohan";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            // Register JDBC driver (not required in newer versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Successfully connected to MySQL!");

            // Create statement
            statement = connection.createStatement();

            // Query 1: Top 5 films by rental rate
            System.out.println("\nTop 5 Films by Rental Rate:");
            String sqlTopFilms = "SELECT title, rental_rate FROM film ORDER BY rental_rate DESC LIMIT 5";
            ResultSet resultSet1 = statement.executeQuery(sqlTopFilms);
            while (resultSet1.next()) {
                String title = resultSet1.getString("title");
                double rentalRate = resultSet1.getDouble("rental_rate");
                System.out.println("Title: " + title + ", Rental Rate: " + rentalRate);
            }
            resultSet1.close();

            // Query 2: Customers with more than 20 rentals (simplified)
            System.out.println("\nCustomers with More Than 20 Rentals:");
            String sqlTopCustomers = "SELECT customer_id, COUNT(rental_id) AS rental_count FROM rental GROUP BY customer_id HAVING rental_count > 20";
            ResultSet resultSet2 = statement.executeQuery(sqlTopCustomers);
            while (resultSet2.next()) {
                int customerId = resultSet2.getInt("customer_id");
                int rentalCount = resultSet2.getInt("rental_count");

                System.out.println("Customer ID: " + customerId + ", Rentals: " + rentalCount);
            }
            resultSet2.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Close connection
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
