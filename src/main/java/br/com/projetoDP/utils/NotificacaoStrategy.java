package br.com.projetoDP.utils;

import br.com.projetoDP.dto.UserObserver;
import br.com.projetoDP.entity.Notificacao;

public class NotificacaoStrategy {

    public static String DEFAULT_MESSAGE = "Notificação: botão de pânico acionado.";

    public static String gerarMensagem(UserObserver user, Notificacao notificacao) {
        int hour = notificacao.dataHora.getHour();
        int minute = notificacao.dataHora.getMinute();
        int second = notificacao.dataHora.getSecond();
        String time = String.format("%02d:%02d:%02d", hour, minute, second);

        switch (user.tipo) {
            case PROFESSOR:
                return "Professor " + user.nome + ", foi acionado o botão de pânico localizado no local: " + notificacao.botao.getLocal() +
                        " às " + time + ".\nLeve seus alunos à segurança seguindo o protocolo de treinamento.";
            case ALUNO:
                return "Caro aluno, foi acionado o botão de pânico localizado no local: " +
                        notificacao.botao.getLocal() + " às " + time + ".\nComunique seu professor, siga as instruções passadas por ele e mantenha a calma.";
            case SERVIDOR:
                return "Servidor " + user.nome + ", foi acionado o botão de pânico localizado no local: " + notificacao.botao.getLocal() +
                        " às " + time +".\nSiga o protocolo de segurança, chame as autoridades competentes e mantenha a calma.";
            default:
                return "Notificação: botão de pânico acionado.";
        }
    }
}