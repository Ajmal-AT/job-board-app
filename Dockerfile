FROM maven:3.9.9-eclipse-temurin-8-focal AS build
RUN mkdir /job-board-app
WORKDIR /job-board-app
COPY . .
RUN mvn -DskipTests clean compile
RUN mvn -DskipTests package

# Package stage

FROM eclipse-temurin:latest
RUN mkdir -p /job-board-app
# RUN mkdir -p /usr/images
# RUN chmod 777 /usr/images
COPY --from=build /job-board-app/target/job-board-app-*.jar /job-board-app/job-board-app.jar
ENTRYPOINT ["java","-jar","/job-board-app/job-board-app.jar"]

