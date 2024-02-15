package org.acme.domain.repositories;

import org.acme.domain.model.ProfilePhoto;

public interface ProfilePhotoPersistenceRepository {
    void safe(String customerId, ProfilePhoto profilePhoto);
}
