# Use Maven with Java 8 for building the project
FROM maven:3.9.9-eclipse-temurin-8-focal AS build

# Create and set the working directory
RUN mkdir /job-board-app
WORKDIR /job-board-app

# Copy project files into the container
COPY . .

# Clean and compile the project, skipping tests
RUN mvn -DskipTests clean compile

# Package the application
RUN mvn -DskipTests package

# Use a minimal Java 8 runtime environment for the final image
FROM eclipse-temurin:8-jdk

# Create the directory for the application
RUN mkdir -p /job-board-app

# Copy the packaged JAR from the build stage
COPY --from=build /job-board-app/target/job-board-app-*.jar /job-board-app/job-board-app.jar

# Set the entry point to run the application
ENTRYPOINT ["java", "-jar", "/job-board-app/job-board-app.jar"]
