package org.acme.domain.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.domain.model.Customer;
import org.acme.domain.repositories.CustomerRepository;

import java.util.List;
import java.util.NoSuchElementException;

@ApplicationScoped
public class CustomerReadService {
    private final CustomerRepository repository;
    public CustomerReadService(CustomerRepository repository) {
        this.repository = repository;
    }
    public List<Customer> find(){
        return repository.findAll();
    }

    public Customer findById(String Id){
        return repository.findById(Id).orElseThrow(NoSuchElementException::new);
    }
}
