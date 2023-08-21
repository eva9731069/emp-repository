#從docker hub載入jdk使專案可以編譯
FROM openjdk:8

#將本地主機專案下的 genCode-0.0.1-SNAPSHOT.jar 文件複製到正在創建的 Docker 映像中，並將其重命名為 app.jar
COPY greenBuild-0.0.1-SNAPSHOT.jar  /app.jar

#app.jar使用Java虛擬機執行-jar的指令
CMD ["java", "-jar", "/app.jar"]