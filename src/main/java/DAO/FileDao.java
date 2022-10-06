package DAO;

import Models.File;

import Connection.ConnectionFactory;
import Models.Medicament;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class FileDao implements Dao<File>{
    @Override
    public Optional<File> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<File> getAll() {
        return null;
    }

    @Override
    public long save(File file) {
        try{
            Connection connection = ConnectionFactory.getConnection();

            String sql = "INSERT into Dossier (state, patientImm, consultationType, depositDate,  repaymentAmount, conjoint_ID) " +
                        "values (?, ?, ?, ?, ?, ?)";

            PreparedStatement prepareStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString( 1, file.getState());
            prepareStatement.setLong(   2, file.getPatientImm());
            prepareStatement.setString( 3, file.getConstultationType());
            prepareStatement.setString( 4, file.getDepositionDate());
            prepareStatement.setFloat(  5, file.getMontant());
            prepareStatement.setLong(6, file.getConjointID());


            int affectedRows = prepareStatement.executeUpdate();
            System.out.println("Affected rows : " + affectedRows);
            try (ResultSet generatedKeys = prepareStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
                else {
                    return 0;
                }
            }

        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return 0;
        }
    }

    public void saveFile(File file){
        long newFileID = this.save(file);
        if(newFileID == 0){
            System.out.println("File is not saved .. something went wrong");
            return;
        }
        for(Medicament medicament: file.getMedicaments()){
            try{
                Connection connection = ConnectionFactory.getConnection();
                String query = "insert into Prescription (medicationId, dossierId, consultationDate, quantity)" +
                        "values (?, ?, ?, ?)";

                PreparedStatement statement = connection.prepareStatement(query);

                statement.setLong(1, medicament.getCodeBare());
                statement.setLong(2, newFileID);
                statement.setString(3, file.getConsultationDate());
                statement.setInt(4, 1);

                int affectedRows = statement.executeUpdate();



            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }


        for (HashMap.Entry<String, Float> attachment : file.getAttachments().entrySet()) {
            String name = attachment.getKey();
            Float price = attachment.getValue();
            try{
                Connection connection = ConnectionFactory.getConnection();
                String query = "insert into Attachement (type, dossierId, price)" +
                        "values (?, ?, ?)";

                PreparedStatement statement = connection.prepareStatement(query);

                statement.setString(1, name);
                statement.setLong(2, newFileID);
                statement.setFloat(3, price);


                int affectedRows = statement.executeUpdate();

            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }

        }


    }

    @Override
    public void update(File file, String[] params) {

    }

    @Override
    public void delete(File file) {

    }
}
