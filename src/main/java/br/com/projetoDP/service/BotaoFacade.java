package br.com.projetoDP.service;

import br.com.projetoDP.entity.Botao;
import br.com.projetoDP.entity.User;
import br.com.projetoDP.repository.BotaoRepositoryImpl;
import br.com.projetoDP.utils.BaseService;
import br.com.projetoDP.utils.BotaoObservable;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/button")
public class BotaoFacade extends BaseService<Botao> implements BotaoObservable {


    private final BotaoRepositoryImpl repository;
    private final UserProxy userProxy;
    private final NotificacaoService notificacaoService;

    protected BotaoFacade(BotaoRepositoryImpl repository, UserProxy userProxy, NotificacaoService notificacaoService) {
        super(repository);
        this.repository = repository;
        this.userProxy = userProxy;
        this.notificacaoService = notificacaoService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<Botao> findAll() {
        return repository.listAll();
    }

    @POST
    @Path("/push")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response pushButton(Botao botao){
        try{
            repository.persist(botao);
            notificar(botao);
            return Response.accepted().build();
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public void notificar(Botao botao) {
        List<User> users =  userProxy.findAll();
        if (users.isEmpty()) {
            System.out.println("Nenhum usuário registrado para receber notificações.");
            return;
        }
        String assunto = "🚨 Alerta de Emergência - Botão de Pânico Ativado";

        notificacaoService.enviarNotificacaoParaTodos(users, botao);
    }


}
