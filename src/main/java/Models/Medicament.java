package Models;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Medicament {
    private String codeBare;
    private String medicamentName;
    private Float prixPublicMaroc;
    private Float prixRemboursement;

    public String getCodeBare() {
        return codeBare;
    }

    public void setCodeBare(String codeBare) {
        this.codeBare = codeBare;
    }

    public String getMedicamentName() {
        return medicamentName;
    }

    public void setMedicamentName(String medicamentName) {
        this.medicamentName = medicamentName;
    }

    public Float getPrixPublicMaroc() {
        return prixPublicMaroc;
    }

    public void setPrixPublicMaroc(Float prixPublicMaroc) {
        this.prixPublicMaroc = prixPublicMaroc;
    }

    public Float getPrixRemboursement() {
        return prixRemboursement;
    }

    public void setPrixRemboursement(Float prixRemboursement) {
        this.prixRemboursement = prixRemboursement;
    }
}