package service.facade;

import dto.SurfaceDTO;
import entity.Surface;
import service.BeanMappingService;
import service.SurfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation for facade of Surface entity.
 * Serves for converting Entities to DTOs
 *
 * @author Oliver Mach
 */
@Service
@Transactional
public class SurfaceFacadeImpl {
    @Inject
    private SurfaceService surfaceService;

    @Autowired
    private BeanMappingService mapper;

    /**
     * Finds all Surface entities and converts them to DTOs
     *
     * @return list of Surface DTOs
     */
    public List<SurfaceDTO> findAll() {
        List<Surface> all = surfaceService.findAll();
        return mapper.mapTo(all, SurfaceDTO.class);
    }

    /**
     * Finds Surface entity with given Id and converts it to DTO
     *
     * @param id of Surface entity to be found
     * @return Surface DTO
     */
    public SurfaceDTO findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id is null");
        }
        Surface entity = surfaceService.findById(id);
        return mapper.mapTo(entity, SurfaceDTO.class);
    }
}