package entity;

import enums.GameType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entity representing reservation
 *
 * @author Oliver Mach
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    /**
     * Start of the reservation
     */
    @NotNull
    private LocalDateTime reserveStart;

    /**
     * End of the reservation
     */
    @NotNull
    private LocalDateTime reserveEnd;

    /**
     * Type of the game that will be played
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    private GameType gameType;

    @NotNull
    @ManyToOne
    private Customer customer;

    @NotNull
    @ManyToOne
    private Court court;

    /**
     * Cost of the reservation
     */
    @NotNull
    private BigDecimal reserveCost;

    /**
     * Computes the cost from the duration of the reservation,
     * price per minute for given surface and coefficient of game type
     * @return final cost of the reservation
     */
    public BigDecimal computeCost() {
        BigDecimal cost = BigDecimal.valueOf(obtainMinutes(getReserveEnd()) - obtainMinutes(getReserveStart()));
        BigDecimal fullCost = cost.multiply(BigDecimal.valueOf(getCourt().getSurface().getCostPerMinute()));
        return gameType.equals(GameType.SINGLE) ? fullCost : fullCost.multiply(new BigDecimal("1.5"));
    }

    /**
     * Retrieves time in minutes
     * @param time to be converted into minutes
     * @return amount minutes
     */
    public int obtainMinutes(LocalDateTime time) {
        return  time.toLocalTime().getHour() * 60 + time.toLocalTime().getMinute();
    }

    public void addCustomer(Customer c) {
        setCustomer(c);
        c.addReservation(this);
    }

    public void addCourt(Court c) {
        setCourt(c);
        c.addReservation(this);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation that = (Reservation) o;
        return getReserveStart().equals(that.getReserveStart())
                && getReserveEnd().equals(that.getReserveEnd())
                && getGameType() == that.getGameType()
                && getCustomer().equals(that.getCustomer())
                && getCourt().equals(that.getCourt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReserveStart(), getReserveEnd(), getGameType(), getCustomer(), getCourt());
    }
}
