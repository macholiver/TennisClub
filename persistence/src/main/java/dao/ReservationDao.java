package dao;

import entity.Reservation;

import java.util.List;

/**
 * Interface for Dao representation of Reservation entity
 *
 * @author Oliver Mach
 */
public interface ReservationDao {

    /**
     * Finds Reservation entity by provided Id
     * @param id of Reservation entity to be found
     * @return Reservation entity
     */
    Reservation findById(Long id);

    /**
     * Creates provided entity Reservation
     * @param reservation Entity to be created
     */
    void create(Reservation reservation);

    /**
     * Finds all existing Reservation entities
     * @return list of Reservation entities
     */
    List<Reservation> findAll();

    /**
     * Finds all Reservation entities with given phone parameter
     * @param phone of Reservation entity to be found
     * @return list of Reservation entities
     */
    List<Reservation> findByPhone(String phone);

    /**
     * Finds all Reservation entities with given courtID
     * @param courtId of Court entity
     * @return list of Reservation entities
     */
    List<Reservation> findByCourtId(Long courtId);
}
