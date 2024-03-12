# Use OpenJDK as the base image
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file into the container
COPY target/movie-catalog-service-0.0.1-SNAPSHOT.jar /app/movie-catalog-service-0.0.1-SNAPSHOT.jar

# Expose the port that your gRPC server listens on
EXPOSE 9090

# Command to run the application
CMD ["java", "-jar", "movie-catalog-service-0.0.1-SNAPSHOT.jar"]
