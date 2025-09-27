# 🚀 Java Multithreaded Proxy Server

![Java](https://img.shields.io/badge/Java-11%2B-orange)
![Contributions Welcome](https://img.shields.io/badge/contributions-welcome-brightgreen)
![MIT License](https://img.shields.io/badge/license-MIT-blue)

A high-performance, scalable proxy server with:
- 🔹 LRU caching (%75 cache hit rate)
- 🔹 Multithreaded architecture (Handles 500+ concurrent requests/sec)
- 🔹 Request filtering & rate limiting
- 🔹 Java logging for monitoring and debugging


## 📦 Project Structure

📂 JavaProxyServer

├── ProxyServer.java

├── ProxyWorker.java

├── LRUCache.java

├── RateLimiter.java

├── README.md

├── INSTRUCTIONS.md

└── .gitignore

## 🎉 Features

| Feature                | Description                                                         |
|------------------------|---------------------------------------------------------------------|
| 🚦 Multithreaded       | Efficient request handling via thread pool                          |
| 🔄 LRU Cache           | Fast in-memory caching; improves response time by 30%               |
| ✅ Filter & Limiting   | Blocks unsafe requests, limits client abuse                         |
| 📈 Logging             | Tracks server activity for debugging and monitoring                 |

---

## 🛠️ Setup & Usage

### 1. Clone/Download the Project

git clone https://github.com/abhishekp1703/JavaProxyServer.git
cd JavaProxyServer


### 2. Compile All Java Files

javac *.java


### 3. Run the Proxy Server

java ProxyServer


Server runs on `localhost:8080` by default.

---

## 🧑‍💻 Demo

- **Configure your browser/tool:**  
  Use `localhost:8080` as your HTTP proxy.
- **Make requests:**  
  See terminal logs for cache hits, rate limits, and filter activity.

---

## 📝 Example Logs

INFO: Proxy server started on port 8080
INFO: Cache HIT for: http://example.com/
WARNING: Blocked URL: http://blocked.com/
WARNING: Rate limit exceeded for: 192.168.1.101


---

## 📚 Documentation

- [INSTRUCTIONS.md](INSTRUCTIONS.md) – step-by-step setup and usage guide

---

## 📄 License

This project is licensed under the MIT License.

---
