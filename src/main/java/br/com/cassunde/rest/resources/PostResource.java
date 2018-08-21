package br.com.cassunde.rest.resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.cassunde.api.PostDAO;
import br.com.cassunde.api.UserDAO;
import br.com.cassunde.model.Post;
import br.com.cassunde.model.User;


@Path("posts")
public class PostResource {

    private final UserDAO userDAO;
    private final PostDAO postDAO;

    @Inject
    public PostResource(UserDAO peopleDAO, PostDAO postDAO) {
        this.userDAO = peopleDAO;
        this.postDAO = postDAO;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll() {

    	return Response.ok(postDAO.list()).build();
    }
    
    @Path(value="/{key}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSpecific(@PathParam(value="key") String key) {
        
    	User user = userDAO.get(key);
    	
    	try {
        	Post post1 = new Post();
        	post1.setTitle("Arq and designer");
			post1.setDate(new SimpleDateFormat("dd/mm/yyyy").parse("01/01/2018") );
			post1.setBody("Descrição");
			post1.setAuthor(user);
			
			post1 = postDAO.create(post1);
		
    	} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return Response.ok(postDAO.list()).build();
    }
}
