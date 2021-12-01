package ua.goit.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceProvider {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("module7");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
