package br.com.projetoDP.service;

import br.com.projetoDP.domain.UserObserver;
import br.com.projetoDP.repository.UserRepository;
import br.com.projetoDP.utils.BaseService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

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
}
