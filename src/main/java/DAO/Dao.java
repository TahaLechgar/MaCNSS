package DAO;


import java.util.ArrayList;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> get(long id);

    ArrayList<T> getAll();

    long save(T t);

    void update(T t, String[] params);

    void delete(T t);

}
