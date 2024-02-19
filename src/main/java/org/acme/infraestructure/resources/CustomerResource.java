package org.acme.infraestructure.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.application.ApplicationService;
import org.acme.application.dto.Customer;
import org.acme.application.dto.ProfilePhoto;


import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.util.List;
import java.util.NoSuchElementException;

@Path("customers")
public class CustomerResource {

    private final ApplicationService service;

    public CustomerResource(ApplicationService service) {
        this.service = service;
    }

    @GET
    public List<Customer> searchCustomers(){
        return service.searchCustomers();
    }

    @GET
    @Path("/{id}")
    public Customer getCustomer(@PathParam("id") String id){
        try {
            return service.getCustomer(id);
        }catch (NoSuchElementException exception){
            throw new NotFoundException();
        }
    }

    @POST
    @Path("/{id}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @ResponseStatus(RestResponse.StatusCode.ACCEPTED)
    public void persistProfilePhoto(@PathParam("id") String id,
                                    @RestForm("photo") FileUpload fileUpload){
        service.persistProfilePhoto(id, ProfilePhoto.create(fileUpload));
    }
}
