package rest.controllers;

import dto.CourtDTO;
import rest.ApiUris;
import rest.exceptions.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.facade.CourtFacadeImpl;

import javax.inject.Inject;
import java.util.List;

/**
 * Rest controller for Court entity
 *
 * @author Oliver Mach
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_COURTS)
public class CourtController {

    final static Logger logger = LoggerFactory.getLogger(CourtController.class);

    @Inject
    private CourtFacadeImpl courtFacade;

    /**
     * Finds all existing Court entities
     *
     * @return list of Court DTOs
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<CourtDTO> findAll() {
        logger.debug("rest court findAll()");
        return courtFacade.findAll();
    }

    /**
     * Finds Court entity with given id
     *
     * @param id of the Court to be retrieved
     * @throws ResourceNotFoundException if Court is not present
     * @return Court DTO
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final CourtDTO findById(@PathVariable("id") Long id) {
        logger.debug("rest court findById({})", id);

        CourtDTO courtDto = courtFacade.findById(id);
        if (courtDto == null)
            throw new ResourceNotFoundException();
        return courtDto;
    }
}
