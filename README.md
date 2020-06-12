# Backend Challenge

[Teste ze code challenges](https://github.com/ZXVentures/ze-code-challenges/blob/master/backend.md)

## Pré requisito
- Maven 3
- Java 8
- Docker 1.13.0+
- Utlizando projeto lombok (necessária instalação de plugin na IDE)

## Preparando ambiente

```
mvn clean package dockerfile:build 
```

#### Executando container do banco de dados
```
docker run -it \
       -p 5432:5432 \
       --name docker-postgres 
       -e POSTGRES_DB=ze_db 
       -e POSTGRES_USER=postgres 
       -e POSTGRES_PASSWORD=postgres 
       postgis/postgis
```

## Executando com Docker Compose

```
docker-compose up -d
```

## Acessando 

- http://localhost:8080/partners

## Acessando documentação da API Rest
- http://localhost:8080/swagger-ui.html
