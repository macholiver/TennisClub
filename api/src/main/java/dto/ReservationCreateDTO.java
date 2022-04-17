package dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * DTO representation of Reservation entity that
 * is about to be created by user.
 *
 * @author Oliver Mach
 */
@Data
public class ReservationCreateDTO {

    /**
     * Beginning of the reservation
     */
    @NotNull
    private String start;

    /**
     * End of the reservation
     */
    @NotNull
    private String end;

    /**
     * Type of the game, single or double
     */
    @NotBlank
    private String gameType;

    /**
     * Id of court associated with representation
     */
    @NotNull
    private Long courtId;

    /**
     * Name of the customer that created reservation
     */
    @NotNull
    private String name;

    /**
     * Phone number of customer that created reservation
     */
    @NotNull
    private String phone;
}
