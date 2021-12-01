package ua.goit.dao;

import ua.goit.config.PersistenceProvider;

import javax.persistence.EntityManager;

public abstract class AbstractDao<T> implements Dao<T> {

    protected EntityManager em = PersistenceProvider.getEntityManager();

    @Override
    public void create(T entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    @Override
    public void update(T entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    @Override
    public void delete(T entity) {
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }
}
