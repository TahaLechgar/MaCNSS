package DAO;

import Models.User;

import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<User>{

    @Override
    public Optional<User> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public long save(User user) {
        return 0;
    }

    @Override
    public void update(User user, String[] params) {

    }

    @Override
    public void delete(User user) {

    }
}
