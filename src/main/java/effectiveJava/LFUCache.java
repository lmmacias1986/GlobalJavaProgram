package effectiveJava;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class LFUCache<K, V> {
    private final int maxSize;
    private final long cacheExpirationTime;
    private final Map<K, CacheEntry> cache;
    private final PriorityQueue<CacheEntry> evictionQueue;

    public LFUCache(int maxSize, long cacheExpirationTime) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("Max size must be positive.");
        }
        this.maxSize = maxSize;
        this.cacheExpirationTime = TimeUnit.SECONDS.toMillis(cacheExpirationTime);
        this.cache = new HashMap<>(maxSize);
        this.evictionQueue = new PriorityQueue<>(Comparator.comparing(CacheEntry::getFrequency)
                .thenComparing(CacheEntry::getLastAccessTime));
    }

    public V get(K key) {
        CacheEntry cacheEntry = cache.get(key);
        if (cacheEntry != null) {
            long currentTime = System.currentTimeMillis();
            if (cacheEntry.getLastAccessTime() + cacheExpirationTime >= currentTime) {
                cacheEntry.incrementFrequency();
                cacheEntry.setLastAccessTime(currentTime);
                evictionQueue.remove(cacheEntry);
                evictionQueue.offer(cacheEntry);
                return cacheEntry.getValue();
            } else {
                cache.remove(key);
                evictionQueue.remove(cacheEntry);
            }
        }
        return null;
    }

    public void put(K key, V value) {
        if (cache.size() >= maxSize) {
            evictCacheEntry();
        }
        CacheEntry cacheEntry = new CacheEntry(key, value);
        cache.put(key, cacheEntry);
        evictionQueue.offer(cacheEntry);
    }

    private void evictCacheEntry() {
        CacheEntry cacheEntry = evictionQueue.poll();
        if (cacheEntry != null) {
            cache.remove(cacheEntry.getKey());
        }
    }

    private class CacheEntry {
        private final K key;
        private final V value;
        private int frequency;
        private long lastAccessTime;

        public CacheEntry(K key, V value) {
            this.key = key;
            this.value = value;
            this.frequency = 1;
            this.lastAccessTime = System.currentTimeMillis();
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public int getFrequency() {
            return frequency;
        }

        public void incrementFrequency() {
            frequency++;
        }

        public long getLastAccessTime() {
            return lastAccessTime;
        }

        public void setLastAccessTime(long lastAccessTime) {
            this.lastAccessTime = lastAccessTime;
        }
    }
}
