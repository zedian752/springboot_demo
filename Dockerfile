FROM openjdk:8u111-jre-alpine
WORKDIR /home
ADD ./target/springboot_demo.jar springboot_demo.jar
ENV TZ Asia/Shanghai
EXPOSE 9001
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar springboot_demo.jar"]
