# 🏗️ Build Stage
FROM maven:3-eclipse-temurin-8-alpine AS build

# Create and Set Work Directory
RUN mkdir /job-board-app
WORKDIR /job-board-app

# Copy Source Code
COPY . .

# Compile and Package the Application
RUN mvn clean compile
RUN mvn package -DskipTests

# 🚀 Production Stage
FROM eclipse-temurin:8-jre-alpine

# # Create Necessary Directories
# RUN mkdir -p /job-board-app
# RUN mkdir -p /usr/images && chmod 777 /usr/images

# Copy the Built JAR File
COPY --from=build /job-board-app/target/job-board-app.jar /job-board-app/job-board-app.jar

# Set Entry Point
ENTRYPOINT ["java", "-jar", "/job-board-app/job-board-app.jar"]
