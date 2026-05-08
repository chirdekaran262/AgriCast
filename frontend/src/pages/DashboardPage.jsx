import { useEffect, useState } from "react";
import API from "../api/api";
import PriceChart from "../components/PriceChart";
import DashboardCard from "../components/DashboardCard";
export default function DashboardPage() {

    const [crops, setCrops] = useState([]);
    const [selectedCrop, setSelectedCrop] = useState("");
    const [dashboard, setDashboard] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");
    // load crops
    useEffect(() => {
        fetchCrops();
    }, []);

    const fetchCrops = async () => {
        const response = await API.get("/crops");
        setCrops(response.data);
    };

    const loadDashboard = async (cropId) => {

        try {

            setLoading(true);

            setSelectedCrop(cropId);

            const response =
                await API.get(`/dashboard/${cropId}`);

            setDashboard(response.data);

        } catch (error) {
            setError("Failed to load dashboard");
            console.error(error);

        } finally {

            setLoading(false);
        }
    };

    return (
        <div style={{ padding: "20px" }}>

            <h1>Farmer Dashboard</h1>

            <select
                value={selectedCrop}
                onChange={(e) => loadDashboard(e.target.value)}
            >
                <option value="">Select Crop</option>

                {crops.map((crop) => (
                    <option key={crop.id} value={crop.id}>
                        {crop.name}
                    </option>
                ))}
            </select>

            {dashboard && (
                <div style={{ marginTop: "20px" }}>

                    {dashboard && (

                        <div style={{ marginTop: "30px" }}>

                            <h1>
                                {dashboard.cropName.toUpperCase()}
                            </h1>

                            <div
                                style={{
                                    display: "flex",
                                    gap: "15px",
                                    marginTop: "20px",
                                    flexWrap: "wrap",
                                }}
                            >

                                <DashboardCard
                                    title="Current Price"
                                    value={`${dashboard.currentPrice}` || 'N\A'}
                                />

                                <DashboardCard
                                    title="Predicted Price"
                                    value={`${dashboard.predictedPrice}` || 'N\A'}
                                />

                                <DashboardCard
                                    title="Recommendation"
                                    value={dashboard.recommendation || 'N\A'}
                                />
                                <DashboardCard
                                    title="Forecast Range"
                                    value={`${dashboard.predictions[0]?.lower}
   - ${dashboard.predictions[0]?.upper}` || 'N\A'}
                                />
                            </div>

                            <PriceChart

                                history={dashboard.history}
                                predictions={dashboard.predictions}

                                predictedPrice={dashboard.predictedPrice}
                                predictedDate={dashboard.predictedDate}
                            />

                        </div>
                    )}

                </div>
            )}
            {loading && <h3>Loading...</h3>}
            {error && <h3>{error}</h3>}
        </div>
    );
}