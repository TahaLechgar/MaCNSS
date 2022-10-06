package DAO;

import Connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Consultation {

    public static ArrayList<Consultation> consultations = new ArrayList<>();

    private final String type;
    private final float refundPrice;
    private final boolean refundable;

    public String getType() {
        return type;
    }

    public float getRefundPrice() {
        return refundPrice;
    }

    public boolean isRefundable() {
        return refundable;
    }

    public Consultation(String type, float refundPrice, boolean refundable){
        this.type = type;
        this.refundable = refundable;
        this.refundPrice = refundPrice;
    }

    public static void getAllConsultationTypes(){

        try{
            Connection connection = ConnectionFactory.getConnection();

            String sql = " SELECT * FROM Consultation";

            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()){

                String type = resultSet.getString("type");
                float refundPrice = resultSet.getFloat("refundPrice");
                boolean refundable = resultSet.getBoolean("refundable");

                Consultation consultation = new Consultation(type, refundPrice, refundable);
                consultations.add(consultation);
            }

        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

    }

    public String toString(){
        return "type : " + type + " refund price :  " + refundPrice + " refundable " + refundable;
    }

}
