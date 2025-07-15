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
            return Response.serverError().entity("Erro ao criar usuário: " + e.getMessage()).build();
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

    @POST
    @Path("/register/{role}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(UserObserver user, @PathParam("role") Role role) {
        if (role != Role.ADMIN && role != Role.MONITOR) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("Apenas usuários ADMIN ou MONITOR podem realizar cadastro.").build();
        }
        if (repository.findByEmail(user.getEmail()) != null) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("E-mail já registrado").build();
        }
        repository.persist(user);
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @POST
    @Path("/login/{role}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserObserver credentials, @PathParam("role") Role role) {
        if (role != Role.ADMIN && role != Role.MONITOR) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("Apenas ADMIN ou MONITOR podem realizar login").build();
        }
        UserObserver user = repository.findByEmail(credentials.getEmail());
        if (user == null || !user.getPassword().equals(credentials.getPassword())) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("E-mail ou senha inválidos").build();
        }
        return Response.ok(user).build();
    }
}