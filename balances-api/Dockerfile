# app-a/Dockerfile (multi-stage)
FROM maven:3.9.9-amazoncorretto-21-alpine AS builder
WORKDIR /workspace
COPY pom.xml mvnw ./
COPY .mvn .mvn
COPY src src
COPY config/checkstyle config/checkstyle
COPY config/pmd config/pmd
RUN ./mvnw -B -DskipTests package

FROM amazoncorretto:21-alpine-jdk AS runtime
ARG JAR_FILE=/workspace/target/*.jar
WORKDIR /app
COPY --from=builder ${JAR_FILE} /app/app.jar
RUN addgroup -S app && adduser -S -G app app
RUN apk add --no-cache curl
RUN rm -f /etc/localtime && \
    ln -s /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime
USER app
ENTRYPOINT ["java","-Duser.timezone=UTC-3", "-jar","/app/app.jar"]
