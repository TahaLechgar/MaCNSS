import Connection.ConnectionFactory;
import DAO.UserDao;

import Enums.UserType;
import Models.User;
import Security.BCrypt;

import java.sql.*;


public class Main {
    public static void main(String[] args) throws SQLException {
//        System.out.println(statement);
        UserDao userDao = new UserDao();
        User user = userDao.getUserForAuth("agent001", "pwd", UserType.Agent);
        if(user != null){
            System.out.println("Authenticated successfully");
        }else{
            System.out.println("Something went wrong!!!");
        }
//        MailSender.sendMail("tahamr08@gmail.com", "Message subject", "Message body body body");

//        String originalPassword = "password";
//        String generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
//        System.out.println(generatedSecuredPasswordHash);
//
//        boolean matched = BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash);
//        System.out.println(matched);
    }
}
