# Step 1: Use Gradle 8.11.1 image to build the JAR
FROM gradle:8.11.1-jdk17 AS build
WORKDIR /app
COPY . /app
RUN ./gradlew clean build -Pprofile=local -x test --no-daemon

# Step 2: Use a lightweight JDK image to run the JAR
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
