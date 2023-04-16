FROM openjdk:18-jdk-alpine
COPY target/app.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]