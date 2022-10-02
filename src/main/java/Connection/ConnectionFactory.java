package Connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static final String URL = "jdbc:mysql://localhost:33060/MaCNSS";
    public static final String USER = "root";
    public static final String PASS = "123";

    /**
     * Get a connection to database
     *
     * @return Connection object
     */
    public static Connection getConnection() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver"); // Driver name
            Connection con = DriverManager.getConnection(
                    URL, USER, PASS);
            System.out.println(
                    "Connection Established successfully");
            return con;

        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
