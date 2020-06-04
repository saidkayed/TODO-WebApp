/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Api;

import Dao.TodoFacade;
import Dao.exceptions.NonexistentEntityException;
import Entity.Todo;
import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author sidad
 */
@Path("/")
public class GenericResource {

    Gson gson = new Gson();

    /* from TodoFacade */
    TodoFacade tf = new TodoFacade(Persistence.createEntityManagerFactory("pu"));

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of Api.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/todo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {

        return gson.toJson(tf.getAllTodo());
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Path("/edit/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putJson(String json, @PathParam("id") Long id) throws Exception {
        Todo t = gson.fromJson(json, Todo.class);
        
        Todo nt = tf.findTodo(id);
        nt.setBio(t.getBio());
        nt.setTask(t.getTask());
        System.out.println(nt);       
        tf.edit(nt);
        
        return Response.ok(json).build();
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJson(String json) {
        Todo t = gson.fromJson(json, Todo.class);
       
        tf.create(t);

        return Response.ok(json).build();
    }
    
    
    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response DeleteJson(String json, @QueryParam("id") Long id) throws NonexistentEntityException {
       
       
        tf.destroy(id);

        return Response.ok("deleted veriiii nice").build();
    }
}
