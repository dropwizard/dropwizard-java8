package com.github.joschi.dropwizard.java8.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/optional-param/")
@Produces(MediaType.TEXT_PLAIN)
public class OptionalParamResource {
    @GET
    public String show(@QueryParam("id") Optional<Integer> id) {
        return id.orElse(-1).toString();
    }
}
