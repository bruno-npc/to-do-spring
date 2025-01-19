# To-Do Project

Este repositório contém um sistema de gerenciamento de tarefas (To-Do), criado em **Java 21**, utilizando **Maven** e **MS SQL Server** para persistência de dados. A aplicação faz uso de **Liquibase** para migrações de banco de dados, **Spring Data JPA** para persistência, **JUnit 5** e **Mockito** para testes, além de um fluxo de desenvolvimento baseado em três branches: **customização**, **produção** e **sustentação**.

---

## Sumário

1. [Visão Geral](#visão-geral)
2. [Tecnologias Utilizadas](#tecnologias-utilizadas)
3. [Pré-Requisitos](#pré-requisitos)
4. [Instalação e Configuração do Projeto](#instalação-e-configuração-do-projeto)
5. [Configuração do Banco de Dados (MS SQL Server)](#configuração-do-banco-de-dados-ms-sql-server)
6. [Rodando o Projeto](#rodando-o-projeto)
7. [Testes Unitários (JUnit + Mockito)](#testes-unitários-junit--mockito)
8. [Liquibase](#liquibase)
9. [Estratégia de Branches](#estratégia-de-branches)
10. [Links Úteis](#links-úteis)
11. [Contato](#contato)

---

## Visão Geral

O **To-Do Project** é uma API REST que permite criar, listar, editar e excluir tarefas. Cada tarefa possui campos como:

- Título
- Descrição
- Data de Cadastro
- Status (por exemplo, “Pendente”, “Concluído”, etc.)
- Prioridade
- Tipo (por exemplo, “Pessoal”, “Trabalho”)

Além disso, o projeto oferece DTOs para retornar dados resumidos das tarefas (por exemplo, `TaskDTO`), seguindo boas práticas de organização e arquitetura.

---

## Tecnologias Utilizadas

- **Java 21.0.5**
- **Spring Boot 3.x**
- **Maven**
- **MS SQL Server**
- **Liquibase** (migrações de banco)
- **Spring Data JPA**
- **JUnit 5** e **Mockito** (para testes)
- **Lombok**
- **IntelliJ Ultimate**

---

## Pré-Requisitos

- **Java 21** instalado
    - [Link para Download Java 21.0.5](https://wiki.openjdk.org/display/JDKUpdates/JDK+21u)
- **Maven** instalado
    - [Link para Download do Maven](https://maven.apache.org/download.cgi)
- **MS SQL Server** ou SQL Server Express
    - [Link para Download do Microsoft SQL Server](https://www.microsoft.com/pt-br/sql-server/sql-server-downloads)
- IDE de preferência (IntelliJ/Eclipse/VSCode)

---

## Instalação e Configuração do Projeto

1. **Clonar** o repositório:

   ```bash
   git clone <URL_DO_REPOSITÓRIO>
   cd to-do-spring

2. Importar o projeto como Maven na IDE.
3. **Lombok**, habilite o annotation processing na sua IDE (por exemplo, IntelliJ ou Eclipse).


## Configuração do Banco de Dados (MS SQL Server)

1. Instalar ou utilizar o MS SQL Server (ou Express):
- [Link para Download MS SQL Server](https://www.microsoft.com/pt-br/sql-server/sql-server-downloads)

2. Criar o database todo_db (se não existir). Exemplo:
   `CREATE DATABASE todo_db;`

3. Configurar application.properties username e password de acordo com sua configuração durante instalação do sql server.

## Rodando o Projeto

1. Via IDE (preferencial)
   Localize a classe principal (ex.: TodoApplication.java), com @SpringBootApplication.
   Rode como Spring Boot Application.
   A aplicação inicia na porta 8080.
- Acesse: http://localhost:8080


2. Via Maven (Terminal)


   ```bash
   mvn clean install
   mvn spring-boot:run
   
A aplicação estará disponível em: http://localhost:8080

 
## Testes Unitários (JUnit + Mockito)
- Local dos testes: src/test/java/com/project/todo/...
- Ferramentas: JUnit 5 para estrutura de testes (@Test, @DisplayName etc.).
- Mockito para mockar dependências e simular comportamentos (@MockBean, Mockito.when(...) etc.).

Executando Testes:
`mvn test`

Ou via IDE, selecione Run Tests no diretório test.


## Liquibase
O projeto utiliza Liquibase para gerenciar e versionar as alterações de banco de dados.

- Arquivos de migração: src/main/resources/db/changelog/
- db.changelog-master.yaml (arquivo mestre)
- Demais arquivos (create_status.yaml, create_tipo.yaml, create_to_do.yaml etc.)
- Quando a aplicação sobe, o Liquibase verifica e aplica migrações pendentes (caso spring.liquibase.enabled=true).

## Estratégia de Branches
Este projeto segue 3 branches principais:

- customizacao
Onde são desenvolvidas novas funcionalidades ou melhorias.
Ao concluir, faz merge para producao.

- producao
Estado atual do deploy em produção.
Recebe merges de customizacao (features) e sustentacao (hotfixes).

- sustentacao
Destinada a hotfixes e correções urgentes.
Após correção, faz merge em producao.

Fluxo Simplificado de Branches:

`(customizacao) --(merge)--> (producao) <--(merge)-- (sustentacao)`


## Links Úteis
  - [Documentação do Spring Boot](https://docs.spring.io/spring-boot/documentation.html)  
  - [Documentação do Liquibase](https://docs.liquibase.com/home.html)  
  - [Documentação do MS SQL Server](https://learn.microsoft.com/pt-br/sql/?view=sql-server-ver16)  
  - [Documentação do Maven](https://maven.apache.org/guides/index.html)  
  - [Guia Lombok](https://medium.com/devdomain/using-lombok-in-spring-boot-simplifying-your-code-c38057894cb8)  