import DAO.UserDao;
import Models.User;

import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;


public class Main {
    public static void main(String[] args) {
        try{
            long id = 13;
            UserDao userDao = new UserDao();
            Optional<User> user = userDao.get(id);

            if (user.isPresent()) {
                String[] params = new String[4];
                System.out.println(user.get());

                params[0] = getNewOrKeepOld(user.get().getFullname(), "fullName");

                params[1] = getNewOrKeepOld(user.get().getBirthdate(), "Birthdate");

                params[2] = getNewOrKeepOld(user.get().getEmail(), "Email");

                params[3] = String.valueOf(id);

                userDao.update(user.get(), params);
            } else {
                System.out.println("no user was found with the given id");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
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
