import Connection.ConnectionFactory;
import Mail.MailSender;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;



public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        System.out.println(statement);

        MailSender.sendMail("tahamr08@gmail.com", "Message subject", "Message body body body");

    }
}
