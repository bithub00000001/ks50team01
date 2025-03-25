FROM openjdk:17-jdk-slim AS builder
WORKDIR /app
COPY target/*.jar app.jar

FROM openjdk:17-jre-slim
WORKDIR /app
COPY --from=builder /app/app.jar app.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
