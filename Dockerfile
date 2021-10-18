##### build #####
FROM maven:3.8-jdk-11 as builder
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN --mount=type=cache,target=/root/.m2 mvn clean package  -Dmaven.test.skip

##### final #####
FROM adoptopenjdk/openjdk11:jre-11.0.12_7-alpine
RUN mkdir /app
WORKDIR /app

COPY --from=builder /app/target/userprofile-manager.jar /app

CMD [ "java", "-Dserver.port=45800", "-jar", "userprofile-manager.jar", "-v" ]
