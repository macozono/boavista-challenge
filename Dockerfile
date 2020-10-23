FROM openjdk:8-jdk-alpine

RUN apk add bash
RUN apk add --no-cache shadow
RUN apk add --no-cache openssl

ENV DOCKERIZE_VERSION v0.6.1
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz

WORKDIR /var/www

#RUN chown -R www-data:www-data /var/www

#RUN usermod -u 1000 www-data
#USER www-data

EXPOSE 8080

#ENTRYPOINT ["php-fpm"]
