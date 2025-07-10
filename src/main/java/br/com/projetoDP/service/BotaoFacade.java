package br.com.projetoDP.service;

import br.com.projetoDP.domain.Botao;
import br.com.projetoDP.repository.BotaoRepositoryImpl;
import br.com.projetoDP.utils.BaseService;
import br.com.projetoDP.utils.Observable;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/button")
public class BotaoFacade extends BaseService<Botao> implements Observable {


    private final BotaoRepositoryImpl repository;

    protected BotaoFacade(BotaoRepositoryImpl repository) {
        super(repository);
        this.repository = repository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<Botao> findAll() {
        return repository.listAll();
    }

    @Override
    public void notificar() {

    }
}
