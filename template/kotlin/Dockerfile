# Gradle caching workaround
FROM gradle:6.0.1-jdk11 as cache
ENV APP_HOME=/app
RUN mkdir -p /home/gradle/cache_home
ENV GRADLE_USER_HOME=/home/gradle/cache_home
WORKDIR $APP_HOME/entrypoint
COPY function/build.gradle.kts function/settings.gradle.kts $APP_HOME/function/
RUN gradle -version

FROM gradle:6.0.1-jdk11 as build
ENV APP_HOME=/app
WORKDIR $APP_HOME/entrypoint
COPY entrypoint $WORKDIR
COPY model $APP_HOME/model
COPY function $APP_HOME/function
COPY --from=cache /home/gradle/cache_home /home/gradle/.gradle
RUN gradle shadowJar

FROM openfaas/of-watchdog:0.7.3 as watchdog

FROM alpine:3.10 as minimal_jre

RUN apk --no-cache add openjdk11-jdk openjdk11-jmods

RUN /usr/lib/jvm/java-11-openjdk/bin/jlink \
    --verbose \
    --add-modules \
        java.base,java.sql,jdk.httpserver \
    --compress 2 --strip-debug --no-header-files --no-man-pages \
    --output "/opt/java_minimal"

FROM alpine:3.10

ENV JAVA_HOME=/opt/jre
ENV PATH="$PATH:$JAVA_HOME/bin"

WORKDIR /app
COPY --from=minimal_jre /opt/java_minimal $JAVA_HOME
COPY --from=watchdog /fwatchdog /usr/bin/fwatchdog
COPY --from=build /app/entrypoint/build/libs/entrypoint-all.jar .

ENV cgi_headers="true"
ENV fprocess="java -jar /app/entrypoint-all.jar"
ENV mode="http"
ENV upstream_url="http://127.0.0.1:3000"

HEALTHCHECK --interval=5s CMD [ -e /tmp/.lock ] || exit 1

CMD ["fwatchdog"]
