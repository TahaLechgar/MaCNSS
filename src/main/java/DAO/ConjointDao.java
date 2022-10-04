package DAO;

import Models.Conjoint;

import java.util.List;
import java.util.Optional;

public class ConjointDao implements Dao<Conjoint>{
    @Override
    public Optional<Conjoint> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Conjoint> getAll() {
        return null;
    }

    @Override
    public void save(Conjoint conjoint) {

    }

    @Override
    public void update(Conjoint conjoint, String[] params) {

    }

    @Override
    public void delete(Conjoint conjoint) {

    }
}
