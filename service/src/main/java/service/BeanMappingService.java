package service;

import java.util.Collection;
import java.util.List;

import org.dozer.Mapper;

/**
 * Interface for bean mapping
 *
 * @author Oliver Mach
 */
public interface BeanMappingService {
    <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);
    <T> T mapTo(Object u, Class<T> mapToClass);
    Mapper getMapper();
}
