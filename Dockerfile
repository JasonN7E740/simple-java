FROM 192.168.146.128:5000/base/java11:tomcat9.0-openjdk11
WORKDIR /devops
COPY devops.jar /devops
ENV JAVA_OPTS="-Xms64m -Xmx128m"
EXPOSE 8080

CMD java -jar devops.jar $JAVA_OPTS -Dspring.profiles.active=production
