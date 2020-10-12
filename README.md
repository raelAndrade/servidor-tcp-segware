# PROJETO SERVIDOR TCP

### Definições escolhidas

* A aplicação consiste de uma Integração Java utilizando Sockets nativo, com o objetivo de receber as mensagens enviadas pelo cliente e salvar na base de dados.

* Versão do Java: 8

* Versão do Maven: 3.6.0

* Banco de dados em memória: H2


### Comando para iniciar o banco de dados H2, dentro da pasta h2/bin executa o comando abaixo.

java -cp h2-1.4.199.jar org.h2.tools.Server

### Acesso ao banco de dados em memória no Navegador

* [Console H2](http://127.0.1.1:8082)

* Selecionar a Configuração: Generic H2 (Server)

* Driver Class: org.h2.Driver

* JDBC URL: jdbc:h2:tcp://localhost/~/test

* User Name: sa

* Password: (vazio)

