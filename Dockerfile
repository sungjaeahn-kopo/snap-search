FROM gradle:8.11.1-jdk17 AS build
WORKDIR /app
COPY . /app
RUN ./gradlew clean build -Pprofile=local -x test --no-daemon

FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
