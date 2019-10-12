FROM jenkins/jenkins:lts

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

ENV JENKINS_ENV test

RUN rm -rf /usr/share/jenkins/ref/init.groovy.d/

ADD container-content/init.groovy.d /usr/share/jenkins/ref/init.groovy.d/