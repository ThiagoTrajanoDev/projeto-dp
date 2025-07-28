package br.com.projetoDP.entity;

public class Notificacao {


    public static class NotificacaoBuilder {

        private  UserObserver user;
        private  Botao botao;

        public NotificacaoBuilder user(UserObserver user){
            this.user = user;
            return this;
        }

        public NotificacaoBuilder botao(Botao botao) {
            this.botao = botao;
            return this;
        }
        public String buildNotificacaoProfessor(){
            return "Professor" + this.user.nome + ",foi acionado o botão de pânico localizado no local: " + this.botao.local + "às" + this.botao.horario + "./n" + "Leve seus alunos a segurança seguindo o protocolo de treinamento";
        }

        public String buildNotificacaoAluno(){
            return "Caro aluno, foi acionado o botão de pânico localizado no local: " + this.botao.local + "./n Comunique seu professor e siga as instruções passadas por ele e mantenha a calma";
        }

        public String buildNotificacaoServidor(){
            return "Servidor " + this.user.nome +",foi acionado o botão de pânico localizado no local: " + this.botao.local + "às" + this.botao.horario + "./n" + "Siga o protocolo de segurança, chame as autoridades competentes e mantenha a calma";
        }
    }

}
