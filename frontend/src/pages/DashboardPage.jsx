import { useEffect, useState } from "react";

import API from "../api/api";

import Navbar from "../components/layout/Navbar";

import CropSelector from "../components/dashboard/CropSelector";

import StatsGrid from "../components/dashboard/StatsGrid";

import PriceChart from "../components/dashboard/PriceChart";

export default function DashboardPage() {

    const [crops, setCrops] = useState([]);

    const [selectedCrop, setSelectedCrop]
        = useState("");

    const [dashboard, setDashboard]
        = useState(null);

    const [loading, setLoading]
        = useState(false);

    const [error, setError]
        = useState("");

    const [markets, setMarkets]
        = useState([]);

    const [selectedMarket, setSelectedMarket]
        = useState("");

    useEffect(() => {
        fetchCrops();
    }, []);

    const fetchCrops = async () => {

        try {

            const response =
                await API.get("/crops");

            setCrops(response.data);

        } catch (error) {

            console.error(error);

            setError("Failed to load crops");
        }
    };

    const loadMarkets = async (cropId) => {

        try {

            const response =
                await API.get(`/markets/${cropId}`);

            setMarkets(response.data);

        } catch (error) {

            console.error(error);

            setError("Failed to load markets");
        }
    };

    const loadDashboard = async (
        cropId,
        market = ""
    ) => {

        try {

            setLoading(true);

            setError("");

            const response =
                await API.get(
                    `/dashboard/${cropId}?market=${market}`
                );

            setDashboard(response.data);

        } catch (error) {

            console.error(error);

            setError("Failed to load dashboard");

        } finally {

            setLoading(false);
        }
    };

    return (

        <div className="min-h-screen bg-[#bdfea3]">

            <Navbar />

            <div className="max-w-7xl mx-auto px-6 py-8">

                {/* Page Header */}
                <div className="mb-8">

                    <h1 className="text-4xl font-bold text-gray-800">
                        Farmer Market Intelligence
                    </h1>

                    <p className="text-gray-500 mt-2">
                        AI-powered crop price forecasting dashboard
                    </p>

                </div>

                {/* Selector */}
                <CropSelector
                    crops={crops}
                    selectedCrop={selectedCrop}
                    setSelectedCrop={setSelectedCrop}
                    markets={markets}
                    selectedMarket={selectedMarket}
                    setSelectedMarket={setSelectedMarket}

                    onCropChange={(cropId) => {

                        setSelectedMarket("");

                        loadMarkets(cropId);

                        loadDashboard(cropId, "");
                    }}

                    onMarketChange={(market) => {

                        if (selectedCrop) {

                            loadDashboard(
                                selectedCrop,
                                market
                            );
                        }
                    }}
                />

                {/* Loading */}
                {loading && (

                    <div className="mt-10 text-center">

                        <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-green-700 mx-auto"></div>

                        <p className="mt-4 text-gray-500">
                            Loading market insights...
                        </p>

                    </div>
                )}

                {/* Error */}
                {error && (

                    <div className="bg-red-100 text-red-700 p-4 rounded-xl mt-6">
                        {error}
                    </div>
                )}

                {/* Dashboard */}
                {dashboard && !loading && (

                    <>

                        {/* Crop Header */}
                        <div className="mt-10">

                            <h2 className="text-3xl font-bold text-green-700">
                                {dashboard.cropName}
                            </h2>

                            <p className="text-gray-500 mt-1">
                                Smart selling forecast & market analysis
                            </p>

                        </div>

                        {/* Stats */}
                        <StatsGrid dashboard={dashboard} />

                        {/* Chart */}
                        <PriceChart
                            history={dashboard.history}
                            predictions={dashboard.predictions}
                        />

                    </>
                )}

            </div>

        </div>
    );
}