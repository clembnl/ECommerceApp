FROM openjdk:17-oracle
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} api.jar
ENTRYPOINT ["java", "-jar", "api.jar"]