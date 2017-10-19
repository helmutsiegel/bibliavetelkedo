package org.empit.bibliavetelkedo.dal.repo.jpa;

import org.eclipse.persistence.exceptions.JPQLException;
import org.empit.bibliavetelkedo.dal.entity.QuestionBE;
import org.empit.bibliavetelkedo.dal.entity.UserBE;
import org.empit.bibliavetelkedo.dal.repo.GenericRepo;

import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class QuestionRepo extends GenericRepo<QuestionBE> {


    private static QuestionRepo instance;

    private Random randomGenerator;

    public static QuestionRepo getInstance() {
        if (instance == null) {
            instance = new QuestionRepo();
        }
        return instance;
    }

    private QuestionRepo() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = factory.createEntityManager();
        randomGenerator = new Random();
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

    public QuestionBE getNext(List<QuestionBE> questions){
        try {
            List<QuestionBE> resultList = null;
            if(questions.isEmpty()){
                resultList = this.getAll();
            }else {
                Query query = em.createQuery("SELECT e FROM QuestionBE e where e NOT IN :list");
                query.setParameter("list", questions);
                resultList = query.getResultList();
            }
                int index = randomGenerator.nextInt(resultList.size());
            return resultList.get(index);

        } catch (JPQLException e) {
            //throw new RepositoryException();
        } catch (IllegalArgumentException e) {
            //throw new RepositoryException();
        }
        return null;
    }
}
