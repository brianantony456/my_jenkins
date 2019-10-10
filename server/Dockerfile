FROM jenkins/jenkins:lts

ENV JENKINS_ENV test

RUN rm -rf /usr/share/jenkins/ref/init.groovy.d/

ADD container_scripts/init.groovy.d /usr/share/jenkins/ref/init.groovy.d/