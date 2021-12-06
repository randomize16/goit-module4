package ua.goit.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goit.config.PersistenceProvider;
import ua.goit.model.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class UserDao extends AbstractDao<User> {

    private EntityManager em = PersistenceProvider.getEntityManager();

    private static final Logger LOGGER = LogManager.getLogger(UserDao.class);
    private static UserDao instance;


    private UserDao() {
        super(User.class);
    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    public User getByName(String userName) {
        TypedQuery<User> userByName = em.createNamedQuery("userByName", User.class);
        userByName.setParameter("name", userName);
        return userByName.getSingleResult();
    }
}
