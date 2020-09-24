package web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import web.models.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    void addUser(User user);
    void updateUser(User user);
    void removeUserById(int id);
    User getUserById(int id);
    List<User> listUsers();
}
