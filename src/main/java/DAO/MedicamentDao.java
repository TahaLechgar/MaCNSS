package DAO;

import Models.Medicament;

import java.util.ArrayList;
import java.util.Optional;

public class MedicamentDao implements Dao<Medicament>{

    @Override
    public Optional<Medicament> get(long id) {
        return Optional.empty();
    }

    @Override
    public ArrayList<Medicament> getAll() {
        return null;
    }

    @Override
    public void save(Medicament medicament) {

    }

    @Override
    public void update(Medicament medicament, String[] params) {

    }

    @Override
    public void delete(Medicament medicament) {

    }
}
