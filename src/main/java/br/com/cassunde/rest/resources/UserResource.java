package br.com.cassunde.rest.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.cassunde.api.PostDAO;
import br.com.cassunde.api.UserDAO;
import br.com.cassunde.model.User;


@Path("users")
public class UserResource {

    private final UserDAO userDAO;

    @Inject
    public UserResource(UserDAO peopleDAO, PostDAO postDAO) {
        this.userDAO = peopleDAO;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll() {

    	return Response.ok(userDAO.list()).build();
    }
    
    @Path(value="/{key}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSpecific(@PathParam(value="key") String key) {
        return Response.ok(userDAO.get(key) ).build();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save( User user ) {
    	
    	user = userDAO.create(user);
        return Response.ok(user).build();
    }
}
