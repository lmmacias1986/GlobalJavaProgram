package tdd;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StockManagerTest {

    @Test
    public void testCanGetACorrectLocatorCode(){

        //use of the stub
        ExternalISBNDataService testService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return new Book(isbn, "Of Mice And Men", "J. Macias");
            }
        };

        String isbn = "0140177396";
        StockManager stockManager = new StockManager();
        stockManager.setService(testService);
        String locatorCode = stockManager.getLocator(isbn);
        assertEquals("7396J4", locatorCode);
    }
}
