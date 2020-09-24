package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import web.models.Role;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoEntityImpl implements UserDao{
    @PersistenceContext
    EntityManager entityManager;

    private List<User> users;

    private RoleDao roleDao;

//    @Override
//    public void addUser(User user) {
//        entityManager.persist(user);
//    }
@Override
public void addUser(User user) {
    Set <Role> roles = new HashSet<>();
    roles.add(roleDao.getOne(1L));
    user.setRoles(roles);
    entityManager.persist(user);
}

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void removeUserById(int id) {
        User us = entityManager.find(User.class, id);
        entityManager.remove(us);
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }


    @Transactional
    @Override
    public User getUserByEmail(String email) {
        return (User) entityManager.createQuery("Select u from User u where u.email = : email").setParameter("email", email).getSingleResult();
    }

    @Override
    public List<User> listUsers() {
        users = entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
        return users;
    }
}








//    private static int STATIC_COUNT;
//    private List<User> users;
//    {
//        users = new ArrayList<>();
//        users.add(new User(++STATIC_COUNT, "Ivan", "abc@mail.ru"));
//        users.add(new User(++STATIC_COUNT, "Fedya", "deg@mail.ru"));
//        users.add(new User(++STATIC_COUNT, "Vasya", "ign@mail.ru"));
//    }
//
//    public List<User> index(){
//        return users;
//    }
//    public User show(int id){
//        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
//    }
//
//    public void save(User user){
//        user.setId(++STATIC_COUNT);
//        users.add(user);
//    }

