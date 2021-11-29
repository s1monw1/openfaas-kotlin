# Gradle caching workaround
FROM quay.io/quarkus/ubi-quarkus-native-image:21.3.0-java11 as builder
USER root

ENV APP_HOME /app
ENV GRADLE_USER_HOME /home/gradle/cache_home

COPY gradlew $APP_HOME/
COPY gradle $APP_HOME/gradle
RUN mkdir -p /home/gradle/cache_home

WORKDIR $APP_HOME
RUN ./gradlew -version

# At this point the native image is built
COPY ./function $APP_HOME/function/
RUN ./gradlew -p function --stacktrace build

FROM openfaas/of-watchdog:0.7.3 as watchdog

# Create new image from alpine
FROM frolvlad/alpine-glibc:alpine-3.10
RUN apk --no-cache add ca-certificates curl libstdc++

RUN addgroup -S app && adduser -S -g app app

WORKDIR /root/

RUN mkdir -p /home/app

# Wrapper/boot-strapper
WORKDIR /home/app
# Copy generated native executable from build-aot
COPY --from=builder /app/function/build/*-runner /usr/bin/function
COPY --from=watchdog /fwatchdog /usr/bin/fwatchdog
USER app

ENV cgi_headers="true"
ENV fprocess="function"
ENV mode="http"
ENV upstream_url="http://127.0.0.1:8000"

ENV exec_timeout="20s"
ENV write_timeout="25s"
ENV read_timeout="25s"

HEALTHCHECK --interval=1s CMD [ -e /tmp/.lock ] || exit 1

CMD ["fwatchdog"]
