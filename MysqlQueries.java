
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class MysqlQueries {
    // Database URL, Username, and Password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/sakila";
    private static final String USER = "Rohan";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            // Step 2: Create Connection
            // Register JDBC driver (automatically done in newer versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // b. Open a connection
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Successfully connected to MySQL!");

            // Step 3: Create statement
            statement = connection.createStatement();

            // Step 4: Execute SQL SELECT query
            String sql = "SELECT actor_id, first_name, last_name, last_update FROM actor";
            ResultSet resultSet = statement.executeQuery(sql);

            // Process the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("actor_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Timestamp lastUpdate = resultSet.getTimestamp("last_update");

                System.out.println("ID: " + id + ", First Name: " + firstName + ", Last Name: " + lastName + ", Last Update: " + lastUpdate);
            }

            resultSet.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // hello
        } finally {
            // Step 5: Close connection
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
