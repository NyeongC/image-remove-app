from fastapi import FastAPI, UploadFile, File
from fastapi.responses import Response
from rembg import remove
import uvicorn

app = FastAPI()


@app.get("/health")
def health():
    return {"status": "ok"}


@app.post("/process")
async def remove_background(image: UploadFile = File(...)):
    input_bytes = await image.read()

    output_bytes = remove(input_bytes)

    return Response(
        content=output_bytes,
        media_type="image/png"
    )


if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=5000)