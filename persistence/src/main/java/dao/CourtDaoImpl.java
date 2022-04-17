package dao;

import entity.Court;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implementation of CourtDao interface
 *
 * @author Oliver Mach
 */
@Repository
public class CourtDaoImpl implements CourtDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Court findById(Long id) {
        return em.find(Court.class, id);
    }

    @Override
    public List<Court> findAll() {
        return em.createQuery("select c from Court c", Court.class)
                .getResultList();
    }

    @Override
    public void create(Court court) {
        em.persist(court);
    }
}
