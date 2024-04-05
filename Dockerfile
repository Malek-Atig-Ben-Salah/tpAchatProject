FROM maven AS build
WORKDIR /Application
COPY . .
RUN mvn install -DskipTests

FROM openjdk:17-alpine
WORKDIR /Application
COPY --from=build /Application/target/tpAchatProject-1.0.jar /Application/
EXPOSE 8082
CMD ["java","-jar","tpAchatProject-1.0.jar"]