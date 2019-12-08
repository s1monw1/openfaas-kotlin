# Gradle caching workaround
FROM gradle:6.0.1-jdk11 as cache
ENV APP_HOME /app
RUN mkdir -p /home/gradle/cache_home
ENV GRADLE_USER_HOME /home/gradle/cache_home
WORKDIR $APP_HOME/function
COPY function/build.gradle function/settings.gradle function/gradle.properties $APP_HOME/function/
RUN gradle -version

# Get a build environment
FROM oracle/graalvm-ce:19.2.1 as build-aot
FROM gradle:6.0.1-jdk11 as builder
ENV APP_HOME /app
ENV JAVA_HOME /opt/graalvm-ce-19.2.1/
ENV PATH $JAVA_HOME/bin:$PATH
RUN apt-get update && apt-get install -y gcc zlib1g-dev
COPY --from=build-aot /opt /opt
RUN gu install native-image
COPY --from=cache /home/gradle/cache_home /home/gradle/.gradle
COPY ./function $APP_HOME/function/
WORKDIR $APP_HOME/function

# At this point the native image is built
RUN gradle --stacktrace buildNative

FROM openfaas/of-watchdog:0.7.3 as watchdog

# Create new image from alpine
FROM frolvlad/alpine-glibc:alpine-3.10
RUN apk add --no-cache ca-certificates

RUN addgroup -S app && adduser -S -g app app

RUN apk --no-cache add ca-certificates curl

WORKDIR /root/

RUN mkdir -p /home/app

# Wrapper/boot-strapper
WORKDIR /home/app
# Copy the SUNEC lib from graal to the final image
COPY --from=build-aot /opt/graalvm-ce-19.2.1/jre/lib/amd64/libsunec.so /usr/lib/libsunec.so
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