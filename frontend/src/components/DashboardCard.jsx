export default function DashboardCard({
    title,
    value,
}) {

    return (
        <div
            style={{
                border: "1px solid #ddd",
                borderRadius: "12px",
                padding: "20px",
                width: "220px",
                boxShadow: "0 2px 8px rgba(0,0,0,0.1)",
                background: "green",
            }}
        >

            <h3>{title}</h3>

            <h2>{value}</h2>

        </div>
    );
}