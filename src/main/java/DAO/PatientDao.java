package DAO;

import Models.Patient;

import java.util.List;
import java.util.Optional;

public class PatientDao implements Dao<Patient>{
    @Override
    public Optional<Patient> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Patient> getAll() {
        return null;
    }

    @Override
    public void save(Patient patient) {

    }

    @Override
    public void update(Patient patient, String[] params) {

    }

    @Override
    public void delete(Patient patient) {

    }
}
