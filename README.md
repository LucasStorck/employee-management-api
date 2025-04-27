# Employee Management API
Sistema de Gerenciamento de Funcionários desenvolvido em Java com Spring Boot, seguindo a arquitetura em camadas e boas práticas de desenvolvimento de APIs RESTful.

## Funcionalidades

- Cadastro de novos funcionários
- Consulta de todos os funcionários
- Consulta de funcionário por ID
- Atualização de dados de um funcionário
- Exclusão de funcionário

## Tecnologias

- Maven
- Java 17
- Spring Boot 3
- PostgreSQL
- JUnit
- Mockito
- Swagger/OpenAI

## Rodando o Projeto

#### 1. Clonando o Porjeto

```
git clone https://github.com/LucasStorck/employee-management-api
```
#### 2. Acessando o Projeto

```
cd employee-management-api
```
#### 3. Configurando o Banco de Dados no `application.properties`

```
spring.datasource.url=jdbc:postgresql://localhost:5432/employee_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=validate
```
#### 4. Executando o Projeto

```
./mvnw spring-boot:run
```
## Rodando os Tests

#### Execute o Comando

```
./mvnw test
```

## Acessando a Documentação do Swagger

```
http://localhost:8080/swagger-ui.html
```

## Endepoints

| Método | URL | Desc |
|--------|-----|------|
| GET | /api/employees | Lista todos os funcionários |
| GET | /api/employees/{id} | Consulta funcionário por ID |
| POST | /api/employees | Cria um novo funcionário |
| PUT | /api/employees/{id} | Atualiza dados de funcionário |
| DELETE | /api/employees/{id} | Remove um funcionário |

## Considerações
Este projeto foi desenvolvido com o objetivo de consolidar conceitos fundamentais de:

- Programação Orientada a Objetos (POO)
- Desenvolvimento de APIs RESTful
- Boas práticas de desenvolvimento
- Realização de testes

**_Desenvolvido por Lucas Storck_**
