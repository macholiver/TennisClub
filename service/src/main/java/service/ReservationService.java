package service;

import entity.Reservation;

import java.math.BigDecimal;
import java.util.List;

/**
 * Interface for Reservation service
 *
 * @author Oliver Mach
 */
public interface ReservationService {

    /**
     * Creates provided Reservation entity
     * @param reservation entity to be created
     * @return cost of created Reservation entity
     */
    BigDecimal create(Reservation reservation);

    /**
     * Finds Reservation entity with provided Id
     * @param id of Reservation entity to be found
     * @return Reservation entity
     */
    Reservation findById(Long id);

    /**
     * Finds all existing Reservation entities
     * @return list of Reservation entities
     */
    List<Reservation> findAll();

    /**
     * Finds Reservation entities created by Customer entity with provided phone number
     * @param phone of Customer that created reservation
     * @return list of Reservation entities
     */
    List<Reservation> findByPhone(String phone);

    /**
     * Finds Reservation entities created for Court entity with given Id
     * @param courtId of Court entity associated with Reservation entities to be found
     * @return list of Reservation entities
     */
    List<Reservation> findByCourtId(Long courtId);
}
