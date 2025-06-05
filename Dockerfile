FROM eclipse-temurin:17-jdk-jammy

# Установка метаданных образа
LABEL maintainer="123"
LABEL version="0.2.5"
LABEL description="123"


RUN \
echo "**** install packages ****" && \
apt-get update -y && \
echo "**** end packages ****"


COPY build/libs /app


#CMD java -jar /app/subscription-tracker-0.0.1-SNAPSHOT.jar


ENTRYPOINT ["java", "-jar", "/app/subscription-tracker-0.0.1-SNAPSHOT.jar"]



