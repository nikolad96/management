FROM amazoncorretto:21-alpine3.19
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} management-0.0.1.jar
ENTRYPOINT ["java","-jar","/management-0.0.1.jar"]