package ua.goit.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goit.dao.UserDao;
import ua.goit.model.User;

import java.util.List;
import java.util.Optional;

public class UserService {

    private static UserService instance;

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);
    private static final UserDao userDao = UserDao.getInstance();

    private UserService() {
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }


    public List<User> getAll() {
        return userDao.getAll();
    }

    public Optional<User> get(long id) {
        return userDao.get(id);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void create(User user) {
        userDao.create(user);
    }

    public void delete(User user) {
        userDao.delete(user);
    }
}
