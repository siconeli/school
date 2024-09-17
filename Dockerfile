FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/dinosaur-api-0.0.1-SNAPSHOT.jar /app/dinosaur-api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "dinosaur-api.jar"]