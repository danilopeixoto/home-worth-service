FROM nginx:1.18.0

LABEL description "A home appraisal web server."
LABEL version "1.0.0"
LABEL maintainer "Danilo Peixoto Ferreira"

WORKDIR /usr/bin/server/

COPY docker-entrypoint.sh .
COPY nginx.conf.template /etc/nginx/

RUN chmod +x docker-entrypoint.sh

EXPOSE 80

ENTRYPOINT ["./docker-entrypoint.sh"]
CMD ["nginx", "-g", "daemon off;"]
