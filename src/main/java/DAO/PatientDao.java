package DAO;

import Models.Patient;

import java.util.ArrayList;
import java.util.Optional;

public class PatientDao implements Dao<Patient>{
    @Override
    public Optional<Patient> get(long id) {
        return Optional.empty();
    }

    @Override
    public ArrayList<Patient> getAll() {
        return null;
    }

    @Override
    public long save(Patient patient) {
        return 0;
    }

    @Override
    public void update(Patient patient, String[] params) {

    }

    @Override
    public void delete(Patient patient) {

    }
}
