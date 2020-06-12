FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/code-challenge-*.jar code-challenge.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=docker", "-jar", "/code-challenge.jar"]