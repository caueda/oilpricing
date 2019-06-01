FROM openjdk

VOLUME /tmp
ADD maven/oilpricing-0.0.1-SNAPSHOT.jar oilpricing.jar
RUN sh -c 'touch /oilpricing.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/oilpricing.jar"]
