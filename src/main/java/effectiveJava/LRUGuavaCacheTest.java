package effectiveJava;

import com.google.common.cache.*;

import java.util.concurrent.TimeUnit;

public class LRUGuavaCacheTest<K, V> {

    private LoadingCache<String, String> cache;
    public LRUGuavaCacheTest(long maximumSize, long expirationDuration, TimeUnit timeUnit) {
        this.cache = CacheBuilder.newBuilder()
                .maximumSize(maximumSize)
                .expireAfterWrite(expirationDuration, timeUnit)
                .removalListener(notification -> {
                    String key = notification.getKey().toString();
                    String value = notification.getValue().toString();
                    String reason = notification.getCause().name();
                    System.out.println("Removed key: " + key + ", value: " + value + ", reason: " + reason);
                })
                .recordStats()
                .build(
                    new CacheLoader<String, String>() {
                        @Override
                        public String load(String key) throws Exception {
                            return "Value for " + key;
                        }
                });
    }

    public void put(String key, String value) {
        cache.put(key, value);
    }

    public String get(String key) {
        return cache.getIfPresent(key);
    }

    public Long getSize(){
        return cache.size();
    }

    public CacheStats getCacheStats(){
        return cache.stats();
    }
}
