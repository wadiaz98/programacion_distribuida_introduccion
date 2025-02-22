# docker build -t app_authors_examen .
# docker build -t docker.io/willan98/app_authors_examen .
# docker push willan98/app_authors_examen

FROM eclipse-temurin:21.0.5_11-jre-alpine
RUN mkdir /app
WORKDIR /app

COPY build/libs/app_authors.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]