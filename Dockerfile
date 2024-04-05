FROM maven AS build
WORKDIR /app
COPY . .
RUN mvn install -DskipTests

FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /app/target/tpAchatProject-1.0.jar /app/
EXPOSE 8082
CMD ["java","-jar","tpAchatProject-1.0.jar"]

