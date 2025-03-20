FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/reminder-service-1.0.0.0.jar reminder-service-1.0.0.0.jar
EXPOSE 8080
CMD ["java", "-jar", "reminder-service-1.0.0.0.jar"]