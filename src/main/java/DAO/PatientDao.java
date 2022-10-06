package DAO;

import Connection.ConnectionFactory;
import Models.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Optional;

public class PatientDao implements Dao<Patient> {
    @Override
    public Optional<Patient> get(long id) {
        try {
            Connection connection = ConnectionFactory.getConnection();

            String query = "select fullName, birthDate, email, cin from Patient where immatricule = ?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String fullName = resultSet.getString("fullName");
                String birthdate = resultSet.getString("birthdate");
                String email = resultSet.getString("email");
                String cin = resultSet.getString("cin");
                return Optional.of(new Patient(fullName, birthdate, email, cin));
            }
            return Optional.empty();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public ArrayList<Patient> getAll() {
        return null;
    }

    @Override
    public long save(Patient patient) {
        return 0;
    }

    @Override
    public void update(Patient patient, String[] params) {

    }

    @Override
    public void delete(Patient patient) {

    }
}
