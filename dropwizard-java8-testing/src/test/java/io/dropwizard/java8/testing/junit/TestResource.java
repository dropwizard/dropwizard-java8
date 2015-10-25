package io.dropwizard.java8.testing.junit;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/optional")
public class TestResource {

    interface Check {
        public void check(Optional<String> paramOne, Optional<Long> paramTwo);
    }

    private final Check check;

    public TestResource(Check check) {
        this.check = check;
    }

    @GET
    @Path("/params")
    @Produces(MediaType.APPLICATION_JSON)
    public String responseOk(@QueryParam("p1") Optional<String> paramOne,
                             @QueryParam("p2") Optional<Long> paramTwo) {
        check.check(paramOne, paramTwo);
        return "{}";
    }

    @GET()
    @Path("/some")
    @Produces(MediaType.APPLICATION_JSON)
    public Optional<String> optional_some() {
        return Optional.of("{}");
    }

    @GET()

    @Path("/empty")
    @Produces(MediaType.APPLICATION_JSON)
    public Optional<String> optional_empty() {
        return Optional.empty();
    }
}