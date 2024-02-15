package org.acme.infraestructure.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.acme.domain.model.Customer;
import org.acme.domain.repositories.CustomerQuery;
import org.acme.domain.repositories.CustomerRepository;
import org.acme.infraestructure.repositories.entities.CustomerProfilePhoto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class HibernateCustumerRepository implements CustomerRepository {
    private final EntityManager entityManager;

    public HibernateCustumerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Customer> find(CustomerQuery query) {
        var cb = entityManager.getCriteriaBuilder();
        var cq = cb.createQuery(CustomerProfilePhoto.class);
        var root = cq.from(CustomerProfilePhoto.class);

        cq.select(root).where(conditions(query,cb,root));

        return entityManager.createQuery(cq)
                .getResultStream()
                .map(CustomerProfilePhoto::toDomain)
                .collect(Collectors.groupingBy(Customer::id))
                .entrySet()
                .stream()
                .map(entry -> new Customer(entry.getKey(),
                                            entry.getValue()
                                                    .stream()
                                                    .flatMap(customer -> customer.profilePhotos().stream()).toList()))
                .toList();

    }

    private Predicate[] conditions(CustomerQuery query, CriteriaBuilder cb, Root<CustomerProfilePhoto> root) {
        return Stream.of(
                query.ids().map(id-> cb.in(root.get("compositeKey").get("customeId")).value(id)))
                        .flatMap(Optional::stream)
                        .toArray(Predicate[]::new);
    }
}
