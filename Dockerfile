FROM openjdk:17-jdk-slim
WORKDIR /app

COPY target/test-1.0-SNAPSHOT.jar /app/test.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "test.jar"]