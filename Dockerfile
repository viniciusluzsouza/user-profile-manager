##### build #####
FROM maven:3.8-jdk-11 as builder
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN --mount=type=cache,target=/root/.m2 mvn clean package  -Dmaven.test.skip

##### final #####
FROM openjdk:11
WORKDIR /app

COPY --from=builder /app/target/*.jar ./

EXPOSE 8080
ENV JAVA_OPTS ""
CMD [ "bash", "-c", "java ${JAVA_OPTS} -jar *.jar -v"]
