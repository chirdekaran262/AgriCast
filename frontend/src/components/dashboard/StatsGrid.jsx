import DashboardCard from "./DashboardCard";

export default function StatsGrid({
    dashboard,
}) {

    return (

        <div className="grid grid-cols-1 sm:grid-cols-2 xl:grid-cols-5 gap-5 mt-8">

            <DashboardCard
                title="Current Price"
                value={`?${dashboard.currentPrice ?? "N/A"}`}
                subtitle="Latest market price"
            />

            <DashboardCard
                title="AI Recommendation"
                value={dashboard.recommendation ?? "N/A"}
                subtitle="Smart selling suggestion"
            />

            <DashboardCard
                title="Predicted Price"
                value={
                    dashboard.predictedPrice
                        ? `?${dashboard.predictedPrice}`
                        : "N/A"
                }
                subtitle="Expected future price"
            />

            <DashboardCard
                title="Forecast Accuracy"
                value={`${dashboard.predictionAccuracy ?? 0}%`}
                subtitle="Model confidence"
            />

            <DashboardCard
                title="Forecast Range"
                value={
                    dashboard.predictions?.length > 0
                        ? `?${dashboard.predictions[0]?.lower}
               - ?${dashboard.predictions[0]?.upper}`
                        : "N/A"
                }
                subtitle="Expected range"
            />

        </div>
    );
}