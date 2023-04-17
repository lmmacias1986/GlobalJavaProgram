package effectiveJava;

import com.google.common.cache.CacheStats;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GuavaCacheLRUTest {
    @Test
    public void testEvictByExpiration() throws InterruptedException {
        LRUGuavaCacheTest<String, String> cache = new LRUGuavaCacheTest<>(10, 2, TimeUnit.SECONDS);
        cache.put("key", "value");
        Thread.sleep(2000);
        assertNull(cache.get("key"));
    }

    @Test
    public void testEvictBySize() throws InterruptedException {
        LRUGuavaCacheTest<String, String> cache = new LRUGuavaCacheTest<>(2, 5, TimeUnit.SECONDS);
        cache.put("key1", "value");
        cache.put("key2", "value");
        cache.put("key3", "value");
        assertEquals(2, (long) cache.getSize());
    }

    @Test
    public void testGetStatics() throws InterruptedException {
        LRUGuavaCacheTest<String, String> cache = new LRUGuavaCacheTest<>(10, 1, TimeUnit.SECONDS);
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        cache.get("key1");
        cache.get("key2");
        Thread.sleep(2000);
        CacheStats stats = cache.getCacheStats();
        System.out.println("Cache hits: " + stats.hitCount());
        System.out.println("Cache misses: " + stats.missCount());
    }
}
