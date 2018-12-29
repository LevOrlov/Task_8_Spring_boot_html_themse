package dao;


import model.User;

import java.util.List;

public interface UserDao {
    void addUser(User application);

    void deleteUser(int userId);

    void updateUser(User application);

    List<User> getAllUsers();

    User getUserById(int userId);

    User getUserByLogin(String login);
}
