# Use Eclipse Temurin JRE 21 as base image
FROM eclipse-temurin:21-jre

# Set working directory
WORKDIR /app

# Copy the JAR file
COPY target/*.jar app.jar

# Expose port
EXPOSE 8080

# Run the application with docker profile
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "app.jar"]
