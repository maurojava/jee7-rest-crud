package ch.codecamel.client;

import ch.codecamel.binding.CustomerMessageBodyReader;
import ch.codecamel.binding.CustomerMessageBodyWriter;
import ch.codecamel.model.Customer;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CustomerClientTest {

    private static final String SERVER = "http://localhost:8080/jee7-rest-crud/api/customers";

    private WebTarget target;

    @Before
    public void setUp() {
        Client client = ClientBuilder.newClient();
        client.register(CustomerMessageBodyReader.class);
        client.register(CustomerMessageBodyWriter.class);
        this.target = client.target(SERVER);
    }

    @Test
    public void crud() {

        Customer origin = new Customer(1, "Mathilda");
        Entity<Customer> entity = Entity.entity(origin, MediaType.APPLICATION_JSON);

        // create
        Response response = target.request(MediaType.APPLICATION_JSON).post(entity, Response.class);
        assertThat(response.getStatus(), equalTo(204));

        // read
        Customer result = target.path(String.valueOf(origin.getId())).request(MediaType.APPLICATION_JSON).get(Customer.class);
        assertThat(result, equalTo(origin));

        // update
        entity.getEntity().setName("Annabelle");
        target.request().put(entity);

        // delete
        target.path(String.valueOf(origin.getId())).request(MediaType.APPLICATION_JSON).delete();
    }
}
