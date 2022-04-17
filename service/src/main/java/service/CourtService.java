package service;

import entity.Court;

import java.util.List;

/**
 * Interface for Court service
 *
 * @author Oliver Mach
 */
public interface CourtService {

    /**
     * Creates provided entity Court
     * @param court entity to be created
     */
    void create(Court court);

    /**
     * Finds Court entity with provided Id
     * @param id of Court entity to be found
     * @return Court entity
     */
    Court findById(Long id);

    /**
     * Finds all existing Court entitites
     * @return list of Court entities
     */
    List<Court> findAll();
}
