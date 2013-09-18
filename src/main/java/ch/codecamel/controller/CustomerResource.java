package ch.codecamel.controller;

import ch.codecamel.model.Customer;
import ch.codecamel.service.CustomerService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("customers")
public class CustomerResource {

    @Inject
    CustomerService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Customer customer) {
        service.create(customer);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Customer customer) {
        service.update(customer);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") long id) {
        service.remove(id);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer find(@PathParam("id") long id) {
        return service.find(id);
    }
}
