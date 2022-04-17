package entity;

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
 * Entity representing court
 *
 * @author Oliver Mach
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Court {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the court
     */
    @Column(unique = true)
    @NotNull
    private String name;

    @OneToMany(mappedBy = "court")
    private List<Reservation> reservations = new ArrayList<>();

    @NotNull
    @ManyToOne
    private Surface surface;

    public void addReservation(Reservation r) {
        reservations.add(r);
    }

    public void addSurface(Surface s) {
        setSurface(s);
        s.addCourt(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Court)) return false;
        Court court = (Court) o;
        return getName().equals(court.getName())
                && getSurface().equals(court.getSurface());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSurface());
    }
}
