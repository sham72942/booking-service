# Build Stage
FROM maven:3.8.4-openjdk-11 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of your source code
COPY src ./src
RUN mvn package

# Package Stage
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/BookingService-1.0-SNAPSHOT.jar BookingService-1.0-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "BookingService-1.0-SNAPSHOT.jar"]
