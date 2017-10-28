package org.empit.bibliavetelkedo.dal.repo.jpa;

import org.eclipse.persistence.exceptions.JPQLException;
import org.empit.bibliavetelkedo.dal.entity.AnswerBE;
import org.empit.bibliavetelkedo.dal.repo.GenericRepo;

import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class AnswerRepo extends GenericRepo<AnswerBE> {
    private static AnswerRepo instance;

    public static AnswerRepo getInstance() {
        if (instance == null) {
            instance = new AnswerRepo();
        }
        return instance;
    }

    private AnswerRepo() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = factory.createEntityManager();
    }


    @Override
    public AnswerBE getById(Long id) {
        try {
            em.clear();
            Query query = em.createQuery("SELECT e FROM AnswerBE e where e.id = :id");
            query.setParameter("id", id);
            List<AnswerBE> result = (List<AnswerBE>) query.getResultList();
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

    @Override
    public List<AnswerBE> getAll() {

        try {
            Query query = em.createQuery("SELECT e FROM AnswerBE e");
            return (List<AnswerBE>) query.getResultList();
        } catch (JPQLException e) {
            //throw new RepositoryException();
        } catch (IllegalArgumentException e) {
            //throw new RepositoryException();
        }
        return null;
    }

    public AnswerBE getWhereNotAnswer(Long gameId) {
        try {
            em.clear();
            Query query = em.createQuery("SELECT e FROM AnswerBE e where e.game.id = :id and e.answer like :noans");
            query.setParameter("id", gameId);
            query.setParameter("noans", "NO_ANSWER");

            List<AnswerBE> result = (List<AnswerBE>) query.getResultList();
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
