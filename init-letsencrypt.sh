#!/bin/bash

domains=(trip79.duckdns.org)
email="dmdm9218@naver.com"
data_path="./nginx/ssl"

# 디렉토리 생성
mkdir -p "$data_path"

# 가짜 인증서 생성
for domain in "${domains[@]}"; do
  mkdir -p "$data_path/live/$domain"
  openssl req -x509 -nodes -newkey rsa:2048 -days 1 \
    -keyout "$data_path/live/$domain/privkey.pem" \
    -out "$data_path/live/$domain/fullchain.pem" \
    -subj "/CN=$domain"
done

# 도커 컴포즈 시작
docker-compose up -d

# 실제 인증서 발급
for domain in "${domains[@]}"; do
  docker-compose run --rm certbot certonly --webroot \
    --webroot-path=/var/www/certbot \
    --email $email --agree-tos --no-eff-email \
    -d $domain
done

# Nginx 재시작
docker-compose exec nginx nginx -s reload
