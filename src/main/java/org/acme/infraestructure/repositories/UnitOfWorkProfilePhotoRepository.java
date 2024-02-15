package org.acme.infraestructure.repositories;

import jakarta.enterprise.context.RequestScoped;
import org.acme.domain.model.ProfilePhoto;
import org.acme.domain.repositories.ProfilePhotoRepository;

import java.util.Map;

@RequestScoped
public class UnitOfWorkProfilePhotoRepository implements ProfilePhotoRepository {
    private final HibernateProfilePhotoPersistenceRepository hibernateProfilePhotoPersistenceRepository;
    private final S3ProfilePhotoStorageRepository s3ProfilePhotoStorageRepository;
    private final StableDiffusionService stableDiffusionService;
    private Map<String, ProfilePhoto> entities;

    public UnitOfWorkProfilePhotoRepository(HibernateProfilePhotoPersistenceRepository hibernateProfilePhotoPersistenceRepository, S3ProfilePhotoStorageRepository s3ProfilePhotoStorageRepository, StableDiffusionService stableDiffusionService) {
        this.hibernateProfilePhotoPersistenceRepository = hibernateProfilePhotoPersistenceRepository;
        this.s3ProfilePhotoStorageRepository = s3ProfilePhotoStorageRepository;
        this.stableDiffusionService = stableDiffusionService;
        this.entities = Map.of();
    }

    @Override
    public void registerEntities(Map<String, ProfilePhoto> customerId) {
        this.entities = entities;
    }

    @Override
    public void commit() {

    }

    @Override
    public void rollback() {

    }
}
