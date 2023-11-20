FROM openjdk:17.0.1-jdk-slim as builder
WORKDIR application
ADD target/*.jar user-management.jar
RUN java -Djarmode=layertools -jar user-management.jar extract

FROM openjdk:17.0.1-jdk-slim
LABEL PROJECT_NAME=user-management \
      PROJECT=user-management

EXPOSE 8080

WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "org.springframework.boot.loader.JarLauncher"]

