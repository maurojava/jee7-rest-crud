package ch.codecamel.binding;

import ch.codecamel.model.Customer;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class CustomerMessageBodyWriter implements MessageBodyWriter<Customer> {

    @Override
    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public long getSize(Customer customer, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(Customer customer, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType,
                        MultivaluedMap<String, Object> stringObjectMultivaluedMap, OutputStream outputStream) throws IOException, WebApplicationException {

        if (mediaType.getType().equals("application") && mediaType.getSubtype().equals("json")) {
            StringBuffer buffer = new StringBuffer();

            buffer = buffer.append("{");
            buffer = buffer.append("\"id\":\"").append(customer.getId()).append("\",");
            buffer = buffer.append("\"name\":\"").append(customer.getName()).append("\"");
            buffer = buffer.append("}");

            try (PrintStream printStream = new PrintStream(outputStream)) {
                printStream.print(buffer.toString());
            }
            return;
        }
        throw new UnsupportedOperationException("Not supported MediaType: " + mediaType);
    }
}
