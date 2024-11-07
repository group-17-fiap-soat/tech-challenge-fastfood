# Start with the official OpenJDK 21 image, slim variant for reduced size
FROM openjdk:21-slim as builder

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle wrapper and project configuration files
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle.kts settings.gradle.kts ./

# Grant permission for gradlew and install dependencies
RUN chmod +x gradlew && ./gradlew dependencies --no-daemon

# Copy the source code into the container
COPY src src

# Build the Spring Boot application
RUN ./gradlew clean build -x test --no-daemon

# Start with a fresh OpenJDK 21 slim image to keep the final image minimal
FROM openjdk:21-slim

# Set environment variables
ENV APP_NAME fastfood-app
ENV APP_JAR /app.jar

# Copy the built JAR file from the builder stage
COPY --from=builder /app/build/libs/*.jar ${APP_JAR}

# Expose the default Spring Boot port
EXPOSE 8080

# Set default command to run the application
CMD ["java", "-jar", "/app.jar"]