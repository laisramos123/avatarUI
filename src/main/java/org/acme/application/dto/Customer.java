package org.acme.application.dto;


import java.util.List;

public record Customer(String customerId, List<String> photos) {
    public static Customer fromDomain(org.acme.domain.model.Customer domain){
        return new Customer(domain.id(),
                domain.profilePhotos().stream().map(ProfilePhoto::generatedPhoto).toList());
    }
}
