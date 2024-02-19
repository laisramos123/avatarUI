package org.acme.application;

import org.acme.application.dto.Customer;
import org.acme.application.dto.ProfilePhoto;
import org.acme.domain.service.ProfilePhotoCreateService;
import org.acme.domain.service.CustomerReadService;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ApplicationService {
    private final CustomerReadService customerReadService;
    private  final ProfilePhotoCreateService profilePhotoCreateService;

    public ApplicationService(CustomerReadService customerReadService, ProfilePhotoCreateService profilePhotoCreateService) {
        this.customerReadService = customerReadService;
        this.profilePhotoCreateService = profilePhotoCreateService;
    }

    public List<Customer> searchCustomers(){
        return customerReadService.find().stream().map(Customer::fromDomain).toList();
    }
    public Customer getCustomer(String customerId){
        return Customer.fromDomain(customerReadService.findById(customerId));
    }

    public void persistProfilePhoto(String customerId, @NotNull ProfilePhoto dto){
        profilePhotoCreateService.save(customerId, dto.toDomain());
    }
}
