FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY . .

COPY /target/*.jar taskEasy.jar

CMD ["java", "-jar", "/target/taskEasy*.jar"]