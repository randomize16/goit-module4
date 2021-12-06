package ua.goit.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goit.config.PersistenceProvider;
import ua.goit.model.Category;

import javax.persistence.EntityManager;

public class CategoryDao extends AbstractDao<Category> {

    private static final Logger LOGGER = LogManager.getLogger(CategoryDao.class);
    private EntityManager em = PersistenceProvider.getEntityManager();

    private static CategoryDao instance;
    private CategoryDao() {
        super(Category.class);
    }

    public static CategoryDao getInstance() {
        if (instance == null) {
            instance  = new CategoryDao();
        }
        return instance;
    }

}
