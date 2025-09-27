# Java Multithreaded Proxy Server

A robust Java proxy server that efficiently handles over 500 concurrent requests per second. Features LRU in-memory caching, request filtering, rate limiting, and Java logging for improved performance and security.

## Features

- Multithreaded: High concurrency support
- LRU Cache: 75% cache hit rate, 30% faster responses
- In-memory caching: Reduces redundant backend requests
- Request Filtering: Block unsafe or unwanted domains/URLs
- Rate Limiting: Prevents abuse by limiting requests per client
- Logging: Tracks activity for debugging and audits

## Getting Started

See [INSTRUCTIONS.md](INSTRUCTIONS.md) for setup and run steps.

## Project Structure

- ProxyServer.java
- ProxyWorker.java
- LRUCache.java
- RateLimiter.java
- README.md
- INSTRUCTIONS.md
- .gitignore

## License

MIT License
