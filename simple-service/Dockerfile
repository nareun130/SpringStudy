FROM openjdk:18.0-slim
# maven은 target
# * gradle은 run Configuration에서 bootJar설정해서 Run 해주고 경로를 다르게 해줘야 한다.
COPY build/libs/*.jar app.jar
EXPOSE 5000
ENTRYPOINT ["java","-jar","/app.jar"]