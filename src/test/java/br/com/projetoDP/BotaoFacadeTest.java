package br.com.projetoDP;


import br.com.projetoDP.entity.Botao;
import br.com.projetoDP.repository.BotaoRepositoryImpl;
import br.com.projetoDP.service.BotaoFacade;
import br.com.projetoDP.service.NotificacaoService;
import br.com.projetoDP.service.UserProxy;

import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BotaoFacadeTest {

        BotaoFacade botaoFacade;
        BotaoRepositoryImpl botaoRepository;
        UserProxy userProxy;
        NotificacaoService notificacaoService;
        final String IFPB = "IFPB";

        @BeforeEach
        void setup(){
                botaoRepository = mock();
                userProxy = mock();
                notificacaoService = mock();
                botaoFacade = new BotaoFacade(botaoRepository,userProxy,notificacaoService);
        }

        @Test
        void deveRetornar201QuandoBotaoForCadastradoComSucesso(){
                Botao botao = new Botao();
               
                botao.local = IFPB;
                Response response = botaoFacade.createButton(botao);
                assertEquals(Response.Status.CREATED.getStatusCode(),response.getStatus());

        }

        @Test
        void deveRetornaErroQuandoBotaoForCadastradoSemDados(){
                Botao botao = new Botao();
                doThrow(PersistenceException.class).when(botaoRepository).persist(botao);
                Response response = botaoFacade.createButton(botao);
                assertEquals(Response.Status.BAD_REQUEST.getStatusCode(),response.getStatus());
        }

        @Test
        void deveRetornar200QuandoUmBotaoExistenteForDeletado(){
                Long id = 1L;
                Botao botao = new Botao();
                botao.id = id;
                botao.local = IFPB;
                when(botaoRepository.findById(id)).thenReturn(botao);
                Response response = botaoFacade.deleteButton(id);
                assertEquals(Response.Status.OK.getStatusCode(),response.getStatus());
        }

        @Test
        void deveRetornar404QuandoTentarDeletarUmBotaoInexistente(){
                Long id = 1L;
                Botao botao = new Botao();
                botao.id = id;
                botao.local = IFPB;
                when(botaoRepository.findById(id)).thenReturn(null);
                Response response = botaoFacade.deleteButton(id);
                assertEquals(Response.Status.NOT_FOUND.getStatusCode(),response.getStatus());
        }

        @Test
        void deveRetornar500QuandoOcorrerErroDePersistencia(){
                Long id = 1L;
                Botao botao = new Botao();
                botao.id = id;
                botao.local = IFPB;
                when(botaoRepository.findById(id)).thenReturn(botao);
                doThrow(PersistenceException.class).when(botaoRepository).delete(botao);
                Response response = botaoFacade.deleteButton(id);
                assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),response.getStatus());
        }

        @Test
        void deveRetornar404QuandoTentarPressionarUmBotaoInexistente(){
                Long id = 1L;
                Botao botao = new Botao();
                botao.id = id;
                botao.local = IFPB;
                when(botaoRepository.findById(id)).thenReturn(null);
                Response response = botaoFacade.pushButton(id);
                assertEquals(Response.Status.NOT_FOUND.getStatusCode(),response.getStatus());
        }

        @Test
        void deveRetornar200QuandoUmBotaoExistenteForPressionado(){
                Long id = 1L;
                Botao botao = new Botao();
                botao.id = id;
                botao.local = IFPB;
                when(botaoRepository.findById(id)).thenReturn(botao);
                Response response = botaoFacade.pushButton(id);
                assertEquals(Response.Status.OK.getStatusCode(),response.getStatus());
        }

        @Test
        void deveRetornar500QuandoOcorrerErroInesperadoAcontecer(){
                Long id = 1L;
                Botao botao = new Botao();
                botao.id = id;
                botao.local = IFPB;
                doThrow(PersistenceException.class).when(botaoRepository).findById(id);
                Response response = botaoFacade.pushButton(id);
                assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),response.getStatus());
        }


}
