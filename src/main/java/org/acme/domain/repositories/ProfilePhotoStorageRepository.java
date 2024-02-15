package org.acme.domain.repositories;

import io.smallrye.mutiny.Uni;
import org.acme.domain.model.ProfilePhoto;

public interface ProfilePhotoStorageRepository {
    Uni<String> store (String customerId , ProfilePhoto profilePhoto);

    Uni<String> store (String customerId, ProfilePhoto profilePhoto, String base64);

}
