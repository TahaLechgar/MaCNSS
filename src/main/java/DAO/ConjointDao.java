package DAO;

import Models.Conjoint;

import Connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Optional;

public class ConjointDao implements Dao<Conjoint>{
    @Override
    public Optional<Conjoint> get(long id) {
        return Optional.empty();
    }

    @Override
    public ArrayList<Conjoint> getAll() {
        return null;
    }

    public List<Conjoint> getConjointsForPatient(long patientImm) {

        Connection connection = ConnectionFactory.getConnection();

        ArrayList<Conjoint> conjoints = new ArrayList<>();

        try{

            String query = "select * from Conjoint where patientImm = ? ";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, patientImm);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){

                long id = resultSet.getLong("id");
                String fullName = resultSet.getString("fullName");
                String relationship = resultSet.getString("relationship");
                String birthDate = resultSet.getString("birthDate");
                conjoints.add(new Conjoint(id, relationship, fullName, birthDate, patientImm));

            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        System.out.println(conjoints);

        return conjoints;
    }

    @Override
    public long save(Conjoint conjoint) {
        return 0;
    }

    @Override
    public void update(Conjoint conjoint, String[] params) {

    }

    @Override
    public void delete(Conjoint conjoint) {

    }
}
