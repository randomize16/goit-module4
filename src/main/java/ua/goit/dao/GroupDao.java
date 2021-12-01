package ua.goit.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goit.config.PersistenceProvider;
import ua.goit.model.Group;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class GroupDao extends AbstractDao<Group> {

    private static final Logger LOGGER = LogManager.getLogger(GroupDao.class);
    private EntityManager em = PersistenceProvider.getEntityManager();

    private static GroupDao instance;
    private GroupDao() {
    }

    public static GroupDao getInstance() {
        if (instance == null) {
            instance  = new GroupDao();
        }
        return instance;
    }

    @Override
    public List<Group> getAll() {
        TypedQuery<Group> query = em.createQuery("from Group", Group.class);
        return query.getResultList();
    }

    @Override
    public Optional<Group> get(Long id) {
        Group group = em.find(Group.class, id);
        return Optional.of(group);
    }

}
