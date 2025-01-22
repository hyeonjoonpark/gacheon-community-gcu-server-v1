# Gradle 빌드를 위한 베이스 이미지
FROM gradle:7.5.0-jdk17-alpine AS build

# 작업 디렉토리 설정
WORKDIR /home/gradle/project

# 현재 디렉토리의 모든 파일을 컨테이너로 복사
COPY . .

# Gradle 캐시 삭제 후 빌드
RUN rm -rf /home/gradle/.gradle/caches && ./gradlew clean build --no-daemon -x test --info

# 실행을 위한 OpenJDK 베이스 이미지
FROM openjdk:17-alpine

# 작업 디렉토리 설정
WORKDIR /app

# 빌드된 JAR 파일을 복사
COPY --from=build /home/gradle/project/build/libs/gcu-0.0.1-SNAPSHOT.jar /app/gcu.jar

# 애플리케이션 실행 명령어
CMD ["java", "-jar", "/app/gcu.jar"]
