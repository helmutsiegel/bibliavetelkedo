package org.empit.bibliavetelkedo.dal.repo.jpa;

import org.eclipse.persistence.exceptions.JPQLException;
import org.empit.bibliavetelkedo.dal.entity.QuestionBE;
import org.empit.bibliavetelkedo.dal.entity.UserBE;
import org.empit.bibliavetelkedo.dal.repo.GenericRepo;

import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class QuestionRepo extends GenericRepo<QuestionBE> {


    private static QuestionRepo instance;

    public static QuestionRepo getInstance() {
        if (instance == null) {
            instance = new QuestionRepo();
        }
        return instance;
    }

    private QuestionRepo() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = factory.createEntityManager();
    }


    public QuestionBE getById(Long id) {
        return null;
    }

    public List<QuestionBE> getAll() {
        try {
            Query query = em.createQuery("SELECT e FROM QuestionBE e");
            return (List<QuestionBE>) query.getResultList();

        } catch (JPQLException e) {
            //throw new RepositoryException();
        } catch (IllegalArgumentException e) {
            //throw new RepositoryException();
        }
        return null;
    }
}
