package effectiveJava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class GuavaCacheLRUTest {
    private static final int MAX_CACHE_SIZE = 100000;
    private static final int CACHE_EXPIRATION_SECONDS = 5;

    public static void main(String[] args) throws ExecutionException {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(MAX_CACHE_SIZE)
                .expireAfterAccess(CACHE_EXPIRATION_SECONDS, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key){
                        return "Value for " + key;
                    }
                });

        // Access the cache
        cache.put("Key1", "Value1");
        cache.put("Key2", "Value2");

        // Print the values
        System.out.println(cache.get("Key1"));
        System.out.println(cache.get("Key2"));

        timeExpiration(5000);

        // Print the values after time expirations is over
        System.out.println(cache.get("Key1"));
        System.out.println(cache.get("Key2"));
    }

    public static void timeExpiration(int millisenconds){
        //wait until the cache is cleaned for the expiration time
        try {
            Thread.sleep(millisenconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
