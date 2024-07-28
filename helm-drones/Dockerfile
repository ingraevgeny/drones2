FROM dock999evg/alp-ja:net
VOLUME /tmp
COPY maven/drones-jar/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]