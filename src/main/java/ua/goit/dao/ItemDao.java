package ua.goit.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goit.model.Item;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class ItemDao extends AbstractDao<Item>{

    private static final Logger LOGGER = LogManager.getLogger(Item.class);

    private static ItemDao instance;
    private ItemDao() {
    }

    public static ItemDao getInstance() {
        if (instance == null) {
            instance  = new ItemDao();
        }
        return instance;
    }

    @Override
    public List<Item> getAll() {
        TypedQuery<Item> query = em.createQuery("from Item", Item.class);
        return query.getResultList();
    }

    @Override
    public Optional<Item> get(Long id) {
        Item entity = em.find(Item.class, id);
        return Optional.of(entity);
    }

}
