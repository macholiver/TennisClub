package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of bean mapping interface
 *
 * @author Oliver Mach
 */
@Service
public class BeanMappingServiceImpl implements BeanMappingService {
    @Autowired
    private Mapper dozer;

    /**
     * Maps collection of objects to objects of provided class
     * @param objects to be converted
     * @param mapToClass desired class of conversion
     * @param <T> type
     * @return collection of converted objects
     */
    public  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
        List<T> mappedCollection = new ArrayList<>();
        for (Object object : objects) {
            mappedCollection.add(dozer.map(object, mapToClass));
        }
        return mappedCollection;
    }

    /**
     * Converts object to object of provided class
     * @param u object to be converted
     * @param mapToClass desired class of conversions
     * @param <T> type
     * @return converted object
     */
    public  <T> T mapTo(Object u, Class<T> mapToClass)
    {
        return dozer.map(u,mapToClass);
    }
    
    public Mapper getMapper(){
    	return dozer;
    }
}
