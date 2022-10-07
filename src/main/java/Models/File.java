package Models;

import Enums.State;

import java.util.ArrayList;
import java.util.HashMap;

public class File {

    private Long id;
    private String constultationType;
    private ArrayList<Medicament> medicaments;
    private String depositionDate;
    private String consultationDate;
    private float montant;

    private long patientImm;

    private Long conjointID;
    private HashMap<String,Float> attachments;

    private String state;

    public long getPatientImm() {
        return patientImm;
    }

    public String getState() {
        return state;
    }



    public File(Long id, HashMap<String, Float> attachments, ArrayList<Medicament> medicaments , String constultationType, String depositionDate, String consultationDate, float montant, long patientImm, String state, Long conjointID) {
        this.id = id;
        this.constultationType = constultationType;
        this.depositionDate = depositionDate;
        this.consultationDate = consultationDate;
        this.montant = montant;
        this.patientImm = patientImm;
        this.state = state;
        this.medicaments = medicaments;
        this.attachments = attachments;
        this.conjointID = conjointID;
    }

    @Override
    public String toString() {
        return "consultation type : " + constultationType + "\n"
        + "deposition date : " + depositionDate + "\n"
        + "repayment price : " + montant + "\n"
        + "patient Matriculate  : " + patientImm + "\n"
        + "state : " + state + "\n"
        + "conjoint : " + ((conjointID == null) ? "non" : conjointID )+ "\n";
    }

    public Long getId() {
        return id;
    }

    public Long getConjointID() {
        return conjointID;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getConstultationType() {
        return constultationType;
    }

    public void setConstultationType(String constultationType) {
        this.constultationType = constultationType;
    }

    public ArrayList<Medicament> getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(ArrayList<Medicament> medicaments) {
        this.medicaments = medicaments;
    }

    public String getDepositionDate() {
        return depositionDate;
    }

    public void setDepositionDate(String depositionDate) {
        this.depositionDate = depositionDate;
    }

    public String getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(String consultationDate) {
        this.consultationDate = consultationDate;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public HashMap<String, Float> getAttachments() {
        return attachments;
    }

    public void setAttachments(HashMap<String, Float> attachments) {
        this.attachments = attachments;
    }
}
