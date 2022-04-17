package dao;

import entity.Surface;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implementation of SurfaceDao interface
 *
 * @author Oliver Mach
 */
@Repository
public class SurfaceDaoImpl implements SurfaceDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Surface findById(Long id) {
        return em.find(Surface.class, id);
    }

    @Override
    public List<Surface> findAll() {
        return em.createQuery("select s from Surface s", Surface.class)
                .getResultList();
    }

    @Override
    public void create(Surface surface) {
        em.persist(surface);
    }
}
