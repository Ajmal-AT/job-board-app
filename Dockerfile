# Use Maven with Java 8
FROM maven:3.9.9-eclipse-temurin-8-focal AS build

# Create and set the working directory
RUN mkdir -p /job-board-app
WORKDIR /job-board-app

# Copy only the pom.xml first to cache dependencies
COPY pom.xml .

# Download dependencies before copying the rest of the source code
RUN mvn dependency:go-offline -U

# Now copy the source code
COPY . .

# Compile and package the application
RUN mvn -DskipTests clean compile
RUN mvn -DskipTests package

# Runtime image
FROM eclipse-temurin:8-jdk
RUN mkdir -p /job-board-app
COPY --from=build /job-board-app/target/job-board-app-*.jar /job-board-app/job-board-app.jar

ENTRYPOINT ["java", "-jar", "/job-board-app/job-board-app.jar"]
