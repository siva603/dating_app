# --- Build stage ---
FROM maven:3.9.0-eclipse-temurin-17-alpine AS build
WORKDIR /app

# Copy project files
COPY pom.xml mvnw .mvn/ ./
COPY src ./src

# Make Maven wrapper executable
RUN chmod +x mvnw

# Build the project
RUN ./mvnw clean package -DskipTests

# --- Runtime stage ---
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy built jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose app port
EXPOSE 8080

# Run the jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
