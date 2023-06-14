FROM bellsoft/liberica-openjdk-alpine:17 AS builder
WORKDIR /build
ADD . /build/
RUN ./mvnw -DskipTests package
#RUN ./mvnw -DskipTests package -s settings.xml


FROM bellsoft/liberica-openjdk-alpine:17
WORKDIR /demo
COPY --from=builder /build/target/demo.jar demo.jar
ENV TZ=Europe/Moscow

CMD ["java", "-jar", "demo.jar"]


# podman build -f src/main/docker/Dockerfile -t demo .
