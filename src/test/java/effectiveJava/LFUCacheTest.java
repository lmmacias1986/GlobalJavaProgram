package effectiveJava;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LFUCacheTest {
    @Test
    public void CacheExpirationTime() throws InterruptedException {
        LFUCache<String, String> cache = new LFUCache<>(5, 5);

        cache.put("key1", "value1");
        cache.put("key2", "value2");

        assertEquals("value1", cache.get("key1"));
        assertEquals("value2", cache.get("key2"));

        Thread.sleep(5000);

        //now the value from cache is null
        assertNull(cache.get("key1"));
        assertNull(cache.get("key2"));
    }

    @Test
    public void CacheExpirationTimeLessThanLimit() throws InterruptedException {
        LFUCache<String, String> cache = new LFUCache<>(5, 5);

        cache.put("key1", "value1");
        cache.put("key2", "value2");

        assertEquals("value1", cache.get("key1"));
        assertEquals("value2", cache.get("key2"));

        Thread.sleep(3000);

        assertEquals("value1", cache.get("key1"));
        assertEquals("value2", cache.get("key2"));
    }
}
