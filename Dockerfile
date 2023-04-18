# Stage 1: Build the application with Gradle
FROM gradle:7.1.1-jdk11-hotspot AS build
WORKDIR /app
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle
RUN ./gradlew --no-daemon clean build --stacktrace

COPY . .
RUN ./gradlew --no-daemon clean bootJar --stacktrace

# Stage 2: Package the application into a Docker image
FROM adoptopenjdk:11-jre-hotspot
WORKDIR /app
COPY --from=build /app/build/libs/*.jar ./app.jar
EXPOSE 8081
CMD ["java", "-jar", "app.jar"]
