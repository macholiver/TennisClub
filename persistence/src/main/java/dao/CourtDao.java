package dao;

import entity.Court;

import java.util.List;


/**
 * Interface for Dao representation of Court entity
 *
 * @author Oliver Mach
 */
public interface CourtDao {

    /**
     * Finds Court entity by provided Id
     * @param id of Court entity to be found
     * @return Court entity
     */
    Court findById(Long id);

    /**
     * Creates provided entity Court
     * @param court Entity to be created
     */
    void create(Court court);

    /**
     * Finds all existing Court entities
     * @return list of Court entities
     */
    List<Court> findAll();
}
