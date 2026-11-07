# Live Counter

A shared counter built with FastAPI (backend), SQLite (storage), and Streamlit (frontend). Everyone who opens the page sees the same number, and it survives server restarts.

## Project structure

```
Livecounter/
├── main.py      # FastAPI backend: stores the count in counter.db
├── app.py       # Streamlit frontend: shows the count and the buttons
├── counter.db   # SQLite database (created automatically on first run)
└── README.md
```

## Requirements

- Python 3.9 or newer
- Streamlit 1.37 or newer (needed for `st.fragment`)

## Setup

Install the dependencies:

```powershell
python -m pip install --upgrade streamlit fastapi uvicorn requests
```

## Running the app

Both parts must run at the same time. Open two terminals in this folder.

**Terminal 1: backend**

```powershell
python -m uvicorn main:app --reload
```

You should see `Uvicorn running on http://127.0.0.1:8000`.

**Terminal 2: frontend**

```powershell
python -m streamlit run app.py
```

Then open http://localhost:8501 in your browser.

**OR**
**Terminal : DOCKER**
```powershell
docker compose up --build  
```



## API endpoints

| Method | Endpoint     | Description                        |
|--------|--------------|------------------------------------|
| GET    | `/counter`   | Returns the current count          |
| POST   | `/increment` | Adds 1 to the count and returns it |
| POST   | `/reset`     | Sets the count back to 0           |

You can check the backend directly at http://localhost:8000/counter.

## How it works

- **Backend (`main.py`)** keeps the count in a SQLite table with a single row. Each increment is done by the database (`count = count + 1`), so updates stay correct even when several requests arrive at once.
- **Frontend (`app.py`)** shows the count and sends button clicks to the backend. It asks for the latest number once per second, so changes from other browser tabs appear automatically.

## Troubleshooting

- **"Backend not running" warning:** uvicorn isn't running. Start it in Terminal 1.
- **Buttons don't appear:** check that Streamlit is 1.37 or newer with `python -m streamlit version`.
- **`uvicorn` or `streamlit` not recognized:** use the `python -m` form shown above.
- **Reset the count manually:** click Reset, or stop the backend and delete `counter.db`.

## Learning notes

- **Single source of truth:** the server owns the count, not the browser.
- **Polling vs. push:** Streamlit polls every second. WebSockets or Server-Sent Events would push updates instead.
- **Concurrency:** the lock and the atomic SQL update prevent lost clicks.
- **Scaling limit:** one SQLite file works for one server. Multiple servers would need a shared database such as PostgreSQL or Redis.