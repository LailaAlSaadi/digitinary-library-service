## Digitinary Library Management System Spring Boot App

This Maven project as a solid starter project for digitinary library management system

## Environment Setup

You will need to install the following to run the application locally:

* Java 17+
* Maven 3+

## Running the applications

This starter contains one runnable applications: `library`. You can run as any other Spring Boot application
(http://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html).

To install the required dependencies:

```
> mvn clean install
```

### Running the `api` application

The `api` application can be run as a Spring Boot app from the command line:

```
   > mvn spring-boot:run
```

## Tests

### Unit

The unit tests run automatically during the `mvn test` phase as part of the `mvn clean install` lifecycle. To run the
tests without doing a full build, run:

```
mvn test
```

## Digitinary Library Management System

Collection Management Service:

POST: `library/v1/collections/`
add new book/journal.

PUT: `library/collections/{collectionId}`
update book/journal by collectionId.

DELETE: `library/collections/{collectionId}`
delete book/journal by collectionId.

Search Collection Service:

```
GET: library/collections/{collectionId}
```

get collections[book/journal] by collectionId

```
GET: library/collections
```

get all collections[book/journal]

Borrow and Return Service

```
PATCH: /collections/{collectionId}/return
```

return collection[book/journal] by collectionId

```
PATCH: /collections/{collectionId}/borrow
```

borrow collection[book/journal] by collectionId

## Running application with docker

### Create image by following command:

```
docker build --tag=library-service:latest .
```

### Run the service image by following command:

```
docker run -it -p 8090:8090 library-service:latest
```

http://localhost:8090/library/v1/swagger-ui/index.html

You can access the api Swagger docs from http://localhost:8080/api/v2/api-docs
Just copy content to https://editor.swagger.io/
