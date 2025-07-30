package br.com.projetoDP.service;

import br.com.projetoDP.dto.UserObserver;
import br.com.projetoDP.entity.Botao;
import br.com.projetoDP.repository.BotaoRepositoryImpl;
import br.com.projetoDP.utils.BaseService;
import br.com.projetoDP.utils.BotaoObservable;
import jakarta.annotation.security.RolesAllowed;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.io.IOException;
import java.util.List;

@Path("/button")
@Tag(name = "Botão", description = "Gerenciamento de botões do sistema")
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
    @Path("/create")
    @RolesAllowed("ADMIN")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createButton(Botao botao) {
        if (botao == null || botao.getLocal() == null || botao.getLocal().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Local do botão não pode ser nulo ou vazio.").build();
        }
        try {
            repository.persist(botao);
            return Response.status(Response.Status.CREATED).entity(botao).build();
        } catch (PersistenceException e) {
            return Response.serverError().entity("Erro ao criar botão: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @RolesAllowed("ADMIN")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response deleteButton(@PathParam("id") Long id) {
        try {
            Botao botao = repository.findById(id);
            if (botao == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Botão com ID " + id + " não encontrado.").build();
            }
            repository.delete(botao);
            return Response.ok()
                    .entity("Botão com ID " + id + " deletado com sucesso.").build();
        } catch (PersistenceException e) {
            return Response.serverError().entity("Erro ao deletar botão: " + e.getMessage()).build();
        }
    }

    @POST
    @Path("/push/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response pushButton(@PathParam("id") Long id) {
        try {
            Botao botao = repository.findById(id);
            if (botao == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Botão com ID " + id + " não encontrado.").build();
            }
            notificar(botao);
            return Response.ok()
                    .entity("Botão com ID " + id + " pressionado com sucesso. Enviando e-mail com instruções para usuários cadastrados.").build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro inesperado: " + e.getMessage()).build();
        }
    }

    public void notificar(Botao botao) {
        List<UserObserver> users = userProxy.findAllObservers();
        if (users.isEmpty()) {
            System.out.println("Nenhum usuário registrado para receber notificações.");
            return;
        }

        try {
            notificacaoService.enviarNotificacaoParaTodos(users, botao);
        } catch (IOException e) {
            System.err.println("Erro ao enviar notificações: " + e.getMessage());
        }
    }


}
