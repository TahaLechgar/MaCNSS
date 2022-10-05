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
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserDao implements Dao<User> {

    @Override
    public Optional<User> get(long id) {
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
    public List<User> getAll() {
        return null;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void update(User user, String[] params) {

    }

    @Override
    public void delete(User user) {

    }
}
