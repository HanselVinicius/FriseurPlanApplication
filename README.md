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
| Variable Name             | Description                                            | Example Value                                    |
|---------------------------|--------------------------------------------------------|--------------------------------------------------|
| `POSTGRES_PASSWORD`       | Password for both keycloak and app PostgreSQL user     | `mypassword`                                     |
| `POSTGRES_USER`           | Username for both keycloak and app PostgreSQL database | `friseur_user`                                   |
| `POSTGRES_DB_URL`         | Postgres Db Url                                        | `localhost`                                      |
| `KEYCLOAK_ADMIN`          | Admin username for Keycloak                            | `admin`                                          |
| `KEYCLOAK_ADMIN_PASSWORD` | Admin password for Keycloak                            | `adminpassword`                                  |
| `KEYCLOAK_DB_URL`         | Keycloak db url                                        | `jdbc:postgresql://keycloak-db:5432/FriseurAuth` |
| `CLIENT_ID`               | Keycloak client id                                     | `friseurplan-client`                             |
| `CLIENT_SECRET`           | Keycloak client secret                                 | `asfddahrtgsdgarfeafegea`                        |

## Tests