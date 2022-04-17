package rest.controllers;

import dto.ReservationCreateDTO;
import dto.ReservationDTO;
import rest.ApiUris;
import rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import service.facade.ReservationFacadeImpl;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

/**
 * Rest controller for Reservation entity
 *
 * @author Oliver Mach
 */
@RestController
@RequestMapping(value = ApiUris.ROOT_URI_RESERVATIONS)
public class ReservationController {

    final static Logger logger = LoggerFactory.getLogger(ReservationController.class);

    @Inject
    private ReservationFacadeImpl reservationFacade;

    /**
     * Finds all existing Reservation entities
     * @return list of Reservation DTOs
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<ReservationDTO> findAll() {
        logger.debug("Rest reservation findAll()");
        return reservationFacade.findAll();
    }

    /**
     * Finds Reservation entity with given Id
     * @param id of Reservation entity to be found
     * @throws ResourceNotFoundException if Reservation entity is not present
     * @return Reservation DTO
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ReservationDTO findById(@PathVariable("id") long id) {
        logger.debug("Rest reservation findById({})", id);
        ReservationDTO reservationDto = reservationFacade.findById(id);
        if (reservationDto == null)
            throw new ResourceNotFoundException();
        return reservationDto;
    }

    /**
     * Finds all Reservation entities with given phone number
     * @param phone number Customer that created Reservation
     * @return list of Reservation DTOs
     */
    @RequestMapping(value = "/phone/{phone}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<ReservationDTO> findByPhone(@PathVariable("phone") String phone) {
        logger.debug("Rest reservation findByPhone({})", phone);
        return reservationFacade.findByPhone(phone);
    }

    /**
     * Finds all Reservation entities associated with Court with given courtId
     * @param courtId of Court associated with Reservation entities
     * @return list of Reservation DTOs
     */
    @RequestMapping(value = "/court/{courtId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<ReservationDTO> findByCourtId(@PathVariable("courtId") Long courtId) {
        logger.debug("Rest reservation findByCourtId({})", courtId);
        return reservationFacade.findByCourtId(courtId);
    }

    /**
     * Creates Reservation entity obtained from user
     * @param reservation DTO representation of Reservation to be created
     * @return final cost of created Reservation
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final BigDecimal create(@RequestBody ReservationCreateDTO reservation) {
        logger.debug("rest reservation create()");
        return reservationFacade.create(reservation);
    }
}
