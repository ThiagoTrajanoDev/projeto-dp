package br.com.projetoDP.service;

import br.com.projetoDP.domain.Botao;
import br.com.projetoDP.domain.Notificacao;
import br.com.projetoDP.domain.UserObserver;
import br.com.projetoDP.repository.NotificacaoRepository;
import br.com.projetoDP.utils.BaseService;
import br.com.projetoDP.utils.NotificacaoStrategy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import io.quarkus.mailer.Mailer;
import io.quarkus.mailer.Mail;

import java.util.List;

@ApplicationScoped
public class NotificacaoService extends BaseService<Notificacao> {

    private NotificacaoRepository repository;

    @Inject
    Mailer mailer;

    public NotificacaoService(NotificacaoRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public NotificacaoService() {
        super(null);
    }

    @Override
    public List<Notificacao> findAll() {
        return repository.listAll();
    }

    public void enviarNotificacao(Notificacao notificacao) {
        // Implementar lógica para enviar notificação
        repository.persist(notificacao);
    }

    public void enviarNotificacaoParaTodos(List<UserObserver> users, Botao botao) {
        for (UserObserver user : users) {
            String mensagem = NotificacaoStrategy.gerarMensagem(user, botao);
            mailer.send(Mail.withText(user.email, "🚨 Alerta de Emergência - Botão de Pânico Ativado", mensagem));
        }
    }
}
