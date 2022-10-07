package DAO;

import Models.Attachment;
import Models.File;

import Connection.ConnectionFactory;
import Models.Medicament;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class FileDao implements Dao<File> {
    @Override
    public Optional<File> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<File> getAll() {

        try {
            Connection connection = ConnectionFactory.getConnection();
            String sql = "select * from Dossier";
            PreparedStatement prepareStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = prepareStatement.executeQuery();
            List<File> allFiles = new ArrayList<>();

            while (resultSet.next()) {
                String depositDate = resultSet.getString("depositDate");
                String consultationType = resultSet.getString("consultationType");
                float repaymentAmount = resultSet.getFloat("repaymentAmount");
                long patientImm = resultSet.getLong("patientImm");
                String state = resultSet.getString("state");
                long id = resultSet.getLong("id");
                File newFile = new File(id, null, null, consultationType, depositDate, null, repaymentAmount, patientImm, state, null);
                allFiles.add(newFile);
            }

            return allFiles;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public long save(File file) {
        try {
            Connection connection = ConnectionFactory.getConnection();

            String sql = "INSERT into Dossier (state, patientImm, consultationType, depositDate,  repaymentAmount, conjoint_ID) " +
                    "values (?, ?, ?, ?, ?, ?)";

            PreparedStatement prepareStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString(1, file.getState());
            prepareStatement.setLong(2, file.getPatientImm());
            prepareStatement.setString(3, file.getConstultationType());
            prepareStatement.setString(4, file.getDepositionDate());
            prepareStatement.setFloat(5, file.getMontant());
            if (file.getConjointID() == null) {
                prepareStatement.setNull(6, Types.BIGINT);
            } else {
                prepareStatement.setLong(6, file.getConjointID());
            }


            int affectedRows = prepareStatement.executeUpdate();
            System.out.println("Affected rows : " + affectedRows);
            try (ResultSet generatedKeys = prepareStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                } else {
                    return 0;
                }
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }

    public void saveFile(File file) {
        long newFileID = this.save(file);
        if (newFileID == 0) {
            System.out.println("File is not saved .. something went wrong");
            return;
        }
        if (file.getMedicaments() != null) {
            for (Medicament medicament : file.getMedicaments()) {
                try {
                    Connection connection = ConnectionFactory.getConnection();
                    String query = "insert into Prescription (medicationId, dossierId, consultationDate, quantity)" +
                            "values (?, ?, ?, ?)";

                    PreparedStatement statement = connection.prepareStatement(query);

                    statement.setLong(1, medicament.getCodeBare());
                    statement.setLong(2, newFileID);
                    statement.setString(3, file.getConsultationDate());
                    statement.setInt(4, 1);

                    statement.executeUpdate();

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        if (file.getAttachments() != null) {

            for (HashMap.Entry<String, Float> attachment : file.getAttachments().entrySet()) {
                String name = attachment.getKey();
                Float price = attachment.getValue();
                try {
                    Connection connection = ConnectionFactory.getConnection();
                    String query = "insert into Attachement (type, dossierId, price)" +
                            "values (?, ?, ?)";

                    PreparedStatement statement = connection.prepareStatement(query);

                    statement.setString(1, name);
                    statement.setLong(2, newFileID);
                    statement.setFloat(3, price);

                    int affectedRows = statement.executeUpdate();

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

            }
        }
    }

    public Optional<ArrayList<Attachment>> getAttachmentsOfFile(long dossierId) {
        try {

            Connection connection = ConnectionFactory.getConnection();
            String query = "select type, dossierId, price from Attachement where dossierId = ?;";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, dossierId);

            ResultSet resultSet = statement.executeQuery();

            ArrayList<Attachment> attachments = new ArrayList<>();

            while (resultSet.next()) {
                float price = resultSet.getFloat("price");
                String type = resultSet.getString("type");
                attachments.add(new Attachment(dossierId, price, type));
            }

            return Optional.of(attachments);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return Optional.empty();
    }

    public Optional<ArrayList<Medicament>> getMedicamentsOfFile(long dossierId) {
        try {

            Connection connection = ConnectionFactory.getConnection();
            String query = "select Medications.name, Medications.price ,Medications.codeBarre, Medications.repaymentPrice\n" +
                    "from Medications join Prescription P on Medications.codeBarre = P.medicationId\n" +
                    "where P.dossierId = ?;";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, dossierId);

            ResultSet resultSet = statement.executeQuery();

            ArrayList<Medicament> medicaments = new ArrayList<>();

            while (resultSet.next()) {
                float price = resultSet.getFloat("price");
                String name = resultSet.getString("name");
                float repaymentPrice = resultSet.getFloat("repaymentPrice");
                long codeBarre = resultSet.getLong("codeBarre");
                medicaments.add(new Medicament(codeBarre, name, price, repaymentPrice));
            }

            return Optional.of(medicaments);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return Optional.empty();
    }


    public List<File> getFileOfPatient(long patientImm) {

        try {
            Connection connection = ConnectionFactory.getConnection();
            String sql = "select * from Dossier where patientImm = ?";
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setLong(1, patientImm);
            ResultSet resultSet = prepareStatement.executeQuery();
            List<File> allFiles = new ArrayList<>();

            while (resultSet.next()) {
                String depositDate = resultSet.getString("depositDate");
                String consultationType = resultSet.getString("consultationType");
                float repaymentAmount = resultSet.getFloat("repaymentAmount");
                long patientImmatricule = resultSet.getLong("patientImm");
                String state = resultSet.getString("state");
                long id = resultSet.getLong("id");
                File newFile = new File(id, null, null, consultationType, depositDate, null, repaymentAmount, patientImm, state, null);
                allFiles.add(newFile);
            }

            return allFiles;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }


    @Override
    public void update(File file, String[] params) {

    }

    @Override
    public void delete(File file) {

    }

    public boolean validate(File file) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = "select state from Dossier where id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, file.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String state = resultSet.getString("state");
                if (state.equalsIgnoreCase("refused")) {
                    System.out.println("File was refused");
                    return false;
                }
                if (state.equalsIgnoreCase("pending")) {
                    query = "update Dossier set state = 'validated' where id = ?";
                    statement = connection.prepareStatement(query);
                    statement.setLong(1, file.getId());
                    int rowsAffected = statement.executeUpdate();
                    if (rowsAffected == 1) {
                        System.out.println("File validated successfully!!");
                        return true;
                    } else {
                        System.out.println("Something went wrong!!!");
                        return false;
                    }
                }
                System.out.println("File already validated!!");
                return true;
            } else {
                System.out.println("File not found");
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
