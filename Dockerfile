FROM amazoncorretto:11 AS builder
COPY . /
RUN ./gradlew clean build

FROM amazoncorretto:11
COPY --from=builder /build/libs/challenge-0.0.1-SNAPSHOT.jar /build/libs/challenge-0.0.1-SNAPSHOT.jar
WORKDIR /build/libs

EXPOSE 8080

CMD ["sh", "-c", "java -jar -Dspring.profiles.active=local challenge-0.0.1-SNAPSHOT.jar"]
