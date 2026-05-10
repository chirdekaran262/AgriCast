import {
    Bell,
    UserCircle2,
    Sprout,
} from "lucide-react";

export default function Navbar() {
    return (
        <header className="bg-green-700 shadow-md">

            <div className="max-w-7xl mx-auto px-6 py-4 flex items-center justify-between">

                {/* Logo */}
                <div className="flex items-center gap-3">

                    <div className="bg-white p-2 rounded-full">
                        <Sprout className="text-green-700" size={24} />
                    </div>

                    <div>
                        <h1 className="text-white text-2xl font-bold">
                            AgriForecast
                        </h1>

                        <p className="text-green-100 text-sm">
                            Smart Crop Selling Assistant
                        </p>
                    </div>

                </div>

                {/* Right section */}
                <div className="flex items-center gap-5">

                    <button className="relative">
                        <Bell className="text-white" size={24} />

                        <span className="absolute -top-1 -right-1 w-3 h-3 bg-red-500 rounded-full"></span>
                    </button>

                    <div className="flex items-center gap-2 bg-green-600 px-3 py-2 rounded-xl">

                        <UserCircle2
                            className="text-white"
                            size={28}
                        />

                        <div className="text-left">
                            <p className="text-white text-sm font-semibold">
                                Farmer
                            </p>

                            <p className="text-green-100 text-xs">
                                Premium Dashboard
                            </p>
                        </div>

                    </div>

                </div>

            </div>

        </header>
    );
}