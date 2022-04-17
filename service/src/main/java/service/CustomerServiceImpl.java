package service;

import dao.CustomerDao;
import entity.Customer;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of Customer service interface
 *
 * @author Oliver Mach
 */
@Service
public class CustomerServiceImpl implements CustomerService{
    @Inject
    private CustomerDao repository;

    public void create(Customer entity) {
        if (entity == null)
            throw new IllegalArgumentException("Given entity is null");
        repository.create(entity);
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Customer findById(Long id) {
        return repository.findById(id);
    }

    public Customer findByPhone(String phone) {
        return repository.findByPhone(phone);
    }
}
