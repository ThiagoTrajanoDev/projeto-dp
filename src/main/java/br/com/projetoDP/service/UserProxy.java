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
    public Response createUser(UserObserver user, @PathParam("role") Role role) {
        if (role != Role.ADMIN) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("Apenas usuário com papel ADMIN pode criar outros usuários.").build();
        }
        try {
            repository.persist(user);
            return Response.status(Response.Status.CREATED).entity(user).build();
        } catch (PersistenceException e) {
            return Response.serverError().entity("Error creating user: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}/{role}")
    public Response deleteUser(@PathParam("id") Long id, @PathParam("role") Role role) {
        if (role != Role.ADMIN) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("Apenas usuário com papel ADMIN pode remover outros usuários.").build();
        }
        UserObserver user = repository.findById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usário não encontrado.").build();
        }
        try {
            repository.delete(user);
            return Response.noContent().build();
        } catch (PersistenceException e) {
            return Response.serverError().entity("Erro ao remover usuário: " + e.getMessage()).build();
        }
    }
}