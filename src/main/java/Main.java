import Connection.ConnectionFactory;
import DAO.Consultation;
import DAO.FileDao;
import Models.Attachment;
import Models.Medicament;
import Security.BCrypt;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;


public class Main {
    public static void main(String[] args) throws SQLException {

        Consultation.getAllConsultationTypes();
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        System.out.println(statement);


        String originalPassword = "password";
        String generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
        System.out.println(generatedSecuredPasswordHash);

        boolean matched = BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash);
        System.out.println(matched);

        Display display = new Display();
        FileDao fileDao = new FileDao();
        Optional<ArrayList<Attachment>> attachmentsOptional = fileDao.getAttachmentsOfFile(22);
        attachmentsOptional.ifPresent(System.out::println);
        Optional<ArrayList<Medicament>> medicamentsOptional = fileDao.getMedicamentsOfFile(22);
        medicamentsOptional.ifPresent(System.out::println);

        System.out.println(fileDao.getAll());
        display.displayAllFiles(1);
//        display.addFile();

    }
}
