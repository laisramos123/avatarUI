package org.acme.infraestructure.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.acme.domain.model.ProfilePhoto;
import org.acme.domain.repositories.ProfilePhotoPersistenceRepository;
import org.acme.infraestructure.repositories.entities.CustomerProfilePhoto;

@ApplicationScoped
public class HibernateProfilePhotoPersistenceRepository implements ProfilePhotoPersistenceRepository {
    private final EntityManager entityManager;

    public HibernateProfilePhotoPersistenceRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void safe(String customerId, ProfilePhoto profilePhoto) {
        entityManager.merge(CustomerProfilePhoto.fromDomain(customerId,profilePhoto));
    }
}
