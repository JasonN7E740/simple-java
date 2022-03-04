FROM 10.10.12.123:5001/cloudtogo/openjdk:11.0.12-jre-slim-buster
WORKDIR /devops
COPY devops.jar /devops
ENV JAVA_OPTS="-Xms64m -Xmx128m"
EXPOSE 8080

CMD java -jar devops.jar $JAVA_OPTS -Dspring.profiles.active=production
