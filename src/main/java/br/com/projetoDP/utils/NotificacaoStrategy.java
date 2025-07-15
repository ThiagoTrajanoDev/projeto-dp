package br.com.projetoDP.utils;

import br.com.projetoDP.domain.Botao;
import br.com.projetoDP.domain.UserObserver;

public class NotificacaoStrategy {

    public static String gerarMensagem(UserObserver user, Botao botao) {
        switch (user.tipo) {
            case PROFESSOR:
                return "Professor " + user.nome + ", foi acionado o botão de pânico localizado no local: " +
                        botao.local + " às " + botao.horario + ".\nLeve seus alunos à segurança seguindo o protocolo de treinamento.";
            case ALUNO:
                return "Caro aluno, foi acionado o botão de pânico localizado no local: " +
                        botao.local + ".\nComunique seu professor, siga as instruções passadas por ele e mantenha a calma.";
            case SERVIDOR:
                return "Servidor " + user.nome + ", foi acionado o botão de pânico localizado no local: " +
                        botao.local + " às " + botao.horario + ".\nSiga o protocolo de segurança, chame as autoridades competentes e mantenha a calma.";
            default:
                return "Notificação: botão de pânico acionado.";
        }
    }
}