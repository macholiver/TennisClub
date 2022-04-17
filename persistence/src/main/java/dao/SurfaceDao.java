package dao;

import entity.Surface;

import java.util.List;

/**
 * Interface for Dao representation of Surface entity
 *
 * @author Oliver Mach
 */
public interface SurfaceDao {

    /**
     * Finds Surface entity by provided Id
     * @param id of Surface entity to be found
     * @return Surface entity
     */
    Surface findById(Long id);

    /**
     * Finds list of all existing Surface entities
     * @return list of Surface entities
     */
    List<Surface> findAll();

    /**
     * Creates provided entity Surface
     * @param surface Entity to be created
     */
    void create(Surface surface);
}
