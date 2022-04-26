FROM gradle:7.4-jdk11 AS build

LABEL maintainer="Yiyi Kang <yiyikangrachel@gmail.com>"

COPY . /app/kotlinstarter
WORKDIR /app/kotlinstarter
RUN gradle build

FROM gcr.io/distroless/java11-debian11
COPY --from=build /app/kotlinstarter/build/libs/sillycat-kotlin-starter-0.0.1-SNAPSHOT.jar /app/kotlinstarter.jar
WORKDIR /app
ENTRYPOINT ["java", "-Djava.net.preferIPv4Stack=true", "-Dspring.profiles.active=${RUN_ENV:dev}", "-XX:MaxMetaspaceSize=256M", "-jar",  "./kotlinstarter.jar" ]
