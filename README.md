# FriseurPlan

## Description

**FriseurPlan** is a tool for managing and visualizing hairdressing appointments. It allows users to create, update, and delete appointments, as well as view them in a clear and organized interface.

The system uses PostgreSQL as the database and Keycloak for authentication and user management.

## Technologies Used

- Docker
- Docker Compose
- PostgreSQL
- Keycloak

## How to Run

1. Configure the variables listed below.
2. Run the following command:

```bash
docker compose up --build
````

3. Then run the java application
   
## Environment Variables

| Variable                  | Description                                                                    | Example                                          |
|---------------------------|--------------------------------------------------------------------------------|--------------------------------------------------|
| `POSTGRES_PASSWORD`       | Senha do usuário do PostgreSQL (tanto para a aplicação quanto para o Keycloak) | `pgpw`                                           |
| `POSTGRES_USER`           | Nome de usuário do PostgreSQL                                                  | `friseur_user`                                   |
| `POSTGRES_DB_URL`         | Hostname ou endereço do banco PostgreSQL da aplicação                          | `localhost`                                      |
| `POSTGRES_DB`             | Nome do banco de dados da aplicação                                            | `FriseurPlanDb`                                  |
| `KEYCLOAK_ADMIN`          | Nome de usuário do administrador do Keycloak                                   | `admin`                                          |
| `KEYCLOAK_ADMIN_PASSWORD` | Senha do administrador do Keycloak                                             | `adminpassword`                                  |
| `KEYCLOAK_DB_URL`         | URL JDBC do banco PostgreSQL usado pelo Keycloak                               | `jdbc:postgresql://keycloak-db:5432/FriseurAuth` |
| `CLIENT_ID`               | ID do cliente registrado no Keycloak                                           | `FriseurPlan`                                    |
| `CLIENT_SECRET`           | Segredo do cliente registrado no Keycloak                                      | `KzwIVXQgWYPaE48GEr5GZtE4eNUjhxzX`               |
| `REALM`                   | Nome do Realm usado no Keycloak                                                | `FriseurPlanRealm`                               |