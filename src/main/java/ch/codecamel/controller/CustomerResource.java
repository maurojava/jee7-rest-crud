package ch.codecamel.controller;

import ch.codecamel.model.Customer;
import ch.codecamel.service.CustomerService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

@Stateless
@Path("customers")
public class CustomerResource {

    private static final Logger LOG = Logger.getLogger(CustomerResource.class.getName());

    @Inject
    CustomerService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Customer customer) {
        LOG.info("POST: " + customer);
        service.create(customer);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer find(@PathParam("id") long id) {
        LOG.info("GET: " + id);
        return service.find(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Customer customer) {
        LOG.info("PUT: " + customer);
        service.update(customer);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") long id) {
        LOG.info("DELETE: " + id);
        service.remove(id);
    }
}
