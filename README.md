# Parking Control API
> Exemplo de uma API de controle de estacionamento residencial utilizando Java com o Framework Spring-Boot.

[![NPM Version][npm-image]][npm-url]
[![Build Status][travis-image]][travis-url]
[![Downloads Stats][npm-downloads]][npm-url]

## Exemplo de uso
 Nesta API é possível: 
 * Cadastrar um veículo vinculado a um morador e a uma vaga fixa;
 * Listar todos os veículos do condomínio vinculados a morodar e as respectivas vagas;
 * Listar um cadastro de veículo vinculado a um morador e a uma vaga fixa por ID;
 * Atualizar um veículo vinculado a um morador e a uma vaga fixa por ID;
 * Excluir um veículo vinculado a um morador e a uma vaga fixa por ID;

## Algumas validações: 
* Só é possível cadastrar um veículo por vaga fixa;
* Só é possível cadastrar um veículo por placa, caso a placa já esteja cadastrada não permite novamente;

## Requisitos de Banco de Dados
Instalar um Banco de dados Postgresql e definir uma senha para o schema (ex: 12345)
Criar uma base de dados chamada parking-control-db.

Atentar-se ao arquivo de configuração application.properties do projeto que contém as configurações para conexão. O projeto irá criar a tabela ao ser executado.

## Teste via Swagger:
![](https://user-images.githubusercontent.com/414878/157461855-e9ae9c1a-f3d1-47bf-9eb0-1b44b22f7ee4.png)

## Teste via Postman:

* POST (Salvando)
![](https://user-images.githubusercontent.com/414878/157462025-a46151d8-9b01-405e-b1f5-24a6fbd4242a.png)

* GETALL (Buscando todos)
![](https://user-images.githubusercontent.com/414878/157461921-49dce45c-d3c5-4d83-abfc-8eb059dbaefd.png)

* GETBYID (Buscando por ID)
![](https://user-images.githubusercontent.com/414878/157461977-2f6a2ead-08c8-4c34-be29-707257dd5e54.png)

* PUT (Atualizando por ID)
![](https://user-images.githubusercontent.com/414878/157462103-b4ec815e-3fd9-4c30-b94e-9d50a609872c.png)

* DELETE (Deletando por ID
![](https://user-images.githubusercontent.com/414878/157462046-c49de6d7-6928-49e4-a007-ee03990a4494.png)

## Meta

Edison de Azevedo Filho – [@azevedoedison](https://twitter.com/azevedoedison) – [Linkedin:](https://www.linkedin.com/in/edison-de-azevedo/)
[https://github.com/azevedoedison/parking_control_api](https://github.com/azevedoedison/)


[npm-image]: https://img.shields.io/npm/v/datadog-metrics.svg?style=flat-square
[npm-url]: https://npmjs.org/package/datadog-metrics
[npm-downloads]: https://img.shields.io/npm/dm/datadog-metrics.svg?style=flat-square
[travis-image]: https://img.shields.io/travis/dbader/node-datadog-metrics/master.svg?style=flat-square
[travis-url]: https://travis-ci.org/dbader/node-datadog-metrics
[wiki]: https://github.com/seunome/seuprojeto/wiki
