package br.com.projetoDP.domain;

import br.com.projetoDP.utils.Model;
import br.com.projetoDP.utils.NotificacaoStrategy;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Notificacao extends Model {

    @ManyToOne(optional = false)
    public UserObserver user;

    @ManyToOne(optional = false)
    public Botao botao;

    @Column(nullable = false)
    public String mensagem;

    @Column(nullable = false)
    public LocalDateTime dataHora;

    public Notificacao() {}

    private Notificacao(UserObserver user, Botao botao, String mensagem, LocalDateTime dataHora) {
        this.user = user;
        this.botao = botao;
        this.mensagem = mensagem;
        this.dataHora = dataHora;
    }

    public static class NotificacaoBuilder {
        private UserObserver user;
        private Botao botao;
        private LocalDateTime dataHora;

        public NotificacaoBuilder user(UserObserver user) {
            this.user = user;
            return this;
        }

        public NotificacaoBuilder botao(Botao botao) {
            this.botao = botao;
            return this;
        }

        public NotificacaoBuilder dataHora(LocalDateTime dataHora) {
            this.dataHora = dataHora;
            return this;
        }

        public Notificacao build() {
            String mensagem = NotificacaoStrategy.gerarMensagem(user, botao);
            LocalDateTime timestamp = (dataHora != null) ? dataHora : LocalDateTime.now();
            return new Notificacao(user, botao, mensagem, timestamp);
        }
    }
}