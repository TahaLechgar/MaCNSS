package Models;

import java.util.ArrayList;
import java.util.HashMap;

public class Patient {

    private String consultationType;
    private ArrayList<Medicament> medicaments;
    private String depositionDate;
    private String consultationDate;
    private Float montant;
    private HashMap<String, Float> attachments;

    public String getConsultationType() {
        return consultationType;
    }

    public void setConsultationType(String consultationType) {
        this.consultationType = consultationType;
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

    public Float getMontant() {
        return montant;
    }

    public void setMontant(Float montant) {
        this.montant = montant;
    }

    public HashMap<String, Float> getAttachments() {
        return attachments;
    }

    public void setAttachments(HashMap<String, Float> attachments) {
        this.attachments = attachments;
    }
}
