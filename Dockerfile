FROM frekele/gradle:4.8-jdk8 as build
USER root

RUN mkdir -p /app
WORKDIR /app

ADD . .
RUN gradle build -x test

#------------------------

FROM openjdk:8-jdk-stretch

COPY --from=build /app/build/libs/blaze-demo-api-1.0.jar blazedemo.jar

EXPOSE 16666
ENTRYPOINT ["java", "-jar", "blazedemo.jar"]