#FROM eclipse-temurin:21-jdk-alpine as builder
#VOLUME /tmp
#WORKDIR /app
#RUN apk add --no-cache maven
#COPY . .
#RUN mvn package -DskipTests
#
#
#FROM eclipse-temurin:21-jdk-alpine
#COPY --from=builder /app/target/DiscoveryService.jar .
#EXPOSE 4000
#ENTRYPOINT ["java","-jar","DiscoveryService.jar"]
