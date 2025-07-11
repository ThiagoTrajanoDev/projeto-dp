package br.com.projetoDP.service;

import br.com.projetoDP.domain.UserObserver;
import br.com.projetoDP.repository.UserRepository;
import br.com.projetoDP.utils.BaseService;
import br.com.projetoDP.utils.Role;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/user")
public class UserProxy extends BaseService<UserObserver> {

    private final UserRepository repository;

    public UserProxy(UserRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<UserObserver> findAll() {
        return repository.listAll();
    }

    @POST
    @Path("/{role}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(UserObserver user, @PathParam("role") Role role ) {
        try{
            if(role.equals(Role.ADMIN)){
                repository.persist(user);
                return Response.ok().entity(user).build();
            } else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }


}
