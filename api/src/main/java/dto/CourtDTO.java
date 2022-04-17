package dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * DTO representation of Court entity
 *
 * @author Oliver Mach
 */
@Getter
@Setter
public class CourtDTO {
    private Long id;

    /**
     * Name of the court
     */
    private String name;

    /**
     * Surface DTO associated with given court
     */
    private SurfaceDTO surface;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourtDTO)) return false;
        CourtDTO courtDto = (CourtDTO) o;
        return getName().equals(courtDto.getName())
                && getSurface().equals(courtDto.getSurface());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSurface());
    }
}
