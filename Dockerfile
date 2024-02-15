FROM maven:latest AS builder

WORKDIR /app

COPY .mvn  /.mvn
COPY mvnw pom.xml ./
COPY src ./src

RUN mvn clean package

EXPOSE 5432

FROM openjdk:17-jdk

WORKDIR /app

COPY --from=builder /app/target/taskEasy-0.0.1-SNAPSHOT.jar /app/taskEasy.jar

CMD ["java", "-jar", "taskEasy.jar"]
