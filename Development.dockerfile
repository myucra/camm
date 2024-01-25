FROM maven:3.9.4-eclipse-temurin-17-alpine

COPY . .

#RUN export MAVEN_ARGS="-s .m2/settings.xml --batch-mode --quiet --color always -T 2"
ENV MAVEN_ARGS="-s .m2/settings.xml --batch-mode --no-transfer-progress -T 2"
ENV CONFIGURATION_SERVER_URL="http://configuration-server-backend-develop.apps.bisadodev01.grupobisa.net/account-manage-dev.json"

RUN mkdir -p target/config-server
RUN #java -jar ./lib/commons-ci-pipeline-2.1.1.jar -v -j -s assets/schemas/config-server-definition.json -l $CONFIGURATION_SERVER_URL -o target/config-server
RUN #java -jar ./lib/commons-ci-pipeline-2.1.1.jar -w -d . -n application.yml -e \(\\{SPRING_CLOUD_CONFIG_ADDRESS\\}\) -c $CONFIGURATION_SERVER_URL
RUN mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file -Dfile=./lib/commons-lang-2.1.1.jar
RUN mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file -Dfile=./lib/commons-dsl-2.1.1.jar
RUN mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file -Dfile=./lib/commons-spring-2.1.1.jar
RUN mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file -Dfile=./lib/commons-tracing-2.1.1.jar
RUN mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file -Dfile=./lib/commons-tracing-opentelemetry-2.1.1.jar
RUN mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file -Dfile=./lib/commons-as400-2.1.1.jar
RUN mvn dependency:go-offline install -DskipTests -Dspotbugs.skip=true -Dcheckstyle.skip

RUN mvn checkstyle:checkstyle
#
RUN mvn spotbugs:check
#
RUN mvn test
#
#RUN mvn failsafe:integration-test
#
#INNECESARIO
#RUN mvn surefire-report:report
#
#RUN apk --quiet upgrade libcrypto3 libssl3

COPY account-manage-application/target/*.jar application.jar

COPY ./lib/opentelemetry-javaagent.jar opentelemetry-javaagent.jar
#ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar .

EXPOSE 8080

ENTRYPOINT java -jar -javaagent:/opentelemetry-javaagent.jar application.jar
