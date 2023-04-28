# syntax=docker/dockerfile:1

FROM eclipse-temurin:17-jdk-alpine
VOLUME [ "/tmp" ]
ARG WAR_FILE="build/libs/*.war"
COPY ${WAR_FILE} app.war
ENTRYPOINT [ "java", "-jar", "/app.war" ]
