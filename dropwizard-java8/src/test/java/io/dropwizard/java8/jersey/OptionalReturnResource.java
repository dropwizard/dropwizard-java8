package io.dropwizard.java8.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/optional-return/")
@Produces(MediaType.TEXT_PLAIN)
public class OptionalReturnResource {
    @GET
    public Optional<String> show(@QueryParam("id") String id) {
        return Optional.ofNullable(id);
    }
}
