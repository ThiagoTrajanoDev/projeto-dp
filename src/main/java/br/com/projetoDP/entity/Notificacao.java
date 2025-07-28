package br.com.projetoDP.entity;

import br.com.projetoDP.utils.Model;
import br.com.projetoDP.utils.NotificacaoStrategy;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Notificacao extends Model {
    @ManyToOne(optional = false)
    public Botao botao;

    @Column(nullable = false)
    public String mensagem;

    @Column(nullable = false)
    public LocalDateTime dataHora;

    public Notificacao() {}

    private Notificacao(Botao botao, String mensagem, LocalDateTime dataHora) {
        this.botao = botao;
        this.mensagem = mensagem;
        this.dataHora = dataHora;
    }

    public static class NotificacaoBuilder {
        private Botao botao;
        private String mensagem;
        private LocalDateTime dataHora;

        public NotificacaoBuilder botao(Botao botao) {
            this.botao = botao;
            return this;
        }

        public NotificacaoBuilder mensagem(String mensagem) {
            this.mensagem = mensagem;
            return this;
        }

        public NotificacaoBuilder dataHora(LocalDateTime dataHora) {
            this.dataHora = dataHora;
            return this;
        }

        public Notificacao build() {
            String msg = (mensagem != null) ? mensagem : NotificacaoStrategy.DEFAULT_MESSAGE;
            LocalDateTime timestamp = (dataHora != null) ? dataHora : LocalDateTime.now();
            return new Notificacao(botao, msg, timestamp);
        }
    }
}

