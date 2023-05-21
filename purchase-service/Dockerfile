FROM openjdk:17-jdk-slim
COPY build/libs/purchase-service-0.0.1-SNAPSHOT.jar purchase-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/purchase-service.jar"]
