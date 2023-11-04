# # ! docker는 레이어링을 사용한다. -> 변경 사항 x 재사용되고 다음 단계에서 발생할 일이 없게 만들수 있다.
# FROM gradle:8.4.0-jdk AS build
# WORKDIR /home/app

# # * 변동 사항이 거의 없는 의존성 설정파일이나 Application.java 파일을 재사용 가능하게 설정
# COPY ./build.gradle home/app/build.gradle
# COPY ./src/main/java/com/nareun130/simpleservice/SimpleServiceApplication.java //home/app/src/main/java/com/nareun130/simpleservice/SimpleServiceApplication.java

# # ? 여기서 모든 의존성을 다운로드한다.
# # RUN gradle clean build --no-daemon  #? 실행 안됨.
# # https://findstar.pe.kr/2022/05/13/gradle-docker-cache/ 
# RUN gradle build -x test --parallel --continue > /dev/null 2>&1 || true
# # ! docker는 모든 레이어를 캐시하고, 그것을 재사용하려한다.

# # ~> 아무런 사항도 변경하지 않는다면 여기까지 5개의 레이어가 재사용되고 다음 단계에서 발생하는 일은 밑의 2개만 처리 
# COPY . /home/app/
# RUN gradle clean build --no-daemon


# FROM openjdk:18.0-slim
# EXPOSE 5000
# COPY --from=build /home/app/build/libs/*.jar app.jar
# ENTRYPOINT [ "sh" , "-c", "java -jar /app.jar"]

# * ./gradlew  bootBuildImage 로도 생성 가능!!