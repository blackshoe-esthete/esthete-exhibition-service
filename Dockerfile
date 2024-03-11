# 베이스 이미지 설정
FROM openjdk:17

# 환경 변수 설정
ENV APP_HOME /app

# 작업 디렉토리 설정
WORKDIR $APP_HOME

# JAR 파일을 작업 디렉토리에 추가
ADD ./build/libs/esthete-0.0.1-SNAPSHOT.jar esthete-exhibition-service.jar

# 포트 설정
EXPOSE 8030

# 앱 실행
ENTRYPOINT ["java","-jar","esthete-exhibition-service.jar"]