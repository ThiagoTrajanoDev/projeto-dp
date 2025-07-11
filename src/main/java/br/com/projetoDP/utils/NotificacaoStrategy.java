package br.com.projetoDP.utils;

import br.com.projetoDP.domain.Botao;
import br.com.projetoDP.domain.Notificacao;
import br.com.projetoDP.domain.UserObserver;

public class NotificacaoStrategy {

    public static String getNotificacao(UserObserver user, Botao botao){
        if(user.tipo.equals(Type.ALUNO)){
            return new Notificacao.NotificacaoBuilder().user(user).botao(botao).buildNotificacaoAluno();
        }
        else if(user.tipo.equals(Type.PROFESSOR)){
            return new Notificacao.NotificacaoBuilder().user(user).botao(botao).buildNotificacaoProfessor();
        }
        else{
            return new Notificacao.NotificacaoBuilder().user(user).botao(botao).buildNotificacaoServidor();
        }
    }
}
