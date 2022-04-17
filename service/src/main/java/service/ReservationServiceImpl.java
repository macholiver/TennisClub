package service;

import dao.ReservationDao;
import entity.Reservation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

/**
 * Implementation of Reservation service interface
 *
 * @author Oliver Mach
 */
@Service
public class ReservationServiceImpl implements ReservationService {
    @Inject
    private ReservationDao repository;

    public BigDecimal create(Reservation entity) {
        if (entity == null)
            throw new IllegalArgumentException("Given entity is null");
        repository.create(entity);
        return entity.getReserveCost();
    }

    public List<Reservation> findAll() {
        return repository.findAll();
    }

    public Reservation findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Reservation> findByPhone(String phone) {
        if (phone == null)
            throw new IllegalArgumentException("Phone is null");
        if (phone.isEmpty())
            throw new IllegalArgumentException("Phone is blank");
        return repository.findByPhone(phone);
    }

    @Override
    @Transactional
    public List<Reservation> findByCourtId(Long courtId) {
        if (courtId == null)
            throw new IllegalArgumentException("CourtId is null");
        return repository.findByCourtId(courtId);
    }
}
