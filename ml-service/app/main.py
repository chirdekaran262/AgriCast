from fastapi import FastAPI

from app.predict import forecast_prices

app = FastAPI()


@app.get("/predict/{crop_name}")
def predict(crop_name: str):

    result = forecast_prices(crop_name)

    return {
        "crop": crop_name,
        "predictions": result["predictions"],
        "accuracy": result["accuracy"]
    }