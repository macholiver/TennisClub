package service.facade;

import dto.CourtDTO;
import entity.Court;
import service.BeanMappingService;
import service.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation for facade of Court entity.
 * Serves for converting Entities to DTOs
 *
 * @author Oliver Mach
 */
@Service
@Transactional
public class CourtFacadeImpl {
    @Inject
    private CourtService courtService;

    @Autowired
    private BeanMappingService mapper;

    /**
     * Finds all Court entities and converts them to DTOs
     *
     * @return list of Court DTOs
     */
    public List<CourtDTO> findAll() {
        List<Court> all = courtService.findAll();
        return mapper.mapTo(all, CourtDTO.class);
    }

    /**
     * Finds Court entity with given Id and converts it to DTO
     *
     * @param id of Court entity to be found
     * @return Court DTO
     */
    public CourtDTO findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id is null");
        }
        Court entity = courtService.findById(id);
        return mapper.mapTo(entity, CourtDTO.class);
    }
}
