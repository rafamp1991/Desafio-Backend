# Desafio-Backend

## Descrição

Projeto criado por Rafael Martins de Padua, para um desafio na empresa CompassoUOL, Chapecó - SC.

Este projeto preve a construção de uma api backend, destinada a gerar cadastros e gerencia-los utilizando as operações básicas de (inserção, remoção, atualização e consulta), para as seguintes entidades:

* Cliente
* Cidade
* Estado

## API

Em conjunto com este projeto foi desenvolvida uma API para o frontend em React JS. Para acesso ao repositório, segue o link [mateusxlima](https://github.com/mateusxlima/Compacity).

## Pré requisitos

* Java8
* spring boot
* maven
* IDEs Eclipse
* banco de dados relacional (Postgresql)

## Como testar a aplicação

* No Repositório do github, dentro do diretório database_backup se encontra o arquivo [database.backup](https://github.com/rafamp1991/DesafioBackend/tree/master/database_backup), contendo o backup do banco de dados com as tabelas populadas.

* Para rodar a aplicação abra um prompt de comando e insira o caminho do diretório em que está o projeto, em seguida execute o seguinte comando:
	* mvn spring-boot:run

Para simular as requisições HTTP é recomendável utilizar o aplicativo Postman. Segue o link para download
[Postman](https://www.postman.com/downloads/).

	* Após o Postman for inicializado, no topo da aplicação a um botão "import", para carregar coleções.

* No projeto consta um diretório nomeado "postman_backup", onde há um arquivo para importar a coleção de testes [postman_backup](https://github.com/rafamp1991/DesafioBackend/tree/master/postman_backup).

## Como acessar a documentação com o swagger

* Com a aplicação inicializada, na sequência acesse a seguinte URL em seu navegador:
	* http://localhost:8080/swagger-ui.html

## Rotas

### CIDADE

* Listar todas as cidades: http://localhost:8080/cidades
	
* Consultar cidade pelo nome: http://localhost:8080/cidade/Lages

* Listar cidades pelo nome do Estado: http://localhost:8080/cidade/estadoNome/Santa Catarina

* Cadastrar cidade: http://localhost:8080/cidade	
	
* Atualizar cidade: http://localhost:8080/cidade/ + id
	
* Deletar cidade: http://localhost:8080/cidade + id

### ESTADO

* Listar todos os estados: http://localhost:8080/estados
	
* Consultar estado pelo nome: http://localhost:8080/estadoNome/Acre
	
* Consultar estado pelo UF: http://localhost:8080/estadoUf/RJ

* Cadastrar estado: http://localhost:8080/estado
	
* Atualizar estado: http://localhost:8080/estado/ + id
	
* Deletar estado: http://localhost:8080/estado/ + id

### CLIENTE

* Listar todos os clientes: http://localhost:8080/clientes
	
* Consultar cliente pelo nome: http://localhost:8080/clienteNome/	+ nome

* Consultar cliente pelo id: http://localhost:8080/clienteId/ + id
	
* Cadastrar cliente: http://localhost:8080/cliente
	
* Atualizar cliente: http://localhost:8080/cliente/ + id
	
* Deletar cliente: http://localhost:8080/cliente/ + id

## Licença

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
