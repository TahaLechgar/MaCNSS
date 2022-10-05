package Models;

public class Prescription {
    private final long medicationId;
    private final long dossierId;
    private final String consultationDate;
    private final int quantity;

    public Prescription(long medicationId, long dossierId, String consultationDate, int quantity) {
        this.medicationId = medicationId;
        this.dossierId = dossierId;
        this.consultationDate = consultationDate;
        this.quantity = quantity;
    }

    public long getMedicationId() {
        return medicationId;
    }

    public long getDossierId() {
        return dossierId;
    }

    public String getConsultationDate() {
        return consultationDate;
    }

    public int getQuantity() {
        return quantity;
    }
}
