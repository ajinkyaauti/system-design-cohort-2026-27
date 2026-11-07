import streamlit as st
import requests as req

API="http://localhost:8000"

st.title("Live Counter")
@st.fragment(run_every=1)
def live_counter():
    try:
        n = req.get(f"{API}/counter").json()["count"]
        st.metric("Counter", n)
    except req.ConnectionError:
        st.warning("Backend not running. Start uvicorn in another terminal.")
live_counter()

col1, col2 = st.columns(2)
with col1:
    clicked = st.button("Click to increment")
with col2:
    reset = st.button("Reset")

# Handle clicks before reading the count, so the number is already updated
try:
    if clicked:
        req.post(f"{API}/increment", timeout=2)
    if reset:
        req.post(f"{API}/reset", timeout=2)
except req.RequestException:
    st.warning("Backend not running. Start uvicorn in another terminal.")
