FROM maven as build
WORKDIR /project
COPY . .
RUN mvn install

FROM openjdk:17-alpine
WORKDIR /project
COPY --from=build /project/target/tpAchatProject-1.0.jar /project/
EXPOSE 8082
CMD ["java","-jar","tpAchatProject-1.0.jar"]

