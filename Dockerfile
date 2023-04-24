FROM openjdk:18-jdk-slim
# If need alpine version, use this openjdk:18-jdk-alpine
# Note: alpine version is not working with M1 Mac (Apple Silicon)
# For Linux, use this openjdk:18-jdk-alpine 


ARG JAR_FILE=out/AirbnbBackend-1.0.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","/app.jar"]