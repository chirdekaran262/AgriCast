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

    return predictions