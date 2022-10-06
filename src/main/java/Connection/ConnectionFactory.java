package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionFactory {
    public static String URL;
    public static String USER;
    public static String PASS;

    /**
     * Get a connection to database
     *
     * @return Connection object
     */
    public static Connection getConnection() {
        try {
            Dotenv dotenv = Dotenv.load();
            URL = dotenv.get("URL");
            USER = dotenv.get("USER");
            PASS = dotenv.get("PASSWORD");
//            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                    URL, USER, PASS);

        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }
}