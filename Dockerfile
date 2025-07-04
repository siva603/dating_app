# --- Build stage ---
FROM maven:3.9.0-eclipse-temurin-17-alpine AS build
WORKDIR /app

# Copy project files
COPY pom.xml mvnw .mvn/ ./
COPY src ./src

# Ensure the wrapper is executable
RUN chmod +x 




# Build without running tests
RUN ./mvnw clean package -DskipTests

# --- Runtime stage ---
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port your app listens on
EXPOSE 8080

# Set the command to start your app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
