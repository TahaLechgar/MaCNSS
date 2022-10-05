import Connection.ConnectionFactory;
import DAO.Consultation;
import Mail.MailSender;
import Security.BCrypt;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Main {
    public static void main(String[] args) throws SQLException {

        Consultation.getAllConsultationTypes();
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        System.out.println(statement);

//        MailSender.sendMail("tahamr08@gmail.com", "Message subject", "Message body body body");

        String originalPassword = "password";
        String generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
        System.out.println(generatedSecuredPasswordHash);

        boolean matched = BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash);
        System.out.println(matched);

        Display display = new Display();
        display.addFile();



    }
}
