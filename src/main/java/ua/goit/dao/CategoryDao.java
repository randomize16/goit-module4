package ua.goit.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goit.config.PersistenceProvider;
import ua.goit.model.Category;
import ua.goit.model.Group;
import ua.goit.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CategoryDao extends AbstractDao<Category> {

    private static final Logger LOGGER = LogManager.getLogger(CategoryDao.class);
    private EntityManager em = PersistenceProvider.getEntityManager();

    private static CategoryDao instance;
    private CategoryDao() {
    }

    public static CategoryDao getInstance() {
        if (instance == null) {
            instance  = new CategoryDao();
        }
        return instance;
    }

    @Override
    public List<Category> getAll() {
        TypedQuery<Category> query = em.createQuery("from Category", Category.class);
        return query.getResultList();
    }

    @Override
    public Optional<Category> get(Long id) {
        Category category = em.find(Category.class, id);
        return Optional.of(category);
    }

}
