import json
import os

import boto3
import pika
import requests
from botocore.client import Config
from rembg import remove


RABBITMQ_HOST = os.getenv("WORKER_RABBITMQ_HOST")
RABBITMQ_PORT = int(os.getenv("WORKER_RABBITMQ_PORT"))
RABBITMQ_USER = os.getenv("WORKER_RABBITMQ_USER")
RABBITMQ_PASSWORD = os.getenv("RABBITMQ_PASSWORD")
QUEUE_NAME = os.getenv("IMAGE_QUEUE")

R2_ENDPOINT = os.getenv("R2_ENDPOINT")
R2_ACCESS_KEY = os.getenv("R2_ACCESS_KEY")
R2_SECRET_KEY = os.getenv("R2_SECRET_KEY")
R2_BUCKET = os.getenv("R2_BUCKET")
R2_PUBLIC_URL = os.getenv("R2_PUBLIC_URL")


def validate_env():
    required = [
        "WORKER_RABBITMQ_HOST",
        "WORKER_RABBITMQ_PORT",
        "WORKER_RABBITMQ_USER",
        "RABBITMQ_PASSWORD",
        "IMAGE_QUEUE",
        "R2_ENDPOINT",
        "R2_ACCESS_KEY",
        "R2_SECRET_KEY",
        "R2_BUCKET",
        "R2_PUBLIC_URL",
    ]

    missing = [key for key in required if not os.getenv(key)]

    if missing:
        raise RuntimeError(f"필수 환경변수가 없습니다: {', '.join(missing)}")


def create_s3_client():
    return boto3.client(
        "s3",
        endpoint_url=R2_ENDPOINT,
        aws_access_key_id=R2_ACCESS_KEY,
        aws_secret_access_key=R2_SECRET_KEY,
        config=Config(signature_version="s3v4"),
        region_name="auto",
    )


def make_result_key(original_key: str) -> str:
    file_name = original_key.split("/")[-1]
    name_without_ext = file_name.rsplit(".", 1)[0]
    return f"processed/{name_without_ext}.png"


def download_image(original_url: str) -> bytes:
    response = requests.get(original_url, timeout=60)
    response.raise_for_status()
    return response.content


def upload_result_image(s3_client, result_key: str, output_bytes: bytes) -> str:
    s3_client.put_object(
        Bucket=R2_BUCKET,
        Key=result_key,
        Body=output_bytes,
        ContentType="image/png",
    )

    return f"{R2_PUBLIC_URL.rstrip('/')}/{result_key}"


def handle_message(s3_client, message: dict):
    original_key = message["originalKey"]
    original_url = message["originalUrl"]

    print(f"작업 시작. originalKey={original_key}, originalUrl={original_url}")

    input_bytes = download_image(original_url)
    output_bytes = remove(input_bytes)

    result_key = make_result_key(original_key)
    result_url = upload_result_image(s3_client, result_key, output_bytes)

    print(f"작업 완료. resultKey={result_key}, resultUrl={result_url}")


def main():
    validate_env()

    s3_client = create_s3_client()

    credentials = pika.PlainCredentials(
        RABBITMQ_USER,
        RABBITMQ_PASSWORD
    )

    connection = pika.BlockingConnection(
        pika.ConnectionParameters(
            host=RABBITMQ_HOST,
            port=RABBITMQ_PORT,
            credentials=credentials,
            heartbeat=600,
            blocked_connection_timeout=300,
        )
    )

    channel = connection.channel()
    channel.queue_declare(queue=QUEUE_NAME, durable=True)
    channel.basic_qos(prefetch_count=1)

    def callback(ch, method, properties, body):
        try:
            message = json.loads(body.decode("utf-8"))
            print(f"메시지 수신: {message}")

            handle_message(s3_client, message)

            ch.basic_ack(delivery_tag=method.delivery_tag)

        except Exception as e:
            print(f"메시지 처리 실패: {e}")
            ch.basic_nack(delivery_tag=method.delivery_tag, requeue=False)

    channel.basic_consume(
        queue=QUEUE_NAME,
        on_message_callback=callback,
        auto_ack=False,
    )

    print(f"Consumer 시작. queue={QUEUE_NAME}, rabbitmqHost={RABBITMQ_HOST}")
    channel.start_consuming()


if __name__ == "__main__":
    main()