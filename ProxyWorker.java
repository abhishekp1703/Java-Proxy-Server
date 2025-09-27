import java.io.*;
import java.net.*;
import java.util.logging.*;

public class ProxyWorker implements Runnable {
    private final Socket clientSocket;
    private final LRUCache<String, byte[]> cache;
    private final Logger logger;
    private final RateLimiter rateLimiter;

    public ProxyWorker(Socket clientSocket, LRUCache<String, byte[]> cache, Logger logger, RateLimiter rateLimiter) {
        this.clientSocket = clientSocket;
        this.cache = cache;
        this.logger = logger;
        this.rateLimiter = rateLimiter;
    }

    @Override
    public void run() {
        String clientIP = clientSocket.getInetAddress().getHostAddress();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             OutputStream out = clientSocket.getOutputStream()) {

            String requestLine = in.readLine();
            if (requestLine == null || !requestLine.startsWith("GET")) {
                logger.warning("Invalid request: " + requestLine);
                clientSocket.close();
                return;
            }

            String url = extractURL(requestLine);
            if (!requestFilter(url)) {
                logger.warning("Blocked URL: " + url);
                out.write("HTTP/1.1 403 Forbidden\r\n\r\n".getBytes());
                clientSocket.close();
                return;
            }

            if (!rateLimiter.allow(clientIP)) {
                logger.warning("Rate limit exceeded for: " + clientIP);
                out.write("HTTP/1.1 429 Too Many Requests\r\n\r\n".getBytes());
                clientSocket.close();
                return;
            }

            byte[] response;
            synchronized (cache) {
                response = cache.get(url);
            }
            if (response != null) {
                out.write(response);
                logger.info("Cache HIT for: " + url);
            } else {
                logger.info("Cache MISS for: " + url);
                response = fetchFromBackend(url);
                synchronized (cache) {
                    cache.put(url, response);
                }
                out.write(response);
            }
            out.flush();
        } catch (Exception e) {
            logger.severe("Worker exception: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException ignored) {}
        }
    }

    private String extractURL(String requestLine) {
        String[] parts = requestLine.split(" ");
        return parts[1];
    }

    private boolean requestFilter(String url) {
        // Block access to example banned domains
        return !url.contains("blocked.com");
    }

    private byte[] fetchFromBackend(String urlStr) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream in = conn.getInputStream();
            byte[] chunk = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(chunk)) != -1) {
                buffer.write(chunk, 0, bytesRead);
            }
            in.close();
            return buffer.toByteArray();
        } catch (Exception e) {
            logger.warning("Error fetching backend: " + e.getMessage());
            return "HTTP/1.1 502 Bad Gateway\r\n\r\n".getBytes();
        }
    }
}
