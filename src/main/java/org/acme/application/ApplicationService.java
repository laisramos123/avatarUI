package org.acme.application;

import org.acme.application.dto.Customer;
import org.acme.application.dto.ProfilePhoto;
import org.acme.domain.service.ProfilePhotoCreateService;
import org.acme.domain.service.CustomerReadService;

import java.util.List;

public class ApplicationService {
    private final CustomerReadService custumerReadService;
    private  final ProfilePhotoCreateService profilePhotoCreateService;

    public ApplicationService(CustomerReadService custumerReadService, ProfilePhotoCreateService profilePhotoCreateService) {
        this.custumerReadService = custumerReadService;
        this.profilePhotoCreateService = profilePhotoCreateService;
    }

    public List<Customer> searchCustomers(){
        return custumerReadService.find().stream().map(Customer::fromDomain).toList();
    }
    public Customer getCustomer(String customerId){
        return Customer.fromDomain(custumerReadService.findById(customerId));
    }

    public void persistProfilePhoto(String customerId, ProfilePhoto dto){
        profilePhotoCreateService.save(customerId, dto.toDomain());
    }
}
