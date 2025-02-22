# docker build -t app_authors .
# docker build -t docker.io/willan98/app_authors.
# docker push willan98/app_authors

FROM eclipse-temurin:21.0.5_11-jre-alpine
RUN mkdir /app
WORKDIR /app

COPY build/libs/app_authors-1.0-SNAPSHOT.jar app.jar
COPY build/libs/libs /app/libs

EXPOSE 9090

ENTRYPOINT ["java", "-cp", "app.jar:libs/*", "com.programacion.distribuida.authors.Main"]