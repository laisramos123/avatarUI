package org.acme.domain.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.domain.model.ProfilePhoto;
import org.acme.domain.repositories.ProfilePhotoRepository;

import java.util.Map;

@ApplicationScoped
public class ProfilePhotoCreateService {
    private final ProfilePhotoRepository repository;

    public ProfilePhotoCreateService(ProfilePhotoRepository repository) {
        this.repository = repository;
    }

    public void save (String custumerId, ProfilePhoto profilePhoto){
        repository.registerEntities(Map.of(custumerId,profilePhoto));
        repository.commit();

    }
}
