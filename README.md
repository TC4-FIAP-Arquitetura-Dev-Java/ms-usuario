# 👤 MS Usuário

Microserviço responsável pelo **gerenciamento completo de usuários** (cadastro, consulta, atualização e remoção).  
Esta API segue os padrões **REST**, utiliza **MongoDB** como banco de dados e está totalmente documentada via **Swagger (OpenAPI 3.0)**.

---

## 🛠️ Tecnologias e Infraestrutura

- **Linguagem:** Java 21 (Eclipse Temurin)
- **Framework:** Spring Boot
- **Gerenciador de Dependências:** Maven
- **Banco de Dados:** MongoDB
- **Documentação:** OpenAPI 3.0 (Swagger)
- **Containerização:** Docker

---

## ⚙️ Configurações da Aplicação

As configurações principais de conectividade e servidor estão definidas no arquivo `application.properties`:

```properties
spring.application.name=ms-usuario
server.servlet.context-path=/ms-usuario
server.port=9083

# Persistência MongoDB
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=usuarios
spring.data.mongodb.username=admin
spring.data.mongodb.password=admin
spring.data.mongodb.authentication-database=admin

spring.data.mongodb.auto-index-creation=true
```

---

## 🔗 Base URL da API

```
http://localhost:9083/ms-usuario
```

---

## 🐳 Dockerização

O microserviço está preparado para execução em containers.

### 📦 Build da imagem

```bash
docker build -t ms-usuario .
```

### ▶️ Executar o container

```bash
docker run -p 9083:9083 ms-usuario
```

---

## 🛰️ Endpoints da API (v1.0.0)

Todos os endpoints estão disponíveis sob o contexto:

```
/ms-usuario/users
```

---

### 1️⃣ Criar Usuário

**POST** `/users`

**Request Body (UserRequest):**

```json
{
  "username": "jdoe",
  "name": "John Doe",
  "password": "Password@123",
  "email": "john.doe@email.com",
  "activeUser": true
}
```

**Resposta:** `201 Created`

---

### 2️⃣ Listar Usuários (Filtros e Paginação)

**GET** `/users`

Permite a busca avançada utilizando parâmetros de consulta.

| Parâmetro | Tipo | Descrição |
|----------|------|-----------|
| name | Query | Filtra por nome |
| email | Query | Filtra por e-mail |
| username | Query | Filtra por username |
| active | Query | Filtra por status (`true` / `false`) |
| limit | Query | Quantidade por página (Default: 10) |
| offset | Query | Registros a pular (Default: 0) |

---

### 3️⃣ Consultar Usuário por ID

**GET** `/users/{id}`

**Resposta:** `200 OK`

---

### 4️⃣ Consultar Usuário por Username

**GET** `/users/username/{username}`

**Resposta:** `200 OK`

---

### 5️⃣ Atualizar Usuário

**PUT** `/users/{id}`

- Parâmetro: `id` do usuário
- Corpo: `UserRequest` atualizado

**Resposta:** `204 No Content`

---

### 6️⃣ Excluir Usuário

**DELETE** `/users/{id}`

**Resposta:** `204 No Content`

---

## 📋 Definição dos Dados (Schemas)

### UserRequest / UserResponse

| Atributo | Tipo | Descrição |
|---------|------|-----------|
| id | String | Identificador único (Read-only) |
| username | String | Nome de usuário único |
| name | String | Nome completo |
| password | String | Senha (Write-only) |
| email | String | E-mail válido |
| activeUser | Boolean | Usuário ativo |
| createdAt | DateTime | Data de criação |
| updatedAt | DateTime | Última atualização |

---

## ❌ Tratamento de Erros

Em casos de erro, a API retorna um objeto padronizado:

```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Resource not found.",
  "path": "/users/123",
  "timestamp": "2025-10-11T10:30:00Z"
}
```

---

## 📘 Documentação Swagger

A interface interativa do Swagger UI está disponível em:

```
http://localhost:9083/ms-usuario/swagger-ui.html
```

---

## 📌 Observações Finais

- Certifique-se de que o MongoDB esteja ativo e acessível
- As credenciais devem corresponder ao `application.properties`
- Todos os filtros de busca são opcionais