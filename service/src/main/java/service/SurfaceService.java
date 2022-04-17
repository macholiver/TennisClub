package service;

import entity.Surface;
import java.util.List;

/**
 * Interface for Surface service
 *
 * @author Oliver Mach
 */
public interface SurfaceService {

    /**
     * Creates provided Surface entity
     * @param surface entity to be created
     */
    void create(Surface surface);

    /**
     * Finds Surface entity with provided Id
     * @param id of Surface entity to be found
     * @return Surface entity
     */
    Surface findById(Long id);

    /**
     * Finds all existing Surface entities
     * @return list of Surface entities
     */
    List<Surface> findAll();
}
