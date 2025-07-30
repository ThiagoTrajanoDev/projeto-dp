package br.com.projetoDP.service;

import br.com.projetoDP.entity.Botao;
import br.com.projetoDP.entity.Notificacao;
import br.com.projetoDP.dto.UserObserver;
import br.com.projetoDP.repository.NotificacaoRepository;
import br.com.projetoDP.utils.BaseService;
import br.com.projetoDP.utils.NotificacaoStrategy;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
@Path("/notification")
@Tag(name = "Notificação", description = "Gerenciamento de notificações do sistema")
public class NotificacaoService extends BaseService<Notificacao> {

    @Inject
    private NotificacaoRepository repository;

    @Inject
    @ConfigProperty(name = "sendgrid.api.key")
    String sendgridApiKey;

    public NotificacaoService(NotificacaoRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public NotificacaoService() {
        super(null);
    }

    @GET
    @Path("/history")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<Notificacao> findAll() {
        return repository.listAll();
    }

    @Transactional
    public void enviarNotificacaoParaTodos(List<UserObserver> users, Botao botao) throws IOException {
        // Builda e persiste notificação com botão e data/hora para construir histórico de pressionamento
        Notificacao notificacao = new Notificacao.NotificacaoBuilder()
                .botao(botao)
                .dataHora(LocalDateTime.now())
                .build();
        repository.persist(notificacao);

        SendGrid sg = new SendGrid(sendgridApiKey);
        for (UserObserver user : users) {
            String mensagem = NotificacaoStrategy.gerarMensagem(user, notificacao);
            Email from = new Email("mayara.dnascimentos@gmail.com", "Botão de Pânico IFPB");
            String subject = "🚨 Alerta de Emergência - Botão de Pânico Ativado";
            Email to = new Email(user.getEmail());
            Content content = new Content("text/plain", mensagem);
            Mail mail = new Mail(from, subject, to, content);

            Request request = new Request();
            try {
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());
                Response response = sg.api(request);
                System.out.println("Status: " + response.getStatusCode());
            } catch (IOException ex) {
                System.err.println("Erro ao enviar e-mail para " + user.getEmail() + ": " + ex.getMessage());
                throw ex;
            }
        }
    }
}