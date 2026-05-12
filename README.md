# Image Remove App

## Architecture

- Vue
- Spring Boot
- RabbitMQ
- Python Worker
- Nginx
- OCI

```
이미지 제거 앱/
│
├─ frontend/
├─ backend/
├─ worker/
├─ nginx/
└─ README.md
```

도메인: edgecut.xyz

https 설정

certbot 인증서 발급
sudo apt install certbot python3-certbot-nginx
sudo certbot--nginx