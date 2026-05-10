export default function CropSelector({
    crops,
    selectedCrop,
    setSelectedCrop,
    markets,
    selectedMarket,
    setSelectedMarket,
    onCropChange,
    onMarketChange,
}) {

    return (

        <div className="bg-white rounded-2xl shadow-sm border border-gray-100 p-6">

            <div className="grid grid-cols-1 md:grid-cols-2 gap-5">

                {/* Crop Select */}
                <div>

                    <label className="block text-sm font-semibold text-gray-600 mb-2">
                        Select Crop
                    </label>

                    <select
                        value={selectedCrop}
                        onChange={(e) => {

                            const cropId = e.target.value;

                            setSelectedCrop(cropId);

                            onCropChange(cropId);
                        }}
                        className="w-full border border-gray-200 rounded-xl px-4 py-3 focus:outline-none focus:ring-2 focus:ring-green-500"
                    >

                        <option value="">
                            Choose Crop
                        </option>

                        {crops.map((crop) => (

                            <option
                                key={crop.id}
                                value={crop.id}
                            >
                                {crop.name}
                            </option>

                        ))}

                    </select>

                </div>

                {/* Market Select */}
                <div>

                    <label className="block text-sm font-semibold text-gray-600 mb-2">
                        Select Market
                    </label>

                    <select
                        value={selectedMarket}
                        onChange={(e) => {

                            const market = e.target.value;

                            setSelectedMarket(market);

                            onMarketChange(market);
                        }}
                        className="w-full border border-gray-200 rounded-xl px-4 py-3 focus:outline-none focus:ring-2 focus:ring-green-500"
                    >

                        <option value="">
                            All Markets
                        </option>

                        {markets.map((market) => (

                            <option
                                key={market}
                                value={market}
                            >
                                {market}
                            </option>

                        ))}

                    </select>

                </div>

            </div>

        </div>
    );
}