package DAO;

import Models.Prescription;
import DAO.Dao;

import java.util.List;
import java.util.Optional;

public class PrescriptionDao implements Dao<Prescription> {

    @Override
    public Optional<Prescription> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Prescription> getAll() {
        return null;
    }

    @Override
    public long save(Prescription prescription) {
        return 0;
    }

    @Override
    public void update(Prescription prescription, String[] params) {

    }

    @Override
    public void delete(Prescription prescription) {

    }
}
