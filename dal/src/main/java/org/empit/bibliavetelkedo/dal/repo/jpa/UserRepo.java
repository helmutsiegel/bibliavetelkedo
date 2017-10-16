package org.empit.bibliavetelkedo.dal.repo.jpa;


import org.eclipse.persistence.exceptions.JPQLException;
import org.empit.bibliavetelkedo.dal.entity.UserBE;
import org.empit.bibliavetelkedo.dal.repo.GenericRepo;

import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by helmut on 03.04.2017.
 */
public class UserRepo extends GenericRepo<UserBE> {

    private static UserRepo instance;

    public static UserRepo getInstance() {
        if (instance == null) {
            instance = new UserRepo();
        }
        return instance;
    }

    private UserRepo() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = factory.createEntityManager();
    }

    public UserBE getById(Long id) {
        return null;
    }

    public List<UserBE> getAll() {
        try {
            Query query = em.createQuery("SELECT e FROM UserBE e");
            return (List<UserBE>) query.getResultList();

        } catch (JPQLException e) {
            //throw new RepositoryException();
        } catch (IllegalArgumentException e) {
            //throw new RepositoryException();
        }
        return null;
    }

    public boolean checkUsernameAndPassword(String username, String password) {
        try {
            Query query = em.createQuery("SELECT COUNT(e) FROM UsebBE e where e.username like :uname and e.password like :pwd");
            query.setParameter("uname", username);
            query.setParameter("pwd", password);
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

    public boolean checkUsername(String username) {
        try {
            Query query = em.createQuery("SELECT COUNT(e) FROM UserBE e where e.username like :uname");
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

    public UserBE getByUsername(String username) {
        try {
            Query query = em.createQuery("SELECT e FROM UserBe e where e.username like :username");
            query.setParameter("username", username);
            List<UserBE> result = (List<UserBE>) query.getResultList();
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
