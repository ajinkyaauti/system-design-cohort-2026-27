from fastapi import FastAPI
import sqlite3 as sq
import threading as th

app=FastAPI()
DB="counter.db"

lock=th.Lock()
conn=sq.connect(DB,check_same_thread=False)
conn.execute("CREATE TABLE IF NOT EXISTS counter (count INTEGER)")
conn.execute("INSERT OR IGNORE INTO counter (rowid, count) VALUES (1, 0)")
conn.commit()

@app.get("/counter")
def get_counter():
    with lock:
        cur = conn.execute("SELECT count FROM counter WHERE rowid=1")
        count = cur.fetchone()[0]
    return {"count": count}

@app.post("/increment")
def increment_counter():
    with lock:
        conn.execute("UPDATE counter SET count = count + 1 WHERE rowid=1")
        conn.commit()
    return {"status": "ok"}

@app.post("/reset")
def reset_counter():
    with lock:
        conn.execute("UPDATE counter SET count = 0 WHERE rowid=1")
        conn.commit()
    return {"status": "ok"}

