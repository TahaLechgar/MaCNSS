package DAO;

import Models.File;

import java.util.List;
import java.util.Optional;

public class FileDao implements Dao<File>{
    @Override
    public Optional<File> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<File> getAll() {
        return null;
    }

    @Override
    public void save(File file) {

    }

    @Override
    public void update(File file, String[] params) {

    }

    @Override
    public void delete(File file) {

    }
}
