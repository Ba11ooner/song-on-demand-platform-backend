FROM  circleci/jdk8:0.1.1
COPY target/werun-assignment-0.0.1.jar .
CMD ["java","-jar","werun-assignment-0.0.1.jar","--spring.profiles.active=prod"]