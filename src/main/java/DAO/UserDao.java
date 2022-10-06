package DAO;

import Connection.ConnectionFactory;
import Enums.UserType;
import Mail.MailSender;
import Models.User;
import Security.BCrypt;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class UserDao implements Dao<User> {

    @Override
    public Optional<User> get(long id) {
        try {
            User user;
            Connection connection = ConnectionFactory.getConnection();

            String query = "select username, fullName, type, birthDate, email  from User where id = ?;";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String fullname = resultSet.getString("fullname");
                UserType type = resultSet.getString("type").equalsIgnoreCase("admin") ? UserType.Admin : UserType.Agent;
                String birthdate = resultSet.getString("birthdate");
                String email = resultSet.getString("email");
                user = new User(username, fullname, birthdate, type, email);
                return Optional.of(user);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    public User getUserForAuth(String username, String password, UserType type) {
        try {
            User user;
            Connection connection = ConnectionFactory.getConnection();

            String query = "select * from User where type like ? and username like ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, type.toString());
            statement.setString(2, username);

            ResultSet res = statement.executeQuery();
            System.out.println("Verifying.......");
            if (res.isBeforeFirst()) {
                res.next();
                if (BCrypt.checkpw(password, res.getString("password"))) {
                    String fullname = res.getString("fullname");
                    String email = res.getString("email");
                    String birthdate = res.getString("birthdate");
                    user = new User(username, fullname, birthdate, type, email);
                    if (user.getType() == UserType.Agent) {
                        SecureRandom random = new SecureRandom();
                        String verificationCode = new BigInteger(30, random).toString();
                        MailSender.sendMail(user.getEmail(), "Password verification", "enter this code to complete the verification " + verificationCode);
                        System.out.print("Please check your emails and enter the verification code -> ");
                        LocalTime expirationTime = LocalTime.now().plusMinutes(5);
                        while (LocalTime.now().isBefore(expirationTime)) {
                            Scanner scanner = new Scanner(System.in);
                            String receivedVerificationCode = scanner.nextLine();
                            if (verificationCode.equals(receivedVerificationCode)) {
                                if (LocalTime.now().isAfter(expirationTime)) {
                                    System.out.println("Verification code expired!!");
                                    return null;
                                }
                                return user;
                            }
                            System.out.print("Invalid verification code -> ");
                        }
                    } else
                        return user;
                }
            }
            System.out.println("Wrong credentials!!");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<User> getAll() {
        return null;
    }

    @Override
    public void save(User user) {
        try {
            if (user == null)
                return;
            Connection connection = ConnectionFactory.getConnection();
            Scanner scanner = new Scanner(System.in);
            String query = "insert into User (username, password, fullName, type, birthDate, email) values(?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            System.out.print("Enter a password -> ");
            String password = scanner.nextLine();

            statement.setString(1, user.getUsername());

            String hashedPasswod = BCrypt.hashpw(password, BCrypt.gensalt(12));
            statement.setString(2, hashedPasswod);

            statement.setString(3, user.getFullname());

            statement.setString(4, user.getType().toString());

            statement.setString(5, user.getBirthdate());

            statement.setString(6, user.getEmail());

            if (statement.executeUpdate() == 1) {
                System.out.println("User added successfully!!");
            } else {
                System.out.println("Something went wrong!!!");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * params ( usrname, fullname, birthdate, email, id)
     */
    @Override
    public void update(User user, String[] params) {
        try {
            if (user.getType() == UserType.Admin) {
                System.out.println("Cannot update admin");
                return;
            }
            Connection connection = ConnectionFactory.getConnection();
            String query = "update User set fullname = ?, birthdate = ?, email = ? where id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, params[0]);
            statement.setString(2, params[1]);
            statement.setString(3, params[2]);
            statement.setString(4, params[3]);
            if (statement.executeUpdate() == 1) {
                System.out.println("User updated successfully!!");
            } else {
                System.out.println("Something went wrong!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(User user) {
        try {
            if (user == null)
                return;
            Connection connection = ConnectionFactory.getConnection();
            String query = "delete from User where username like ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getUsername());
            if(statement.executeUpdate() == 1){
                System.out.println("User deleted successfully!!");

            }else{
                System.out.println("Something went wrong!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}


































