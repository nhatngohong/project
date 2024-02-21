FROM openjdk:17-jdk-slim
WORKDIR /app
COPY . .
RUN mvn package
CMD java --jar project.jar