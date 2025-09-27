import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.logging.*;

public class ProxyServer {
    private static final int PORT = 8080;
    private static final int THREAD_POOL_SIZE = 100;
    private static final Logger logger = Logger.getLogger("ProxyLogger");
    private static final LRUCache<String, byte[]> cache = new LRUCache<>(1000);
    private static final RateLimiter rateLimiter = new RateLimiter(20);  // Limit to 20 req/sec per IP

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        logger.info("Proxy server started on port " + PORT);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            threadPool.submit(new ProxyWorker(clientSocket, cache, logger, rateLimiter));
        }
    }
}
