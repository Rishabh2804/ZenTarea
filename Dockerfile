FROM openjdk:20-jdk
LABEL authors="Rishabh"

EXPOSE 8080

WORKDIR /app

COPY run.sh .
COPY build/libs/ZenTarea-0.0.1-SNAPSHOT.jar /app/zentarea.jar

ENTRYPOINT ["sh", "run.sh"]