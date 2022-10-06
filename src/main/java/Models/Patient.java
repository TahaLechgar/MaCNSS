package Models;

import java.util.ArrayList;
import java.util.HashMap;

public class Patient {

    private String fullName;
    private String birthdate;
    private String email;
    private String cin;


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }


    public Patient(String fullName, String birthdate, String email, String cin) {
        this.fullName = fullName;
        this.birthdate = birthdate;
        this.email = email;
        this.cin = cin;
    }

    @Override
    public String toString() {
        return "FullName : " + fullName
                + "\nbirthdate : " + birthdate
                + "\nemail : " + email
                + "\ncin : " + cin;
    }
}
