FROM openjdk:17-oracle
EXPOSE 8090
ARG JAR_FILE=target/digitinary-library-service-1.0.0-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
