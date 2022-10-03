package Models;

import java.util.ArrayList;
import java.util.HashMap;

public class File {
    private String constultationType;
    private ArrayList<Medicament> medicaments;
    private String depositionDate;
    private String consultationDate;
    private float montant;
    private HashMap<String,Float> attachments;

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
