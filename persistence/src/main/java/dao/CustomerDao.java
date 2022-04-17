package dao;

import entity.Customer;

import java.util.List;

/**
 * Interface for Dao representation of Customer entity
 *
 * @author Oliver Mach
 */
public interface CustomerDao {

    /**
     * Finds Customer entity by provided Id
     * @param id of Customer entity to be found
     * @return Customer entity
     */
    Customer findById(Long id);

    /**
     * Finds Customer entity by provided phone
     * @param phone of Customer entity to be found
     * @return
     */
    Customer findByPhone(String phone);

    /**
     * Creates provided entity Customer
     * @param customer Entity to be created
     */
    void create(Customer customer);

    /**
     * Finds all existing Customer entities
     * @return list of Customer entities
     */
    List<Customer> findAll();
}
