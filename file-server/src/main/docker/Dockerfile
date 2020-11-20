FROM java:8
VOLUME /tmp
ADD file-server-uranus.jar file-server.jar
RUN bash -c 'touch /file-server.jar'
ENV VM_OPT=""
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom $VM_OPT -jar /file-server.jar