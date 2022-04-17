package entity;

import enums.Currency;
import enums.SurfaceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Entity representing surface
 *
 * @author Oliver Mach
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Surface {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    /**
     * Cost of one minute of the game
     */
    @NotNull
    private int costPerMinute;

    /**
     * Currency of the cost
     */
    @Enumerated
    private Currency currency;

    @NotNull
    @Enumerated
    private SurfaceType surfaceType;

    @OneToMany
    private List<Court> courts = new ArrayList<>();

    public void addCourt(Court c) {
        courts.add(c);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Surface)) return false;
        Surface surface = (Surface) o;
        return getCostPerMinute() == surface.getCostPerMinute()
                && getSurfaceType() == surface.getSurfaceType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCostPerMinute(), getSurfaceType());
    }
}
