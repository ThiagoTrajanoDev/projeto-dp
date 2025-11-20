package br.com.projetoDP.service;

import br.com.projetoDP.dto.LoginRequest;
import br.com.projetoDP.dto.UserObserver;
import br.com.projetoDP.entity.User;
import br.com.projetoDP.repository.UserRepository;
import br.com.projetoDP.utils.BaseService;
import br.com.projetoDP.utils.Role;
import io.smallrye.common.annotation.Blocking;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Path("/user")
@Tag(name = "Usuário", description = "Gerenciamento de usuários do sistema")
public class UserProxy extends BaseService<User> {

    private final UserRepository repository;

    public UserProxy(UserRepository repository) {
        super(repository);
        this.repository = repository;
    }


    @Override
    public List<User> findAll() {
        return repository.listAll();
    }

    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserObserver> findAllObservers() {
        List<User> users = repository.listAll();
        return users.stream()
                .map(user -> new UserObserver(user.id,
                        user.getNome(),
                        user.getMatricula(),
                        user.getEmail(),
                        user.getRole(),
                        user.getTipo())).toList();
    }

    @POST
    @RolesAllowed("ADMIN")
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createUser(
            User user
    ) {
        try {
            repository.persist(user);
            UserObserver userObserver = new UserObserver(
                    user.id,
                    user.getNome(),
                    user.getMatricula(),
                    user.getEmail(),
                    user.getRole(),
                    user.getTipo()
            );
            return Response.status(Response.Status.CREATED).entity(userObserver).build();
        } catch (PersistenceException e) {
            return Response.serverError().entity("Erro ao criar usuário: " + e.getMessage()).build();
        }
    }

    @PUT
    @PermitAll
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateUser(
            @PathParam("id") Long id,
            User user
    ) {
        User existingUser = repository.findById(id);
        if (existingUser == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuário não encontrado").build();
        }
        try {
            existingUser.setNome(user.getNome());
            existingUser.setMatricula(user.getMatricula());
            existingUser.setEmail(user.getEmail());
            existingUser.setRole(user.getRole());
            existingUser.setTipo(user.getTipo());
            repository.persist(existingUser);
            UserObserver userObserver = new UserObserver(
                    existingUser.id,
                    existingUser.getNome(),
                    existingUser.getMatricula(),
                    existingUser.getEmail(),
                    existingUser.getRole(),
                    existingUser.getTipo()
            );
            return Response.ok(userObserver).build();
        } catch (PersistenceException e) {
            return Response.serverError().entity("Erro ao atualizar usuário: " + e.getMessage()).build();
        }
    }

    @DELETE
    @RolesAllowed("ADMIN")
    @Path("/{id}")
    @Transactional
    public Response deleteUser(
            @PathParam("id") Long id
    ) {
        User user = repository.findById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuário não encontrado").build();
        }
        if (user.getRole().equals(Role.ADMIN.name())) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("Não é possível deletar um usuário com papel ADMIN").build();
        }
        try {
            repository.delete(user);
            return Response.ok("Usuário deletado com sucesso").build();
        } catch (PersistenceException e) {
            return Response.serverError().entity("Erro ao deletar usuário: " + e.getMessage()).build();
        }
    }

    @POST
    @PermitAll
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Blocking
    public Response login(LoginRequest credentials) {
        User user = repository.findByEmail(credentials.getEmail());
        if (user == null || !user.getPassword().equals(credentials.getSenha())) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("E-mail ou senha inválidos").build();
        }
        UserObserver userObserver = new UserObserver(
                user.id,
                user.getNome(),
                user.getMatricula(),
                user.getEmail(),
                user.getRole(),
                user.getTipo()
        );
        return Response.ok(userObserver).build();
    }
}