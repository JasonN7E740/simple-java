FROM yaolianhua/java11-runtime:latest
WORKDIR /devops
COPY thymeleaf-fragments.jar /devops
ENV JAVA_OPTS="-Xms64m -Xmx128m"
EXPOSE 8080

CMD java -jar devops.jar $JAVA_OPTS -Dspring.profiles.active=production
