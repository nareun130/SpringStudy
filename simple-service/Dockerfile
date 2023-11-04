# ! 모든 빌드 프로세스를 docker에서 
FROM gradle:8.4.0-jdk AS build
WORKDIR /home/app
COPY . /home/app/
RUN gradle clean build --no-daemon

FROM openjdk:18.0-slim
EXPOSE 5000
COPY --from=build /home/app/build/libs/*.jar app.jar
ENTRYPOINT [ "sh" , "-c", "java -jar /app.jar"]