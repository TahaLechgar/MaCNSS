
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import DAO.UserDao;
import Enums.UserType;
import Models.User;

import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;


public class Main {
    public static void main(String[] args) throws SQLException {
//        try{
        /*
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

    //        System.out.println(statement);
            UserDao userDao = new UserDao();
            User user = userDao.getUserForAuth("agent001", "pwd", UserType.Agent);
            if(user != null){
                System.out.println("Authenticated successfully");
            }else{
                System.out.println("Something went wrong!!!");
            }
            */

            Display display = new Display();
            FileDao fileDao = new FileDao();
            UserDao userDao = new UserDao();
//            System.out.println(userDao.getAll());
            display.auth();
//            User user = new User("DeeUser", "new user", "1980-08-06", UserType.Agent, "new.user@email.com");
//            userDao.save(user);
//            long id = 13;
//            Optional<User> user = userDao.get(id);
//            if(user.isPresent()){
//                userDao.delete(user.get());
//            }else{
//                System.out.println("No user was found!!!");
//            }
//
//            if (user.isPresent()) {
//                String[] params = new String[4];
//                System.out.println(user.get());
//
//                params[0] = getNewOrKeepOld(user.get().getFullname(), "fullName");
//
//                params[1] = getNewOrKeepOld(user.get().getBirthdate(), "Birthdate");
//
//                params[2] = getNewOrKeepOld(user.get().getEmail(), "Email");
//
//                params[3] = String.valueOf(id);
//
//                userDao.update(user.get(), params);
//            } else {
//                System.out.println("no user was found with the given id");
//            }

//        User user = userDao.getUserForAuth("agent001", "pwd", UserType.Agent);
//        if(user != null){
//            System.out.println("Authenticated successfully");
//        }else{
//            System.out.println("Something went wrong!!!");
//        }
//        MailSender.sendMail("tahamr08@gmail.com", "Message subject", "Message body body body");

//        String originalPassword = "password";
//        String generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
//        System.out.println(generatedSecuredPasswordHash);
//
//        boolean matched = BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash);
//        System.out.println(matched);
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
    }

    // TODO keep this method
    private static String getNewOrKeepOld(String oldPropertyValue, String propertyName){
        try {
        Scanner scanner = new Scanner(System.in);
            int choice = 0;
            if(oldPropertyValue != null) System.out.println("Old " + propertyName + " : " + oldPropertyValue);
            System.out.println("1 -> Keep old " + propertyName);
            System.out.println("2 -> enter a new one");
            choice = Integer.parseInt(scanner.nextLine());
            if(choice == 1) return oldPropertyValue;
            System.out.println("Enter the new value for " + propertyName);
            return scanner.nextLine();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return oldPropertyValue;
        }
    }
}
