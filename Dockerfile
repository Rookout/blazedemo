FROM frekele/gradle:4.8-jdk8 as build
USER root

RUN mkdir -p /app
WORKDIR /app

ADD . .
RUN gradle build -x test

#------------------------

FROM openjdk:8-jdk-stretch

COPY --from=build /app/build/libs/blaze-demo-api-1.0.jar blazedemo.jar
RUN wget "http://repository.sonatype.org/service/local/artifact/maven/redirect?r=central-proxy&g=com.rookout&a=rook&v=0.1.61" -O rook.jar

EXPOSE 16666
ENTRYPOINT ["java", "-javaagent:rook.jar", "-jar", "blazedemo.jar"]