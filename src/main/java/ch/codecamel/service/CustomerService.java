package ch.codecamel.service;

import ch.codecamel.model.Customer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CustomerService {

    @PersistenceContext
    EntityManager em;

    public void create(Customer entity) {
        em.persist(entity);
    }

    public void update(Customer entity) {
        em.merge(entity);
    }

    public void remove(long id) {
        Customer customer = find(id);
        em.remove(customer);
    }

    public Customer find(long id) {
        return em.find(Customer.class, id);
    }
}
