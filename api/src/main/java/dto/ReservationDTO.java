package dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * DTO representation of Reservation entity
 *
 * @author Oliver Mach
 */
@Getter
@Setter
public class ReservationDTO {
    private Long id;

    /**
     * Beginning of the reservation
     */
    private String start;

    /**
     * End of the reservation
     */
    private String end;

    /**
     * Game type of the reservation, single or double
     */
    private String gameType;

    /**
     * DTO representation of customer that created reservation
     */
    private CustomerDTO customer;

    /**
     * DTO representation of court associated with reservation
     */
    private CourtDTO court;

    /**
     * Price of the reservation
     */
    private BigDecimal cost;

    /**
     * Price currency of reservation
     */
    private String currency;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReservationDTO)) return false;
        ReservationDTO that = (ReservationDTO) o;
        return getStart().equals(that.getStart())
                && getEnd().equals(that.getEnd())
                && getGameType().equals(that.getGameType())
                && getCustomer().equals(that.getCustomer())
                && getCourt().equals(that.getCourt())
                && getCost().equals(that.getCost())
                && getCurrency().equals(that.getCurrency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStart(), getEnd(), getGameType(), getCustomer(), getCourt(), getCost(), getCurrency());
    }
}
