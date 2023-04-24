FROM openjdk:18
MAINTAINER "Pattanan Numpong"

VOLUME /build/libs
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]

