package org.acme.domain.repositories;

import org.acme.domain.model.ProfilePhoto;

import java.util.Map;

public interface ProfilePhotoRepository {
    void registerEntities(Map<String, ProfilePhoto> customerId);

    void commit();
    void rollback();
}
