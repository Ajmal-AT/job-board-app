FROM maven:3.9.9-eclipse-temurin-8-alpine AS build
RUN mkdir /job-board-app
WORKDIR /job-board-app
COPY . .
RUN mvn clean compile
#RUN mvn test
RUN mvn package -DskipTests
#
# Package stage
#
FROM eclipse-temurin:8u432-b06-jre-noble
RUN mkdir -p /job-board-app
RUN mkdir -p /usr/images
RUN chmod 777 /usr/images
COPY --from=build /job-board-app/target/job-board-app*.jar /job-board-app/job-board-app*.jar
ENTRYPOINT ["java","-jar","/job-board-app/job-board-app*.jar"]
