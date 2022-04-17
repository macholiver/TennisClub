package dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * DTO representation of Surface service
 *
 * @author Oliver Mach
 */
@Getter
@Setter
public class SurfaceDTO {
    private Long id;

    /**
     * Cost per minute for the reservation of court surface
     */
    private int costPerMinute;

    /**
     * Currency of the cost
     */
    private String currency;

    /**
     * Type of surface, grass, clay, hard or artificial
     */
    private String surfaceType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SurfaceDTO)) return false;
        SurfaceDTO that = (SurfaceDTO) o;
        return getCostPerMinute() == that.getCostPerMinute()
                && getSurfaceType().equals(that.getSurfaceType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCostPerMinute(), getSurfaceType());
    }
}
