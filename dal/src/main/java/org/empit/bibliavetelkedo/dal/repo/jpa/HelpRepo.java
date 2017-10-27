package org.empit.bibliavetelkedo.dal.repo.jpa;

import org.eclipse.persistence.exceptions.JPQLException;
import org.empit.bibliavetelkedo.dal.entity.HelpBE;
import org.empit.bibliavetelkedo.dal.entity.HelpEnum;
import org.empit.bibliavetelkedo.dal.repo.GenericRepo;

import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class HelpRepo extends GenericRepo<HelpBE> {


    private static HelpRepo instance;

    public static HelpRepo getInstance() {
        if (instance == null) {
            instance = new HelpRepo();
        }
        return instance;
    }

    private HelpRepo() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = factory.createEntityManager();
    }


    @Override
    public HelpBE getById(Long id) {
        return null;
    }

    public HelpBE getByEnum(HelpEnum hEnum) {
        try {
            em.clear();
            Query query = em.createQuery("SELECT e FROM HelpBE e where e.help = :hEnum");
            query.setParameter("hEnum", hEnum);
            List<HelpBE> result = (List<HelpBE>) query.getResultList();
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
    public List<HelpBE> getAll() {
        return null;
    }
}
