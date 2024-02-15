package org.acme.domain.model;

import java.util.List;

public record Customer(String id, List<ProfilePhoto> profilePhotos) {
}
