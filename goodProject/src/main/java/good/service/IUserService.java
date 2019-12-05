package good.service;

import good.domain.User;

import java.util.List;


public interface IUserService {

    void add(User user);

    boolean findByName(String name);

    List<User> findAll(int page, int size);

    User findById(int id);

    void updateUser(User user);

    void deleteUser(int id);

    User findByUsername(String username);
}
