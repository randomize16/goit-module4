package ua.goit.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goit.config.PersistenceProvider;
import ua.goit.model.Group;
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

    @Override
    public void delete(User entity) {
        entity = em.merge(entity);
        em.getTransaction().begin();
        for (Group group : entity.getGroups()) {
            group.getUsers().remove(entity);
        }
        em.remove(entity);
        em.getTransaction().commit();
    }

    public User createAndReturn(User entity) {
        em.getTransaction().begin();
        User user = em.merge(entity);
        em.getTransaction().commit();
        return user;
    }

    public void addUserToGroup(User user, List<Long> groupIds) {
        em.getTransaction().begin();
        groupIds.forEach(aLong -> {
            Group group = em.find(Group.class, aLong);
            if (group != null) {
                group.getUsers().add(user);
                em.merge(group);
            }
        });
        em.getTransaction().commit();
    }

    public User getByName(String userName) {
        TypedQuery<User> userByName = em.createNamedQuery("userByName", User.class);
        userByName.setParameter("name", userName);
        return userByName.getSingleResult();
    }
}
