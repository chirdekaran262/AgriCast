import {
    LineChart,
    Line,
    XAxis,
    YAxis,
    Tooltip,
    CartesianGrid,
    ResponsiveContainer,
} from "recharts";

export default function PriceChart({
    history,
    predictions,
}) {

    // historical data
    const historicalData = history.map(item => ({
        date: item.date,
        actualPrice: item.price,
        predictedPrice: null,
    }));

    // future prediction data
    const predictionData = predictions.map(item => ({
        date: item.date,
        actualPrice: null,
        predictedPrice: item.price,
    }));

    // merge both
    const chartData = [
        ...historicalData,
        ...predictionData,
    ];

    // trend color
    let trendColor = "#f59e0b";

    if (history.length >= 2) {

        const latest =
            history[history.length - 1].price;

        const previous =
            history[history.length - 2].price;

        if (latest > previous) {
            trendColor = "#22c55e";
        } else if (latest < previous) {
            trendColor = "#ef4444";
        }
    }

    return (
        <div
            style={{
                width: "100%",
                height: 450,
                marginTop: "40px",
            }}
        >

            <h2>Forecast Trend</h2>

            <ResponsiveContainer>

                <LineChart data={chartData}>

                    <CartesianGrid strokeDasharray="3 3" />

                    <XAxis dataKey="date" />

                    <YAxis />

                    <Tooltip />

                    {/* historical */}
                    <Line
                        type="monotone"
                        dataKey="actualPrice"
                        stroke={trendColor}
                        strokeWidth={3}
                        dot={false}
                    />

                    {/* future prediction */}
                    <Line
                        type="monotone"
                        dataKey="predictedPrice"
                        stroke="#3b82f6"
                        strokeDasharray="5 5"
                        strokeWidth={3}
                        dot={false}
                    />

                </LineChart>

            </ResponsiveContainer>

        </div>
    );
}