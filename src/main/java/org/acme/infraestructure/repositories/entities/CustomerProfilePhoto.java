package org.acme.infraestructure.repositories.entities;

import jakarta.persistence.*;
import org.acme.domain.model.Customer;
import org.acme.domain.model.ProfilePhoto;

import java.util.List;

@Entity(name = "profile_photos")
public class CustomerProfilePhoto {
    @EmbeddedId
    CompositeKey compositeKey;

    @Column(name = "original_photo")
    String originalPhoto;

    @Column(name = "generated_photo")
    String generatedPhoto;
    @Embeddable
    static class CompositeKey{
        @Column(name = "custome_id")
        String customeId;

        @Column(name = "id")
        String id;
    }
    public Customer toDomain(){
        return new Customer(compositeKey.customeId, List.of(new ProfilePhoto(compositeKey.id, originalPhoto,generatedPhoto)));
    }

    public static CustomerProfilePhoto fromDomain(String customerId, ProfilePhoto profilePhoto){
        var entity = new CustomerProfilePhoto();

        entity.compositeKey = new CompositeKey();
        entity.compositeKey.customeId = customerId;

        entity.originalPhoto = profilePhoto.originalPhoto();
        entity.generatedPhoto = profilePhoto.generatedPhoto();
        return entity;
    }
}
