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

## Como executar a aplicação

Execute o comando mvn spring-boot:run para executar a aplicação.

## Rotas

### CIDADE

* Listar todas as cidades: http://localhost:8080/cidades
	
* Consultar cidade pelo nome: http://localhost:8080/cidade/Lages

* Listar cidades pelo nome do Estado: http://localhost:8080/cidade/estadoNome/Santa Catarina

* Cadastrar cidade: http://localhost:8080/cidade	
	
* Atualizar cidade: http://localhost:8080/cidade/ + id
	
* Deletar cidade: http://localhost:8080/cidade + id

## Licença

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
