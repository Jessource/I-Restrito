[![Windows](https://svgshare.com/i/ZhY.svg)](https://svgshare.com/i/ZhY.svg)
[![GitHub release](https://img.shields.io/github/release/Naereen/StrapDown.js.svg)](https://GitHub.com/Naereen/StrapDown.js/releases/)
[![Maven](https://badgen.net/badge/icon/maven?icon=maven&label)](https://https://maven.apache.org/)
[![Eclipse](https://badgen.net/badge/icon/eclipse?icon=eclipse&label)](https://https://eclipse.org/)



<p align="center"> <img src = https://user-images.githubusercontent.com/68560810/176057599-9a59c22a-d05c-4e4c-a838-edd4c5a4c0e7.png /> </p>


  
  

<h1 align="center"> App-I-Restrito </h1>
<p align="center">🚀API recém construida em sua primeira versão: Projeto integrado do grupo Missão Impossível - Programa Transforme-se Serasa + Digital House</p>
<p align="center"> O objetivo é criar uma aplicação com o intuito de conectar pessoas com algum tipo de restrição alimentar. </p>

### Features

- [ ] Cadastro de usuário/pessoa
- [ ] Cadastro de produto
- [ ] Cadastro de receita
- [ ] Cadastro de comentarios-produtos/receitas


<hr>

Este projeto foi desenvolvido em [Spring Boot](https://spring.io/quickstart) na versão 2.6.7

## Configurações iniciais
Primeiramente, é necessário ter no mínimo a versão 8 do Java.\
[Versão Windows](https://www.oracle.com/java/technologies/downloads/#java8-windows).\
[Versão Linux - baseados em Debian](https://www.oracle.com/java/technologies/downloads/#java8-linux).\
[Versão MacOS](https://www.oracle.com/java/technologies/downloads/#java8-mac).

Após a instalação do Java, precisamos configurar o banco de dados, que nesse caso é o [MySQL 8](https://dev.mysql.com/downloads/mysql/). Ao término da configuração do MySQL, crie um schema chamado `db_irestrito` para que o Spring Boot possa criar as tabela e preencher com as seeds ao iniciar a aplicação.

Modifique as propriedades `spring.datasource.username` e `spring.datasource.password` no arquivo [application.properties](https://github.com/marcelloJr/agendamento-aula-api/blob/main/src/main/resources/application.properties) para seu respectivo `usuário` e `senha` do MySQL.

Crie uma pasta na raiz do seu diretório e renomei como: "uploads" passando assim o caminho para o upload de imagens: no `aplication.properties` `file.upload-dir=C:/uploads/`

Ao iniciar a aplicação, consulte o swagger no endereço http://localhost:8080/swagger-ui/#/ para ter acesso aos endpoints ou teste pelo postman, importando o documento "I-Restrito.postman_collection"

Para testar essa API juntamente com o frontend da aplicação acesse:(https://github.com/LilianChristo/I-Restrito) para saber mais.

## Dados padrões
Ao rodar a primeira vez a aplicação, alguns dados são semeados no banco:

```

                            Usuarios

id    nome                email              senha         uf       data_nascimento     Perfil
1     Administrador   admin@admin.com        admin         SP       1988-02-18          ADMIN

```
