package service.facade;

import dto.CustomerDTO;
import dto.SurfaceDTO;
import entity.Customer;
import service.BeanMappingService;
import service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation for facade of Customer entity.
 * Serves for converting Entities to DTOs
 *
 * @author Oliver Mach
 */
@Service
@Transactional
public class CustomerFacadeImpl {
    @Inject
    private CustomerService customerService;

    @Autowired
    private BeanMappingService mapper;

    /**
     * Finds all Customer entities and converts them to DTOs
     *
     * @return list of Customer DTOs
     */
    public List<CustomerDTO> findAll() {
        List<Customer> all = customerService.findAll();
        return mapper.mapTo(all, CustomerDTO.class);
    }

    /**
     * Finds Customer entity with given Id and converts it to DTO
     *
     * @param id of Customer entity to be found
     * @return Customer DTO
     */
    public SurfaceDTO findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id is null");
        }
        Customer entity = customerService.findById(id);
        return mapper.mapTo(entity, SurfaceDTO.class);
    }
}
