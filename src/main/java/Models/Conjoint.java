package Models;

import Enums.Relationship;

public class Conjoint {
    private long id;
    private String relationship;
    private String fullName;
    private String birthDate;

    private long patientImm;

    public long getId() {
        return id;
    }

    public long getPatientImm() {
        return patientImm;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Conjoint(long id, String relationship, String fullName, String birthDate, long patientImm) {
        this.id = id;
        this.relationship = relationship;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.patientImm = patientImm;
    }

    @Override
    public String toString() {
        return " fullname : " + fullName + " relationship : " + relationship;
    }
}
