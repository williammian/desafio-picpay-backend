# Desafio PicPay Backend

API de plataforma de pagamentos simplificada que permite realizar depósitos e transferências entre usuários.

## Índice

- [Visão Geral](#visão-geral)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Arquitetura](#arquitetura)
- [Funcionalidades](#funcionalidades)
- [Requisitos](#requisitos)
- [Configuração e Execução](#configuração-e-execução)
- [Endpoints da API](#endpoints-da-api)
- [Exemplos de Uso](#exemplos-de-uso)
- [Testes](#testes)

## Visão Geral

Este projeto implementa uma API REST para uma plataforma de pagamentos simplificada, permitindo operações como criação de carteiras (wallets) e transferências entre usuários. A aplicação foi desenvolvida seguindo boas práticas de desenvolvimento, com foco em escalabilidade, manutenibilidade e segurança.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal
- **Spring Boot**: Framework para desenvolvimento de aplicações Java
- **Spring Data JPA**: Facilita o acesso a dados com JPA
- **MySQL**: Banco de dados relacional
- **Docker**: Containerização da aplicação e dependências
- **OpenFeign**: Cliente HTTP declarativo para integração com serviços externos
- **CompletableFuture**: Para processamento assíncrono
- **Bean Validation**: Validação de dados de entrada
- **Problem Details**: Padronização de respostas de erro
- **Lombok**: Redução de código boilerplate
- **Log4J**: Logging da aplicação

## Arquitetura

O projeto segue uma arquitetura em camadas:

- **Controller**: Responsável por receber as requisições HTTP e retornar as respostas
- **Service**: Contém a lógica de negócio da aplicação
- **Repository**: Camada de acesso a dados
- **Entity**: Representação das entidades do domínio
- **DTO**: Objetos de transferência de dados
- **Exception**: Tratamento de exceções customizadas

A aplicação utiliza o padrão REST para comunicação, com endpoints bem definidos para cada operação.

## Funcionalidades

- Criação de carteiras (wallets) para usuários
- Transferências entre usuários
- Validação de transações
- Notificação de transações (simulada via serviço externo)
- Autorização de transações (simulada via serviço externo)

## Requisitos

- Java 21
- Docker e Docker Compose
- Maven (opcional, pode usar o wrapper incluído no projeto)

## Configuração e Execução

### Usando Docker Compose

1. Clone o repositório:
```bash
git clone https://github.com/williammian/desafio-picpay-backend.git
cd desafio-picpay-backend
```

2. Execute o Docker Compose para iniciar o banco de dados MySQL:
```bash
cd docker
docker-compose up -d
```

3. Execute a aplicação:
```bash
cd ../picpay
./mvnw spring-boot:run
```

### Configuração Manual

1. Configure um banco de dados MySQL:
   - Host: localhost
   - Porta: 3307
   - Usuário: admin
   - Senha: 123
   - Database: picpaydb

2. Execute a aplicação:
```bash
cd picpay
./mvnw spring-boot:run
```

## Endpoints da API

### Carteiras (Wallets)

#### Criar Carteira
- **URL**: `/wallets`
- **Método**: `POST`
- **Payload**:
```json
{
  "name": "Nome do Usuário",
  "document": "123.456.789-00",
  "email": "usuario@exemplo.com",
  "balance": 100.00,
  "userType": "USER" // ou "MERCHANT"
}
```
- **Resposta**: Retorna os dados da carteira criada

### Transferências

#### Realizar Transferência
- **URL**: `/transfer`
- **Método**: `POST`
- **Payload**:
```json
{
  "payer": 1, // ID do pagador
  "payee": 2, // ID do recebedor
  "value": 50.00
}
```
- **Resposta**: Retorna os dados da transferência realizada

## Exemplos de Uso

### Criando uma Carteira

```bash
curl -X POST http://localhost:8080/wallets \
  -H "Content-Type: application/json" \
  -d '{
    "name": "João Silva",
    "document": "123.456.789-00",
    "email": "joao@exemplo.com",
    "balance": 100.00,
    "userType": "USER"
  }'
```

### Realizando uma Transferência

```bash
curl -X POST http://localhost:8080/transfer \
  -H "Content-Type: application/json" \
  -d '{
    "payer": 1,
    "payee": 2,
    "value": 50.00
  }'
```

## Testes

O projeto inclui testes unitários e de integração. Para executar os testes:

```bash
cd picpay
./mvnw test
```

## Regras de Negócio

- Usuários do tipo lojista (MERCHANT) só podem receber transferências, não podem enviar
- Usuários do tipo comum (USER) podem enviar e receber transferências
- Transferências são validadas por um serviço autorizador externo
- Após uma transferência bem-sucedida, o sistema envia uma notificação para os usuários envolvidos
- O sistema verifica se o pagador tem saldo suficiente para realizar a transferência
- Todas as operações são validadas e tratadas com respostas de erro apropriadas

## Estrutura do Projeto

```
desafio-picpay-backend/
├── docker/
│   └── docker-compose.yml
├── info/
├── picpay/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/br/com/wm/picpay/
│   │   │   │   ├── client/
│   │   │   │   ├── config/
│   │   │   │   ├── controller/
│   │   │   │   ├── dto/
│   │   │   │   ├── entity/
│   │   │   │   ├── exception/
│   │   │   │   ├── repository/
│   │   │   │   ├── service/
│   │   │   │   └── PicpayApplication.java
│   │   │   └── resources/
│   │   └── test/
│   └── pom.xml
└── gitignore.txt
```

---

Desenvolvido por William Mian
