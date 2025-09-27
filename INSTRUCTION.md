# Instructions for Setup & Usage

## Prerequisites

- Java JDK 11 or newer
- Terminal (macOS, Linux, or Windows)
- (Optional) Visual Studio Code or other editor

## 1. Clone or Download

Clone:
git clone https://github.com/abhishekp1703/JavaProxyServer.git
cd JavaProxyServer

Or download as ZIP and extract the files.

## 2. Compile

Open a terminal in the project folder and run:
javac *.java

## 3. Run the Proxy Server

Start the server with:
java ProxyServer

The default port is 8080. You can change this in the code if needed.

## 4. Test the Server

- Set your browser or HTTP tool to use localhost:8080 as a proxy.
- Make requests; observe output and logs for cache status and rate limits.

## 5. Configuration

- To change port: Edit the PORT variable in ProxyServer.java.
- To change cache size: Edit the LRUCache instantiation in ProxyServer.java.
- To adjust blocking/filtering: Change logic in the requestFilter method in ProxyWorker.java.
- Rate limit: Update limit in RateLimiter.java.

## Troubleshooting

- If `git push` fails, ensure you are using a GitHub personal access token—not your old password.
- If you encounter compilation errors, verify Java is installed (`java -version`).

---

For issues or contributions, open an issue or pull request on GitHub!
