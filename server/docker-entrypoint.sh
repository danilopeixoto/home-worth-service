#!/bin/bash

set -eu

variables='${SERVER_HOSTNAME}
          ${SERVER_PORT}
          ${API_HOSTNAME}
          ${API_PORT}'

envsubst "$variables" < /etc/nginx/nginx.conf.template > /etc/nginx/nginx.conf

exec "$@"
