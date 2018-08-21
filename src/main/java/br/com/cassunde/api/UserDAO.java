package br.com.cassunde.api;

import java.util.List;

import br.com.cassunde.model.User;

public interface UserDAO {
    User create(User user);

    User get(String key);

    User update(User user);

    List<User> list();

    long count();

    void delete(String key);
}
