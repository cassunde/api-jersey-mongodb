package br.com.cassunde.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class IndexResource {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPeopleById() {        
        return Response.ok("system up").build();
    }
}
