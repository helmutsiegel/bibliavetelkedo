package org.empit.bibliavetelkedo.dal.repo.jpa;

import org.eclipse.persistence.config.CacheUsage;
import org.eclipse.persistence.config.QueryHints;
import org.eclipse.persistence.exceptions.JPQLException;
import org.empit.bibliavetelkedo.dal.entity.GameBE;
import org.empit.bibliavetelkedo.dal.repo.GenericRepo;

import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class GameRepo extends GenericRepo<GameBE> {
    private static GameRepo instance;

    public static GameRepo getInstance() {
        if (instance == null) {
            instance = new GameRepo();
        }
        return instance;
    }

    private GameRepo() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = factory.createEntityManager();
    }

    @Override
    public GameBE getById(Long id) {
        return null;
    }

    @Override
    public List<GameBE> getAll() {
        try {
            Query query = em.createQuery("SELECT e FROM GameBE e");
            return (List<GameBE>) query.getResultList();

        } catch (JPQLException e) {
            //throw new RepositoryException();
        } catch (IllegalArgumentException e) {
            //throw new RepositoryException();
        }
        return null;
    }

    public boolean checkHasGame(String username) {
        try {
            Query query = em.createQuery("SELECT COUNT(e) FROM GameBE e where e.userBE.username like :uname")
                    .setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache);
            query.setParameter("uname", username);
            Long count = (Long) query.getSingleResult();
            if (count == 1L) return true;
            return false;

        } catch (JPQLException e) {
            //throw new RepositoryException();
        } catch (IllegalArgumentException e) {
            //throw new RepositoryException();
        }
        return false;
    }

    public GameBE getByUsername(String username) {
        try {
            em.clear();
            Query query = em.createQuery("SELECT e FROM GameBE e where e.userBE.username like :username")
                    .setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache);
            query.setParameter("username", username);
            List<GameBE> result = (List<GameBE>) query.getResultList();
            if (result == null || result.size() == 0) {
                return null;
            }
            return result.get(0);
        } catch (JPQLException e) {
            //throw new RepositoryException();
        } catch (IllegalArgumentException e) {
            //throw new RepositoryException();
        }
        return null;
    }
}
