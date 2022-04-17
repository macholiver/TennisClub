package sampledata;

import entity.Court;
import entity.Customer;
import entity.Reservation;
import entity.Surface;
import enums.Currency;
import enums.GameType;
import enums.SurfaceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import service.CourtService;
import service.CustomerService;
import service.ReservationService;
import service.SurfaceService;

import java.io.IOException;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Loads sample data to populate the database.
 *
 * @author Oliver Mach
 */
@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

    @Autowired
    private CourtService courtService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SurfaceService surfaceService;

    @Autowired
    private ReservationService reservationService;

    @Override
    public void loadData() {

        log.info("Loading surfaces");
        Surface surfaceGrass = surface(SurfaceType.GRASS, 5, Currency.CZK);
        Surface surfaceClay = surface(SurfaceType.CLAY, 6, Currency.CZK);
        Surface surfaceHard = surface(SurfaceType.HARD, 7, Currency.EUR);
        Surface surfaceArtificial = surface(SurfaceType.ARTIFICIAL, 8, Currency.EUR);

        log.info("Loading courts");
        Court courtGrass = court("GrassCourt", surfaceGrass);
        Court courtClay = court("ClayCourt", surfaceClay);
        Court courtHard = court("HardCourt", surfaceHard);
        Court courtArtificial = court("ArtificialCourt", surfaceArtificial);

        log.info("Loading customers");
        Customer nadal = customer("+421987654321", "Nadal");
        Customer federer = customer("+421123456789", "Federer");
        Customer murray = customer("+421159357852", "Murray");

        log.info("Loading reservations");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        reservation(nadal, courtGrass, GameType.SINGLE,
                LocalDateTime.parse(LocalDateTime.now().format(formatter), formatter),
                LocalDateTime.parse(LocalDateTime.now().plusMinutes(90L).format(formatter), formatter));
        reservation(nadal, courtClay, GameType.DOUBLE,
                LocalDateTime.parse(LocalDateTime.now().plusHours(24L).format(formatter), formatter),
                        LocalDateTime.parse(LocalDateTime.now().plusHours(24L).plusMinutes(90L).format(formatter), formatter));
        reservation(federer, courtHard, GameType.SINGLE,
                LocalDateTime.parse(LocalDateTime.now().plusHours(2L).format(formatter), formatter),
                LocalDateTime.parse(LocalDateTime.now().plusHours(2L).plusMinutes(120L).format(formatter), formatter));
        reservation(federer, courtArtificial, GameType.SINGLE,
                LocalDateTime.parse(LocalDateTime.now().plusHours(39L).format(formatter), formatter),
                LocalDateTime.parse(LocalDateTime.now().plusHours(39L).plusMinutes(100L).format(formatter), formatter));
        reservation(murray, courtClay, GameType.DOUBLE,
                LocalDateTime.parse(LocalDateTime.now().plusHours(1L).format(formatter), formatter),
                LocalDateTime.parse(LocalDateTime.now().plusHours(1L).plusMinutes(180L).format(formatter), formatter));
    }

    private Surface surface(SurfaceType surfaceType, int cost, Currency currency) {
        Surface surface = new Surface();
        surface.setSurfaceType(surfaceType);
        surface.setCostPerMinute(cost);
        surface.setCurrency(currency);
        surfaceService.create(surface);
        return surface;
    }

    private Court court(String name, Surface surface) {
        Court court = new Court();
        court.setName(name);
        court.addSurface(surface);
        courtService.create(court);
        return court;
    }

    private Customer customer(String phone, String name) {
        Customer customer = new Customer();
        customer.setPhone(phone);
        customer.setName(name);
        customerService.create(customer);
        return customer;
    }

    private Reservation reservation(Customer customer, Court court, GameType gameType,
                                    LocalDateTime start, LocalDateTime end) {
        Reservation reservation = new Reservation();
        reservation.addCustomer(customer);
        reservation.addCourt(court);
        reservation.setGameType(gameType);
        reservation.setReserveStart(start);
        reservation.setReserveEnd(end);
        reservation.setReserveCost(reservation.computeCost());
        reservationService.create(reservation);
        return reservation;
    }
}

