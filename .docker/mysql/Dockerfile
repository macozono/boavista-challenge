FROM mysql:5.7

RUN apt-get update && apt-get install -y iputils-ping
RUN apt install net-tools

RUN usermod -u 1000 mysql