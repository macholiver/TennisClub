package dao;

import entity.Reservation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implementation of ReservationDao interface
 *
 * @author Oliver Mach
 */
@Repository
public class ReservationDaoImpl implements ReservationDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Reservation findById(Long id) {
        return em.find(Reservation.class, id);
    }

    @Override
    public List<Reservation> findAll() {
        return em.createQuery("select c from Reservation c", Reservation.class)
                .getResultList();
    }

    @Override
    public void create(Reservation reservation) {
        em.persist(reservation);
    }


    @Override
    public List<Reservation> findByPhone(String phone) {
        if (phone == null)
            throw new IllegalArgumentException("Phone is null");
        return em.createQuery("select r from Reservation r where r.customer.phone = :phone", Reservation.class)
                .setParameter("phone", phone)
                .getResultList();
    }

    @Override
    public List<Reservation> findByCourtId(Long courtId) {
        if (courtId == null)
            throw new IllegalArgumentException("CourtId is null");
        return em.createQuery("select r from Reservation r where r.court.id = :courtId", Reservation.class)
                .setParameter("courtId", courtId)
                .getResultList();
    }
}
