package dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * DTO representation of Customer entity
 *
 * @author Oliver Mach
 */
@Getter
@Setter
public class CustomerDTO {
    private Long id;

    /**
     * Phone number of the customer
     */
    private String phone;

    /**
     * Name of the customer
     */
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerDTO)) return false;
        CustomerDTO that = (CustomerDTO) o;
        return getPhone().equals(that.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhone());
    }
}

