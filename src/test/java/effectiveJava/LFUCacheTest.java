package effectiveJava;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LFUCacheTest {
    @Test
    public void CacheExpirationTime(){
        LFUCache<String, String> cache = new LFUCache<>(100_000, 5);

        cache.put("key1", "value1");
        cache.put("key2", "value2");

        assertEquals("value1", cache.get("key1"));
        assertEquals("value2", cache.get("key2"));

        timeExpiration(5000);

        //now the value from cache is null
        assertNull(cache.get("key1"));
        assertNull(cache.get("key2"));
    }

    @Test
    public void CacheExpirationTimeLessThanLimit(){
        LFUCache<String, String> cache = new LFUCache<>(100_000, 5);

        cache.put("key1", "value1");
        cache.put("key2", "value2");

        assertEquals("value1", cache.get("key1"));
        assertEquals("value2", cache.get("key2"));

        timeExpiration(3000);

        assertEquals("value1", cache.get("key1"));
        assertEquals("value2", cache.get("key2"));
    }

    public void timeExpiration(int millisenconds){
        //wait until the cache is cleaned for the expiration time
        try {
            Thread.sleep(millisenconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
