# Projeto Testes de Software - Botão de Pânico

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
- **Bridge**: Para separar a abstração do repositório da implementação, facilitando a extensão do sistema.
- **Proxy**: Para controle de acesso e segurança dos usuários aos recursos do sistema.

## Requisitos

### Requisitos Funcionais

- RF01: O botão de pânico poderá ser um dispositivo físico ou de componente de software, contendo uma trava de segurança para evitar cliques acidentais. 
- RF02: O botão de pânico deverá estar presente em cada sala do Campus bem como em áreas comuns (Área de vivência, biblioteca, etc). 
- RF03: O sistema deve permitir o cadastro, remoção e alteração de usuários que irão participar do monitoramento do sistema (Seguranças, Recepcionistas, Direção do Campus, etc). 
- RF04: Apenas Administradores podem realizar essas operações. 
- RF05: Ao pressionar o botão, irão ser enviados a todos os usuários cadastrados no sistema um e-mail contendo:
  - O local em que foi pressionado o botão
  - O horário
    RF06: Os avisos do sistema não deverão emitir alertas sonoros para evitar pânico dos frequentadores da instituição, possibilitando uma tomada de decisão mais eficiente e que os processos sejam ágeis, o alerta será enviado via e-mail aos usuários cadastrados.

- RF07: O sistema deverá conter um histórico do pressionamento de cada botão.
- RF08: Ao receber o aviso de que um botão foi pressionado, cada usuário deverá receber instruções de como agir na situação, como por exemplo:
  - Manter a calma
  - Acionar as autoridades competentes
  - Iniciar um protocolo de emergência pré-definido pela instituição
- RF9: O sistema deverá enviar notificações personalizadas a depender do tipo de usuário cadastrado.
- RF10: O sistema terá três tipos de usuários diferentes:
  - Professor
  - Aluno
  - Servidor

### Requisitos Não Funcionais

- RNF01: O tempo total para pressionamento do botão e chegada da notificação no sistema de controle  e monitoramento deverá ser de no máximo 2,5 segundos.
- RNF02: O sistema deverá ser resistente a falhas, não podendo passar mais de 2 minutos fora do ar em horários em que esteja ocorrendo aulas no Campus.
- RNF03: O sistema deverá ser uma Interface de programação de Aplicação (API), para que a implementação do botão seja desacoplada da sua lógica.


## Vídeos de Demonstração

- [Vídeo Comercial](https://www.youtube.com/watch?v=QWZnEV-aiRI)
- [Vídeo Técnico](https://www.youtube.com/watch?v=dgRUXgwhUmI)
- [Vídeo Demonstração](https://www.youtube.com/watch?v=rDbLkGwl-b4)

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

- Mayara do Nascimento Silva
- Thiago Trajano Farias 
- Geovana Stefani Lopes Bezerra
- Luiza Bruna Apolinário
- Lucas Nogueira Aiello
