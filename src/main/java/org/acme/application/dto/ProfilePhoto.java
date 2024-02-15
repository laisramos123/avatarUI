package org.acme.application.dto;

import org.jboss.resteasy.reactive.multipart.FileUpload;
import java.util.UUID;

public record ProfilePhoto(FileUpload fileUpload) {
    public static ProfilePhoto create (FileUpload fileUpload){
        return new ProfilePhoto(fileUpload);
    }
    public org.acme.domain.model.ProfilePhoto toDomain() {
        return new org.acme.domain.model.ProfilePhoto(UUID.randomUUID().toString(),
                fileUpload().uploadedFile().toAbsolutePath().toString(),null);
    }
}
