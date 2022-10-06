package DAO;

import Models.Medicament;

import Connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class MedicamentDao implements Dao<Medicament>{

    @Override
    public Optional<Medicament> get(long codeBarre) {
        try{
            Connection connection = ConnectionFactory.getConnection();

            String sql = " SELECT * FROM Medications where codeBarre = ?";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, codeBarre);
            ResultSet resultSet = stmt.executeQuery();
            if(!resultSet.next()){
               return Optional.empty();
            }
            String medicamentName = resultSet.getString("name");
            float prixPublicMaroc = resultSet.getFloat("price");
            float prixRemoboursement = resultSet.getFloat("repaymentPrice");
            return Optional.of(new Medicament(codeBarre, medicamentName, prixPublicMaroc, prixRemoboursement));

        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<Medicament> getAll() {
        return null;
    }

    @Override
    public long save(Medicament medicament) {
        return 0;
    }

    @Override
    public void update(Medicament medicament, String[] params) {

    }

    @Override
    public void delete(Medicament medicament) {

    }



}
