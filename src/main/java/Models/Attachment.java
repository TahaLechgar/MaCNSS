package Models;


public class Attachment {
    private long dossierID;
    private float price;
    private String type;

    public Attachment(long dossierID, float price, String type) {
        this.dossierID = dossierID;
        this.price = price;
        this.type = type;
    }


    public long getDossierID() {
        return dossierID;
    }

    public void setDossierID(long dossierID) {
        this.dossierID = dossierID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
