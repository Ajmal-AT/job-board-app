# Use Maven with Java 8
FROM maven:3.9.9-eclipse-temurin-8-focal AS build

# Set working directory
WORKDIR /job-board-app

# Copy the Maven configuration first (to cache dependencies)
COPY pom.xml .

# Download dependencies before copying the full project
RUN mvn dependency:go-offline -U

# Now copy the source code
COPY . .

# Compile and package the application
RUN mvn -DskipTests clean compile
RUN mvn -DskipTests package

# Use a lightweight JDK for runtime
FROM eclipse-temurin:8-jdk
RUN mkdir -p /job-board-app

# Copy the built JAR file from the build stage
COPY --from=build /job-board-app/target/job-board-app-*.jar /job-board-app/job-board-app.jar

# Start the application
ENTRYPOINT ["java", "-jar", "/job-board-app/job-board-app.jar"]
