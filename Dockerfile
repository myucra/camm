FROM eclipse-temurin:17-jre-alpine

#RUN apk --quiet --force-missing-repositories upgrade libcrypto3 libssl3
RUN apk --quiet upgrade libcrypto3 libssl3

COPY account-manage-application/target/*.jar application.jar

COPY ./lib/opentelemetry-javaagent.jar opentelemetry-javaagent.jar

EXPOSE 8080

ENTRYPOINT java -jar -javaagent:/opentelemetry-javaagent.jar application.jar
