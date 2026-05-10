import {
    LineChart,
    Line,
    XAxis,
    YAxis,
    Tooltip,
    CartesianGrid,
    ResponsiveContainer,
    Legend,
} from "recharts";

export default function PriceChart({
    history,
    predictions,
}) {

    const historicalData = history.map(item => ({
        date: item.date,
        actualPrice: item.price,
        predictedPrice: null,
    }));

    const predictionData = predictions.map(item => ({
        date: item.date,
        actualPrice: null,
        predictedPrice: item.price,
    }));

    const chartData = [
        ...historicalData,
        ...predictionData,
    ];

    return (

        <div className="bg-white rounded-2xl shadow-sm border border-gray-100 p-6 mt-8">

            <div className="mb-6">

                <h2 className="text-2xl font-bold text-gray-800">
                    Price Forecast Trend
                </h2>

                <p className="text-gray-500 mt-1">
                    Historical prices with AI forecast prediction
                </p>

            </div>

            <div className="w-full h-[450px]">

                <ResponsiveContainer>

                    <LineChart data={chartData}>

                        <CartesianGrid strokeDasharray="3 3" />

                        <XAxis dataKey="date" />

                        <YAxis />

                        <Tooltip />

                        <Legend />

                        <Line
                            type="monotone"
                            dataKey="actualPrice"
                            stroke="#16a34a"
                            strokeWidth={3}
                            dot={false}
                            name="Historical Price"
                        />

                        <Line
                            type="monotone"
                            dataKey="predictedPrice"
                            stroke="#2563eb"
                            strokeDasharray="5 5"
                            strokeWidth={3}
                            dot={false}
                            name="Predicted Price"
                        />

                    </LineChart>

                </ResponsiveContainer>

            </div>

        </div>
    );
}