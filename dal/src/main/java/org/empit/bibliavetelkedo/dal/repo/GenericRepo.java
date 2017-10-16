package org.empit.bibliavetelkedo.dal.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Created by helmut on 20.03.2017.
 */
public abstract class GenericRepo<T> {
    protected static final String PERSISTENCE_UNIT_NAME = "jpaeclipselink";
    protected EntityManagerFactory factory;
    protected EntityManager em;

    static final Logger LOG = LoggerFactory.getLogger(GenericRepo.class);

    public abstract T getById(Long id);

    public abstract List<T> getAll();


    public boolean delete(T entity) {
        em.getTransaction().begin();
        em.remove(em.contains(entity) ? entity : em.merge(entity));
        em.getTransaction().commit();
        return true;
    }

    public T insert(T entity) {
        LOG.info("Inserting the entity: " + entity);
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.flush();
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            LOG.error(e.getMessage());
        } finally {
            if (em.getTransaction().isActive()) {
                LOG.error("Transaction for inserting the entity: " + entity + "was rolled back!");
                em.getTransaction().rollback();
            }
        }
        return entity;
    }

    //save method without transaction
    private boolean pSave(T entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
        return true;
    }

    public boolean save(T entity) {
        pSave(entity);
        return true;
    }

    public boolean save(List<T> entityList) {
        entityList.forEach(be -> {
            pSave(be);
        });
        return true;
    }
}
