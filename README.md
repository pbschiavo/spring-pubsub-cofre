
# Spring Boot Com PUB/SUB + Arquitetura Hexagonal

Essa é uma POC para enviar e receber a mensagem do tópico pubsub pegando as credencias de algum serviço, por ex: um cofre de senha.
## Documentação da API

#### swagger

```http
  http://localhost:8080/swagger-ui/index.html#/solicitacao-controller/solicitacao
```
## Stack utilizada


**Back-end:** Java 17, Spring boot 3.2.2


## Instalação

1 - Criar uma conta de serviço no GCP

2 - Conceder as rule do pubsub de editor e subscriber

3 - Gerar o arquivo de credenciais da conta

4 - Criar o topico e a assinatura

5 - Colocar as credenciais em algums servico para ser consumido

6 - Preencher as informações do projeto GCP no application.yaml

    