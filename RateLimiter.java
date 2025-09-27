import java.util.HashMap;
import java.util.Map;

public class RateLimiter {
    private final int maxRequestsPerSecond;
    private final Map<String, Long> clientLastRequest = new HashMap<>();
    private final Map<String, Integer> clientRequests = new HashMap<>();

    public RateLimiter(int maxRequestsPerSecond) {
        this.maxRequestsPerSecond = maxRequestsPerSecond;
    }

    public synchronized boolean allow(String clientIP) {
        long now = System.currentTimeMillis() / 1000;
        if (!clientLastRequest.containsKey(clientIP) || clientLastRequest.get(clientIP) != now) {
            clientLastRequest.put(clientIP, now);
            clientRequests.put(clientIP, 1);
            return true;
        } else {
            int requests = clientRequests.get(clientIP);
            if (requests < maxRequestsPerSecond) {
                clientRequests.put(clientIP, requests + 1);
                return true;
            } else {
                return false;
            }
        }
    }
}
