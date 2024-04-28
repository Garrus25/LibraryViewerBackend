Docs:
http://localhost:8080/v3/api-docs
http://localhost:8080/swagger-ui/index.html

JVM Arguments:
-Duser.language=en to set hibernate validation error messages to english
-Dspring.profiles.active=dev to disable cors in order to open redoc documentation

Setup env variables:
DATABASE_LOGIN - postgres database login
DATABASE_PASSWORD - postgres database password
DATABASE_URL - postgres database url
DATABASE_SERVER_URL - postgres database server url

LIQUIBASE_COMMAND_PASSWORD - login to liquibase
LIQUIBASE_COMMAND_URL - url to database
LIQUIBASE_COMMAND_USERNAME - username to database

KEYCLOAK_CLIENT_ID - keycloak client id
KEYCLOAK_LOGIN - keycloak login
KEYCLOAK_PASSWORD - keycloak password
KEYCLOAK_REALM - keycloak realm
KEYCLOAK_SERVER_URL - keycloak server url
