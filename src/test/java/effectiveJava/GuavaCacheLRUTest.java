package effectiveJava;

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
}
