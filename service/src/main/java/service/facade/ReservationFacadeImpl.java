package service.facade;

import dto.ReservationCreateDTO;
import dto.ReservationDTO;
import entity.Court;
import entity.Customer;
import entity.Reservation;
import enums.GameType;

import service.BeanMappingService;
import service.CourtService;
import service.CustomerService;
import service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation for facade of Customer entity.
 * Serves for converting Entities to DTOs
 *
 * @author Oliver Mach
 */
@Service
@Transactional
public class ReservationFacadeImpl {
    @Inject
    private ReservationService reservationService;

    @Inject
    private CourtService courtService;

    @Inject
    private CustomerService customerService;

    @Autowired
    private BeanMappingService mapper;

    /**
     * Creates Reservation entity from createDTO.
     * If there is no customer with provided phone,
     * the customer is created
     *
     * @param createDto Reservation DTO retrieved
     *                  from client.
     * @return Cost of created reservation
     */
    public BigDecimal create(ReservationCreateDTO createDto) {
        if (createDto == null)
            throw new IllegalArgumentException("CreateDto is null");
        Reservation reservation = new Reservation();
        Court court = courtService.findById(createDto.getCourtId());
        Customer customer = customerService.findByPhone(createDto.getPhone());
        if (customer == null) {
            customer = new Customer();
            customer.setName(createDto.getName());
            customer.setPhone(createDto.getPhone());
            customerService.create(customer);
        }
        reservation.addCustomer(customer);
        reservation.addCourt(court);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        reservation.setReserveStart(LocalDateTime.parse(createDto.getStart(), formatter));
        reservation.setReserveEnd(LocalDateTime.parse(createDto.getEnd(), formatter));
        GameType gameType = GameType.SINGLE;
        if (createDto.getGameType().equalsIgnoreCase("double"))
            gameType = GameType.DOUBLE;
        reservation.setGameType(gameType);
        reservation.setReserveCost(reservation.computeCost());
        reservationService.create(reservation);
        return reservation.getReserveCost();
    }

    /**
     * Finds all Reservation entities and converts them to DTOs
     *
     * @return list of Reservation DTOs
     */
    public List<ReservationDTO> findAll() {
        List<Reservation> all = reservationService.findAll();
        return editList(all);
    }

    /**
     * Finds Reservation entity with given Id and converts it to DTO
     *
     * @param id of Reservation entity to be found
     * @return Reservation DTO
     */
    public ReservationDTO findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id is null");
        }
        Reservation entity = reservationService.findById(id);
        ReservationDTO r = mapper.mapTo(entity, ReservationDTO.class);
        edit(r, entity);
        return r;
    }

    /**
     * Finds all Reservation entities with provided phone and
     * converts them to DTOs
     * @param phone of Customer that created reservations
     * @return list of Reservation DTOs
     */
    @Transactional
    public List<ReservationDTO> findByPhone(String phone) {
        if (phone == null)
            throw new IllegalArgumentException("Phone is null");
        if (phone.isEmpty())
            throw new IllegalArgumentException("Phone is blank");
        List<Reservation> all = reservationService.findByPhone(phone);
        return editList(all);
    }

    /**
     * Finds Reservation entities with given courtId and
     * converts them to DTOs
     * @param courtId of Court that is associated to reservation
     * @return list Reservation DTOs
     */
    @Transactional
    public List<ReservationDTO> findByCourtId(Long courtId) {
        if (courtId == null)
            throw new IllegalArgumentException("CourtId is null");
        List<Reservation> all = reservationService.findByCourtId(courtId);
        return editList(all);
    }

    /**
     * Converts start and end time to String, sets cost of reservation and
     * currency manually.
     * @param reservationDTO to be edited
     * @param reservation after editing
     */
    public void edit(ReservationDTO reservationDTO, Reservation reservation) {
        reservationDTO.setStart(reservation.getReserveStart().toString().replace("T", " "));
        reservationDTO.setEnd(reservation.getReserveEnd().toString().replace("T", " "));
        reservationDTO.setCost(reservation.getReserveCost());
        reservationDTO.setCurrency(reservation.getCourt().getSurface().getCurrency().toString());
    }

    /**
     * Performs the editing on list of Reservation and returns Reservation DTOs
     * @param reservationList that is passed for editing
     * @return list Reservation entities to be edited and converted to DTOs
     */
    public List<ReservationDTO> editList(List<Reservation> reservationList) {
        List<ReservationDTO> allDto = new ArrayList<>();
        for (Reservation r : reservationList) {
            ReservationDTO rDto = mapper.mapTo(r, ReservationDTO.class);
            edit(rDto, r);
            allDto.add(rDto);
        }
        return allDto;
    }
}
