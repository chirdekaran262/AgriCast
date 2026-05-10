import pandas as pd

from prophet import Prophet

from sqlalchemy import text

from app.db import engine


def forecast_prices(crop_name: str):

    query = text("""
        SELECT p.date, AVG(p.avg_price) as price
        FROM price p
        JOIN crop c ON p.crop_id = c.id
        WHERE c.name = :crop_name
        GROUP BY p.date
        ORDER BY p.date
    """)

    df = pd.read_sql(
        query,
        engine,
        params={"crop_name": crop_name}
    )

    if len(df) < 3:
        return []

    # prophet format
    df = df.rename(columns={
        "date": "ds",
        "price": "y"
    })

    model = Prophet()

    model.fit(df)
    accuracy = calculate_accuracy(df)
    future =model.make_future_dataframe(periods=7)

    forecast = model.predict(future)

    result = forecast[ ["ds", "yhat", "yhat_lower", "yhat_upper"]  ].tail(7)

    predictions = []

    for _, row in result.iterrows():

        predictions.append({
    "date":
        row["ds"].strftime("%Y-%m-%d"),

    "price":
        round(row["yhat"], 2),

    "lower":
        round(row["yhat_lower"], 2),

    "upper":
        round(row["yhat_upper"], 2),
})
    

    return {
    "predictions": predictions,
    "accuracy": accuracy
}


def calculate_accuracy(df):

    # need enough rows
    if len(df) < 7:
        return 0

    # actual values
    actual = df["y"].tail(7).values

    # simple moving average prediction
    predicted = (
        df["y"]
        .rolling(window=3)
        .mean()
        .tail(7)
        .values
    )

    # remove NaN values
    valid_pairs = []

    for a, p in zip(actual, predicted):

        if pd.notna(p):

            valid_pairs.append((a, p))

    if len(valid_pairs) == 0:
        return 0

    errors = []

    for actual_price, predicted_price in valid_pairs:

        error = abs(
            actual_price - predicted_price
        )

        errors.append(error)

    mean_error = sum(errors) / len(errors)

    mean_actual = (
        sum([x[0] for x in valid_pairs])
        / len(valid_pairs)
    )

    accuracy = (
        100 -
        ((mean_error / mean_actual) * 100)
    )

    return round(accuracy, 2)