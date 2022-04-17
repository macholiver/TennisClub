package service;

import dao.SurfaceDao;
import entity.Surface;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of Surface service interface
 *
 * @author Oliver Mach
 */
@Service
public class SurfaceServiceImpl implements SurfaceService {
    @Inject
    private SurfaceDao repository;

    public void create(Surface entity) {
        if (entity == null)
            throw new IllegalArgumentException("Given entity is null");
        repository.create(entity);
    }

    public List<Surface> findAll() {
        return repository.findAll();
    }

    public Surface findById(Long id) {
        return repository.findById(id);
    }
}
