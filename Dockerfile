FROM amazoncorretto:17

ENV MAVEN_HOME /usr/share/maven

# Copy
COPY --from=maven:3.9.6-eclipse-temurin-11 ${MAVEN_HOME} ${MAVEN_HOME}
COPY --from=maven:3.9.6-eclipse-temurin-11 /usr/local/bin/mvn-entrypoint.sh /usr/local/bin/mvn-entrypoint.sh
COPY --from=maven:3.9.6-eclipse-temurin-11 /usr/share/maven/ref/settings-docker.xml /usr/share/maven/ref/settings-docker.xml

RUN ln -s ${MAVEN_HOME}/bin/mvn /usr/bin/mvn

ARG MAVEN_VERSION=3.9.6
ARG USER_HOME_DIR="/root"
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

WORKDIR /app
COPY . /app
RUN mvn package
COPY ./src/main/resources/application.properties /app/
RUN cp ./target/project-0.0.1-SNAPSHOT.jar /app/project.jar
CMD java -Dspring.config.location=/app/application.properties -jar project.jar
