# Start with OpenJDK base image
FROM openjdk:17-jdk-slim

# Set app directory
WORKDIR /app

# Copy built jar to container
COPY target/*.jar app.jar

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
