FROM openjdk:17-slim as build

LABEL maintainer="Afikur Rahman <me.afikur@gmail.com>"

ARG JAR_FILE

COPY ${JAR_FILE} app.jar

RUN mkdir -p target/extracted \
    && java -Djarmode=layertools -jar app.jar extract --destination target/extracted

FROM openjdk:17-slim

ARG EXTRACTED=/target/extracted

RUN mkdir /app \
    && groupadd --system --gid 3000 spring \
    && useradd --system --uid 1000 --shell /bin/false --gid spring spring

COPY --from=build ${EXTRACTED}/dependencies/ /app
COPY --from=build ${EXTRACTED}/spring-boot-loader/ /app
COPY --from=build ${EXTRACTED}/snapshot-dependencies/ /app
COPY --from=build ${EXTRACTED}/application/ /app

RUN chown -R spring:spring /app

USER spring

WORKDIR /app

EXPOSE 8080

ENTRYPOINT ["java","org.springframework.boot.loader.JarLauncher"]
