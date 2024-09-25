FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /desafio
COPY pom.xml .
COPY /src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk
WORKDIR /desafio
COPY --from=build desafio/target/desafio-0.0.1-SNAPSHOT.jar desafio.jar
ENTRYPOINT ["java", "-jar", "desafio.jar"]