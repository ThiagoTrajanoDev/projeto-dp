# Projeto Design Patterns - Botão de Pânico

## Sobre o Projeto

Este projeto é uma aplicação web desenvolvida com Quarkus para gerenciar um sistema de botão de pânico em ambientes educacionais, permitindo o envio de notificações para diferentes perfis de usuários em situações de emergência.

## Objetivos

- Prover um sistema eficiente de alerta para situações de emergência.
- Notificar rapidamente professores, alunos e servidores.
- Demonstrar a aplicação de padrões de projeto em um contexto real.

## Motivação e Relevância

A segurança em ambientes educacionais é fundamental. O projeto visa oferecer uma solução tecnológica para agilizar a comunicação em situações críticas, promovendo a segurança de todos os envolvidos.

## Padrões de Projeto Utilizados

- **Observer**: Para notificação dos usuários quando o botão é acionado.
- **Strategy**: Para customizar mensagens de notificação conforme o perfil do usuário.
- **Facade**: Para simplificar a interface de uso do botão de pânico.
- **Builder**: Para construção de objetos de notificação.
- **Template Method**: Para definir o esqueleto dos serviços, permitindo que subclasses implementem detalhes específicos.
- **Bridge**: Para desacoplar o repo

## Requisitos

### Requisitos Funcionais

- RF01: Permitir login de usuários com papéis ADMIN e COMUM.
- RF02: Permitir que o botão de pânico seja acionado e registre o evento.
- RF03: Enviar notificações personalizadas para todos os usuários cadastrados.
- RF04: Permitir consulta ao histórico de acionamentos do botão.

### Requisitos Não Funcionais

- RNF01: Utilizar framework Quarkus e Java 21.
- RNF02: Persistência dos dados em banco PostgreSQL.
- RNF03: Envio de e-mails via SMTP.
- RNF04: Interface RESTful para integração.

## Protótipo e Implementação

O protótipo foi desenvolvido utilizando Quarkus, Hibernate ORM, Panache, e integração com serviço de e-mail. O código segue boas práticas de design patterns e está disponível neste repositório.

Para rodar em modo de desenvolvimento:

```shell
./mvnw quarkus:dev
```

Para empacotar a aplicação:

```shell
./mvnw package
```

### Conclusão 

O projeto demonstra como padrões de projeto podem ser aplicados para resolver problemas reais, promovendo código reutilizável, organizado e de fácil manutenção, além de contribuir para a segurança em ambientes educacionais.

### Desenvolvedores 

- [Mayara do Nascimento Silva](https://github.com/mayarans)
- [Thiago Trajano Farias](https://github.com/ThiagoTrajanoDev)