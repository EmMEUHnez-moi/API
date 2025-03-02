FROM maven:3.9.9-eclipse-temurin-23 as build
COPY pom.xml .
COPY src/ src/
RUN mvn -f pom.xml -Pprod clean package -Dmaven.test.skip=true

FROM eclipse-temurin:23-jre as run
RUN useradd user
USER user
COPY --from=build /target/EmMEUHnezmoi-API.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]