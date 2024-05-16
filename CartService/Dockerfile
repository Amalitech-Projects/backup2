FROM eclipse-temurin:21-jdk-alpine as builder
VOLUME /tmp
WORKDIR /app
RUN apk add --no-cache maven
COPY . .
RUN mvn package -DskipTests


FROM eclipse-temurin:21-jdk-alpine
COPY --from=builder /app/target/CartService.jar .
EXPOSE 4006
ENTRYPOINT ["java","-jar","CartService.jar"]
