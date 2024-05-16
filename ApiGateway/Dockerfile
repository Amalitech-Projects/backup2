FROM eclipse-temurin:21-jdk-alpine as builder
VOLUME /tmp
WORKDIR /app
RUN apk add --no-cache maven
COPY . .
RUN mvn package -DskipTests


FROM eclipse-temurin:21-jdk-alpine
EXPOSE 4001
COPY --from=builder /app/target/ApiGateway.jar .
ENTRYPOINT ["java","-jar","ApiGateway.jar"]