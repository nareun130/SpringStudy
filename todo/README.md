--- 
marp : true
---

# Srping Boot Todo 게시판

- Spring Boot, Spring Data JPA, 템ㅍ프릿 엔진(HTML), MySQL
- Docker 이용하여 MySQL 설치

----

## 프로젝트 생성
1. spring.io에서 프로젝트 생성
2. build.gradle 파일 확인
3. SpringBootApplication 실행.
    - DataSource 객체 생성을 위해 url속성이 필요. -> 오류 나면서 종료 

---
## docker 환경 설정
/dev/docker/database/docker-compose.yml 생성

```
version: "1" # 파일 규격 버전

services:
    vacation-db:
        image: mysql:8.0 # 사용 이미지
        restart: always 
        environment:
            MYSQL_ROOT_PASSWORD: "root1234"
            MYSQL_DATABASE: "example"
            MYSQL_USER: "nareun130"
            MYSQL_PASSWORD: "u1234"
        command: # 명령어 실행
            - --character-set-server=utf8mb4
            - --collation-server=utf8mb4_unicode_ci
        volumes: # docker 안에서 실행되는 mysql이 바깥에 database폴더에 마운트 되어 mysql 관련 파일들이 자동 생성 
            - ./database/init/:/docker-entrypoint-initdb.d/
            - ./database/datadir/:/var/lib/mysql #  현재 작업 디렉토리의 database/datadir/ 디렉토리를 컨테이너 내부의 /var/lib/mysql 디렉토리에 마운트하겠다는 것을 의미
        platform: linux/x86_64
        ports: # 외부:docker내부 port 설정
            - 3306:3306

```
docker를 열고 iterm에서 docker-compose.yml 파일 이 있는 경로에서 <code>docker-compose up -d</code> 실행

---
## MYSQL 접속 test
workbench 설치 후 다음 정보 입력
```
defaultSchema:  example
username:   nareun130
password:   u1234
```

---
## SpringDataJPA 설정
```
spring:
    jpa:
        hibernate:
            ddl-auto: update
        properties:
            hibernate:
                show_sql: true
                format_sql: true
        database-platform: org.hibernate.spatial.dialect.myql.MySQL8InnoDBSpatialDialect
```