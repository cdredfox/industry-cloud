FROM registry.cn-shanghai.aliyuncs.com/ym_library/centos-jdk:8u371
LABEL authors="fei.yang"
EXPOSE 5550
ARG ARGS_JVM="-Xms2000m -Xmx2000m -Xss256k -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:MaxGCPauseMillis=200 -XX:+UseG1GC "
ENV ENV_JVM=$ARGS_JVM

ENV MYSQL_HOST=$MYSQL_HOST
ENV MYSQL_USER=$MYSQL_USER
ENV MYSQL_PWD=$MYSQL_PWD

#复制项目jar包
COPY ./target/industry-cloud-0.0.1.jar /data/app/industry-cloud-0.0.1.jar
#启动命令
CMD java -jar ${ENV_JVM}  /data/app/industry-cloud-0.0.1.jar -DMYSQL_HOST=$MYSQL_HOST -DMYSQL_USER=$MYSQL_USER -DMYSQL_PWD=$MYSQL_PWD