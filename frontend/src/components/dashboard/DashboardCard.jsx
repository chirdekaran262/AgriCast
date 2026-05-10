export default function DashboardCard({
    title,
    value,
    subtitle,
}) {

    return (

        <div className="bg-yellow-100 rounded-2xl shadow-sm border border-gray-100 p-6 hover:shadow-lg transition-all duration-300">

            <p className="text-green-500 text-sm font-medium">
                {title}
            </p>

            <h2 className="text-3xl font-bold text-gray-800 mt-2">
                {value}
            </h2>

            {subtitle && (
                <p className="text-sm text-gray-400 mt-2">
                    {subtitle}
                </p>
            )}

        </div>
    );
}