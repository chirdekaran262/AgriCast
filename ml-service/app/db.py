from sqlalchemy import create_engine

DATABASE_URL = (
    "postgresql://postgres:5509@localhost:5432/farmer_db"
)

engine = create_engine(DATABASE_URL)