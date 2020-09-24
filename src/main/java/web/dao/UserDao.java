package web.dao;

import web.models.User;
import java.util.List;

public interface UserDao {
    void addUser(User user);
    void updateUser(User user);
    void removeUserById(int id);
    User getUserById(int id);
    User getUserByEmail(String email);
    List<User> listUsers();
}
