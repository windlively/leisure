FROM java:8
VOLUME /tmp
ADD file-server-uranus.jar file-server.jar
RUN bash -c 'touch /file-server.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/files-server.jar"]