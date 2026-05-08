<div align="center">

# 🌾 AgriCast

### AI-Powered Crop Price Forecasting & Analytics Platform

*A full-stack platform helping farmers understand crop market trends, visualize historical prices, and get AI-driven 7-day price forecasts.*

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![FastAPI](https://img.shields.io/badge/FastAPI-0.100+-009688?style=for-the-badge&logo=fastapi&logoColor=white)](https://fastapi.tiangolo.com/)
[![React](https://img.shields.io/badge/React-18+-61DAFB?style=for-the-badge&logo=react&logoColor=black)](https://react.dev/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15+-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Prophet](https://img.shields.io/badge/Prophet-Facebook-0866FF?style=for-the-badge&logo=meta&logoColor=white)](https://facebook.github.io/prophet/)
[![Python](https://img.shields.io/badge/Python-3.10+-3776AB?style=for-the-badge&logo=python&logoColor=white)](https://www.python.org/)

---

> ⚠️ **Status: Active Development — Early Prototype**
> AgriCast is currently under active development. Core architecture is in place and being built incrementally. This README reflects both the current working state and the planned roadmap.

</div>

---

## 📑 Table of Contents

- [Project Overview](#-project-overview)
- [Current Status](#-current-status)
- [System Architecture](#-system-architecture)
- [Tech Stack](#-tech-stack)
- [Folder Structure](#-folder-structure)
- [What's Working Now](#-whats-working-now)
- [API Endpoints](#-api-endpoints)
- [Installation](#-installation)
- [Running Locally](#-running-locally)
- [Screenshots](#-screenshots)
- [Roadmap](#-roadmap)
- [Tech Decisions](#-tech-decisions)
- [Learning Outcomes](#-learning-outcomes)
- [Author](#-author)

---

## 🌱 Project Overview

AgriCast is a full-stack platform that collects daily crop market price data, stores it in PostgreSQL via a Spring Boot backend, runs time-series forecasting using Facebook Prophet (served via FastAPI), and displays everything on a React dashboard.

The goal is to give farmers and agri-traders a simple, data-driven tool to:
- View historical crop price trends
- Get AI-powered 7-day price forecasts
- Receive SELL / HOLD / WAIT recommendations based on predicted price direction

This is being built as a **real, working product** — not a demo. The architecture is designed to scale as more data and features are added over time.

---

## 📍 Current Status

> This project is in its **early prototype phase**. The core pipeline is being wired up end-to-end before expanding features.

### ✅ Done
- [x] Project architecture designed and folder structure set up
- [x] Spring Boot backend initialized with PostgreSQL connection
- [x] CSV ingestion API — upload daily crop price CSVs into the database
- [x] Basic crop price fetch API — retrieve stored prices by crop name
- [x] FastAPI ML service initialized with Prophet installed
- [x] Prophet model running on small dataset (3–4 days of data currently)
- [x] 7-day forecast endpoint working (prototype — accuracy improves with more data)
- [x] React dashboard scaffolded and connecting to backend APIs

### 🔧 In Progress
- [ ] Crop selection dropdown wired to live API data
- [ ] Historical price chart rendering with Recharts
- [ ] Forecast chart displaying Prophet output
- [ ] SELL / HOLD / WAIT recommendation logic
- [ ] More crop price data being collected daily

### 📋 Planned (Roadmap)
- See [Roadmap](#-roadmap) section below

> **Note on forecast accuracy:** Prophet requires weeks-to-months of historical data for reliable predictions. The current 7-day forecast is functional but will become significantly more accurate as daily data accumulates. This is expected and by design.

---

## 🏗️ System Architecture

```
┌─────────────────────────────────────────────────────────────────────┐
│                         AgriCast Platform                           │
├─────────────────────────────────────────────────────────────────────┤
│                                                                     │
│   ┌──────────────┐     ┌──────────────┐     ┌──────────────────┐   │
│   │ Python       │     │  CSV Files   │     │  Spring Boot     │   │
│   │ Scraper      │────▶│  (Raw Data)  │────▶│  REST API        │   │
│   │              │     │              │     │  (Port 8080)     │   │
│   └──────────────┘     └──────────────┘     └────────┬─────────┘   │
│                                                       │             │
│                                              ┌────────▼─────────┐   │
│                                              │   PostgreSQL DB   │   │
│                                              │  (crop_prices)   │   │
│                                              └────────┬─────────┘   │
│                                                       │             │
│   ┌──────────────────┐                      ┌────────▼─────────┐   │
│   │  React Dashboard │◀─────────────────────│  Spring Boot     │   │
│   │  (Port 3000)     │                      │  REST API        │   │
│   │                  │                      └────────┬─────────┘   │
│   │  • Price Charts  │                               │             │
│   │  • Forecasts     │     ┌─────────────────────────▼───────┐    │
│   │  • Recommends    │◀────│  FastAPI ML Service              │    │
│   └──────────────────┘     │  (Port 8000)                    │    │
│                             │  Prophet Forecasting Engine     │    │
│                             └─────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────────────┘
```

**Data Flow:**
```
Python Scraper → CSV → Spring Boot (Ingest) → PostgreSQL
                                                   │
                                         FastAPI reads via SQLAlchemy
                                                   │
                                          Prophet trains & forecasts
                                                   │
                                        Spring Boot proxies result
                                                   │
                                          React Dashboard renders
```

---

## 🛠️ Tech Stack

| Layer | Technology | Purpose |
|---|---|---|
| Backend API | Spring Boot 3.x | REST APIs, data ingestion, business logic |
| ORM | Spring Data JPA | PostgreSQL interaction |
| Database | PostgreSQL | Crop price storage |
| ML Service | FastAPI | Serves Prophet forecasts via REST |
| Forecasting | Facebook Prophet | Time-series price prediction |
| Data Processing | Pandas + SQLAlchemy | Data prep for Prophet |
| Frontend | React 18 | Dashboard UI |
| Charts | Recharts | Price & forecast visualizations |
| HTTP Client | Axios | API calls from React |
| Scraper | Python | CSV data collection |
| Build Tool | Maven | Java dependency management |

---

## 📁 Folder Structure

```
agricast/
│
├── scraper/                        # Python data collection scripts
│   ├── scrape_prices.py
│   ├── requirements.txt
│   └── data/                       # CSV files (gitignored)
│
├── backend/                        # Spring Boot application
│   ├── src/main/java/com/agricast/
│   │   ├── controller/             # REST controllers
│   │   ├── service/                # Business logic
│   │   ├── repository/             # JPA repositories
│   │   ├── model/                  # Entity classes
│   │   └── dto/                    # Request/Response DTOs
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
│
├── ml-service/                     # FastAPI ML microservice
│   ├── main.py
│   ├── forecaster.py               # Prophet logic
│   ├── database.py                 # SQLAlchemy setup
│   ├── models.py
│   ├── schemas.py
│   └── requirements.txt
│
├── frontend/                       # React dashboard
│   ├── src/
│   │   ├── components/
│   │   ├── services/api.js
│   │   └── App.jsx
│   └── package.json
│
├── docker-compose.yml
├── .env.example
├── .gitignore
└── README.md
```

---

## 🔨 What's Working Now

### 1. CSV Ingestion
Upload a CSV file with crop price data and it gets stored in PostgreSQL.

```bash
curl -X POST http://localhost:8080/api/crops/ingest \
  -F "file=@data/crop_prices_2024-11-14.csv"
```

Expected CSV format:
```
crop_name,market,state,price,recorded_date
Tomato,Pune,Maharashtra,1800.00,2024-11-14
Onion,Nashik,Maharashtra,1200.00,2024-11-14
```

### 2. Fetch Crop Prices
```bash
GET http://localhost:8080/api/crops/tomato/prices
```

### 3. 7-Day Forecast (Prototype)
```bash
GET http://localhost:8080/api/forecast/tomato
```

> ⚠️ With only 3–4 days of data, Prophet's forecast is a working proof-of-concept. Prediction quality improves significantly with 30+ days of data. Daily data collection is ongoing.

### Database Table

```sql
CREATE TABLE crop_prices (
    id            BIGSERIAL PRIMARY KEY,
    crop_name     VARCHAR(100) NOT NULL,
    market        VARCHAR(150),
    state         VARCHAR(100),
    price         DECIMAL(10, 2) NOT NULL,
    recorded_date DATE NOT NULL,
    created_at    TIMESTAMP DEFAULT NOW()
);
```

---

## 🔌 API Endpoints

### Spring Boot — `http://localhost:8080/api`

| Method | Endpoint | Status | Description |
|---|---|---|---|
| `POST` | `/crops/ingest` | ✅ Working | Upload CSV and ingest prices |
| `GET` | `/crops` | ✅ Working | List all available crops |
| `GET` | `/crops/{name}/prices` | ✅ Working | Get historical prices |
| `GET` | `/forecast/{name}` | 🔧 In Progress | 7-day forecast + recommendation |
| `GET` | `/crops/{name}/prices?from=&to=` | 📋 Planned | Date range filter |

### FastAPI ML Service — `http://localhost:8000`

| Method | Endpoint | Status | Description |
|---|---|---|---|
| `GET` | `/health` | ✅ Working | Health check |
| `POST` | `/predict/{cropName}` | 🔧 In Progress | Prophet forecast |
| `GET` | `/docs` | ✅ Working | Auto Swagger UI |

### Example Response — Forecast (target shape)

```json
{
  "cropName": "tomato",
  "forecast": [
    { "date": "2024-11-15", "predicted": 2250.00, "lower": 2100.00, "upper": 2400.00 },
    { "date": "2024-11-16", "predicted": 2300.00, "lower": 2140.00, "upper": 2460.00 }
  ],
  "recommendation": "HOLD",
  "message": "Price is rising. Hold for better returns."
}
```

---

## 🚀 Installation

### Prerequisites

| Tool | Version |
|---|---|
| Java (JDK) | 17+ |
| Maven | 3.8+ |
| Python | 3.10+ |
| Node.js | 18+ |
| PostgreSQL | 15+ |

### 1. Clone the Repo

```bash
git clone https://github.com/yourusername/agricast.git
cd agricast
```

### 2. Set Up PostgreSQL

```sql
CREATE DATABASE agricast_db;
CREATE USER agricast_user WITH PASSWORD 'your_password';
GRANT ALL PRIVILEGES ON DATABASE agricast_db TO agricast_user;
```

### 3. Configure `.env`

```bash
cp .env.example .env
# Fill in your DB credentials and service URLs
```

---

## 🖥️ Running Locally

### Spring Boot Backend
```bash
cd backend
mvn spring-boot:run
# http://localhost:8080
```

### FastAPI ML Service
```bash
cd ml-service
python -m venv venv
source venv/bin/activate
pip install -r requirements.txt
uvicorn main:app --reload --port 8000
# http://localhost:8000
# Swagger: http://localhost:8000/docs
```

### React Frontend
```bash
cd frontend
npm install
npm start
# http://localhost:3000
```

---

## 📸 Screenshots

> Will be added as the dashboard UI is completed.

| View | Preview |
|---|---|
| Dashboard — Historical Chart | *Coming soon* |
| 7-Day Forecast Chart | *Coming soon* |
| Recommendation Card | *Coming soon* |

---

## 🗺️ Roadmap

### Phase 1 — Core Pipeline *(In Progress)*
- [x] Spring Boot + PostgreSQL setup
- [x] CSV ingestion API
- [x] FastAPI + Prophet forecast (prototype)
- [ ] React dashboard — charts rendering
- [ ] Recommendation logic (SELL / HOLD / WAIT)
- [ ] Collect 30+ days of price data

### Phase 2 — Dashboard & UX
- [ ] Crop selector dropdown from live API
- [ ] Historical price chart with trend colors
- [ ] Forecast chart with confidence bands
- [ ] Date range filtering
- [ ] Mobile-responsive layout

### Phase 3 — Data & ML Improvements
- [ ] Automate daily data collection (cron / Airflow)
- [ ] Multi-market price aggregation
- [ ] Improve forecast accuracy with 90+ days of data
- [ ] Anomaly detection for sudden price spikes
- [ ] Weather data correlation

### Phase 4 — Platform Features
- [ ] Farmer login (JWT auth)
- [ ] Price alert notifications (email / SMS)
- [ ] Multi-language support (Hindi, Marathi)
- [ ] Agmarknet / eNAM API integration

### Phase 5 — Infrastructure
- [ ] Docker Compose for all services
- [ ] CI/CD with GitHub Actions
- [ ] Cloud deployment (AWS / Railway / Render)
- [ ] Redis caching for forecast results

---

## 💡 Tech Decisions

### Why Spring Boot for the backend?
Java + Spring Boot is the industry standard for building reliable REST APIs at scale. It made sense for the core backend given its mature ecosystem, Spring Data JPA for clean database interaction, and future extensibility (Spring Security, Spring Batch for scheduled ingestion, etc.).

### Why FastAPI for the ML service?
The ML service is Python-only because Prophet, Pandas, and SQLAlchemy are all Python libraries. FastAPI is the best fit here — it's async, lightweight, and generates Swagger docs automatically. Keeping ML in a separate microservice also means it can be scaled or swapped independently of the main backend.

### Why Prophet over LSTM or other deep learning models?
| Factor | Prophet | LSTM |
|---|---|---|
| Data needed | Works with small datasets | Needs months/years of data |
| Interpretability | Shows trend + seasonality clearly | Black box |
| Setup | No GPU, easy to deploy | Needs GPU, complex setup |
| Iteration speed | Fast to experiment | Slow to retrain |

Prophet is the right choice for this stage. When enough historical data is accumulated, LSTM or NeuralProphet can be evaluated as an upgrade.

---

## 📚 Learning Outcomes

| Area | What I'm Learning |
|---|---|
| Microservices | Separating ML concerns from business logic with clean API contracts |
| Time-Series ML | How Prophet handles trend, seasonality, and uncertainty |
| Full-Stack Integration | Connecting React ↔ Spring Boot ↔ FastAPI in one cohesive system |
| Data Engineering | CSV pipelines, PostgreSQL schema design, JPA repositories |
| Iterative Development | Building a real product incrementally, data-first |

---

## 👨‍💻 Author

<div align="center">

**Karan Chirde**

*Full-Stack Developer | Building AgriCast*

[![GitHub](https://img.shields.io/badge/GitHub-chirdekaran262-181717?style=for-the-badge&logo=github)](https://github.com/chirdekaran262)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-karan-chirde-0666ab1aa-0A66C2?style=for-the-badge&logo=linkedin)](https://www.linkedin.com/in/karan-chirde-0666ab1aa/)

</div>

---

<div align="center">

**AgriCast** — *Built for Indian farmers. Grown one commit at a time.* 🌱

⭐ Star this repo to follow the journey!

</div>
