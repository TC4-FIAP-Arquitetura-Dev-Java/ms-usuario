# 👤 MS Usuário

Microserviço responsável pelo **cadastro, consulta, atualização e remoção de usuários** do sistema. A API segue o padrão **REST**, está documentada com **OpenAPI 3.0 (Swagger)** e utiliza **MongoDB** como banco de dados.

---

## 🛠️ Tecnologias e Infraestrutura

* **Linguagem:** Java 21 (Eclipse Temurin)
* **Framework:** Spring Boot
* **Gerenciador de Dependências:** Maven
* **Banco de Dados:** MongoDB
* **Documentação:** OpenAPI 3.0 (Swagger)
* **Containerização:** Docker

---

## ⚙️ Configurações da Aplicação

Principais configurações definidas no `application.properties`:

```properties
spring.application.name=ms-usuario
server.servlet.context-path=/ms-usuario
server.port=9083

spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=usuarios
spring.data.mongodb.username=admin
spring.data.mongodb.password=admin
spring.data.mongodb.authentication-database=admin

spring.data.mongodb.auto-index-creation=true
```

🔗 **Base URL da API:**

```
http://localhost:9083/ms-usuario
```

---

## 🐳 Dockerização

O microserviço possui um **Dockerfile** para execução em container.

### 📦 Build da imagem

```bash
docker build -t ms-usuario .
```

### ▶️ Executar o container

```bash
docker run -p 9083:9083 ms-usuario
```

> ℹ️ Recomenda-se utilizar `docker-compose` para subir o MongoDB em conjunto com o serviço.

---

## 🛰️ Endpoints da API

Todos os endpoints estão disponíveis sob o contexto:

```
/ms-usuario
```

---

### 1️⃣ Criar Usuário

**POST** `/usuarios`

* **Resposta:** `201 Created`

```bash
curl -X POST http://localhost:9083/ms-usuario/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "usuario": "jdoe",
    "nome": "João da Silva",
    "password": "$enh@2025",
    "email": "joao.silva@email.com",
    "usuarioAtivo": true
  }'
```

---

### 2️⃣ Buscar Usuários (com filtros e paginação)

**GET** `/usuarios`

#### Parâmetros opcionais

* `usuario` — Username do usuário
* `usuarioAtivo` — Indica se o usuário está ativo
* `page` — Número da página (default: 0)
* `size` — Quantidade de registros por página
* `sort` — Ordenação (ex: `nome,asc`)

```bash
curl -X GET "http://localhost:9083/ms-usuario/usuarios?usuario=jdoe&usuarioAtivo=true&page=0&size=10"
```

#### Exemplo de resposta (`200 OK`)

```json
{
  "content": [
    {
      "id": "652ff3a9b1c2d40012ab45de",
      "usuario": "jdoe",
      "nome": "João da Silva",
      "email": "joao.silva@email.com",
      "usuarioAtivo": true,
      "password": "sdfsdfasdf45df4asd21sad5fas2d1fsdf54",
      "dataCriacao": "2025-10-11T10:15:30Z",
      "dataAlteracao": "2025-10-11T10:20:45Z"
    }
  ],
  "totalElements": 125,
  "totalPages": 13,
  "size": 10,
  "number": 0
}
```

---

### 3️⃣ Consultar Usuário por ID

**GET** `/usuarios/{id}`

```bash
curl -X GET http://localhost:9083/ms-usuario/usuarios/652ff3a9b1c2d40012ab45de
```

* **Resposta:** `200 OK`

---

### 4️⃣ Consultar Usuário por Username

**GET** `/usuarios/getUser/{username}`

```bash
curl -X GET http://localhost:9083/ms-usuario/usuarios/getUser/jdoe
```

* **Resposta:** `200 OK`

---

### 5️⃣ Atualizar Usuário

**PUT** `/usuarios/{id}`

* **Resposta:** `204 No Content`

```bash
curl -X PUT http://localhost:9083/ms-usuario/usuarios/652ff3a9b1c2d40012ab45de \
  -H "Content-Type: application/json" \
  -d '{
    "usuario": "jdoe",
    "nome": "João da Silva",
    "password": "$enh@2026",
    "email": "joao.silva@email.com",
    "usuarioAtivo": true
  }'
```

---

### 6️⃣ Excluir Usuário

**DELETE** `/usuarios/{id}`

* **Resposta:** `204 No Content`

```bash
curl -X DELETE http://localhost:9083/ms-usuario/usuarios/652ff3a9b1c2d40012ab45de
```

---

## ❌ Padrão de Resposta de Erro

Em caso de erro (4xx ou 5xx), a API retorna:

```json
{
  "status": 400,
  "error": "Bad Request",
  "message": "Parâmetros inválidos.",
  "path": "/usuarios",
  "timestamp": "2025-10-11T10:30:00Z"
}
```

---

## 📘 Documentação OpenAPI (Swagger)

Este microserviço segue o padrão **OpenAPI 3.0**, garantindo contratos bem definidos para integrações.

* Todos os endpoints, parâmetros e modelos estão documentados via Swagger
* Recomenda-se utilizá-lo como **fonte de verdade** para consumo da API

---

## 📌 Observações Finais

* API preparada para ambientes distribuídos e arquitetura de microserviços
* Suporte a paginação, filtros e ordenação
* Pronta para integração com gateways, BFFs e sistemas de autenticação
