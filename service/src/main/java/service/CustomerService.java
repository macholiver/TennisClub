package service;

import entity.Customer;

import java.util.List;

/**
 * Interface for Customer service
 *
 * @author Oliver Mach
 */
public interface CustomerService {

    /**
     * Creates provided Customer entity
     * @param customer entity to be created
     */
    void create(Customer customer);

    /**
     * Find Customer entity with provided Id
     * @param id of Customer entity to be found
     * @return Customer entity
     */
    Customer findById(Long id);

    /**
     * Finds Customer entity with provided phone number
     * @param phone number of entity to found
     * @return list of Customer entities
     */
    Customer findByPhone(String phone);
    List<Customer> findAll();
}
