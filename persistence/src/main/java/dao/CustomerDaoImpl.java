package dao;

import entity.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implementation of CustomerDao interface
 *
 * @author Oliver Mach
 */
@Repository
public class CustomerDaoImpl implements CustomerDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Customer findById(Long id) {
        return em.find(Customer.class, id);
    }

    @Override
    public Customer findByPhone(String phone) {
        List<Customer> customerList =  em.createQuery("select c from Customer c where c.phone = :phone", Customer.class)
                .setParameter("phone", phone)
                .getResultList();
        if (customerList.isEmpty())
            return null;
        return customerList.get(0);

    }

    @Override
    public List<Customer> findAll() {
        return em.createQuery("select c from Customer c", Customer.class)
                .getResultList();
    }

    @Override
    public void create(Customer customer) {
        em.persist(customer);
    }
}
