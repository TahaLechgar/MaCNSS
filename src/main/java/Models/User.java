package Models;

import Enums.UserType;

public class User {

    private String username;
    private String fullname;
    private String birthdate;
    private UserType type;

    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public UserType getType() {
        return type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String username, String fullname, String birthdate, UserType type, String email) {
        this.username = username;
        this.fullname = fullname;
        this.birthdate = birthdate;
        this.type = type;
        this.email = email;
    }

    @Override
    public String toString() {
        return "fullname : " + fullname
        + "\nusername : " + username
        + "\nbirthdate : " + birthdate
        + "\ntype : " + type
        + "\nemail : " + email;
    }
}
