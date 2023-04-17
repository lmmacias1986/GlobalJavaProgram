package effectiveJava;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class InsertionSortTest {
    @Test
    public void testInsertionSort() {
        int[] arr = {3, 2, 1, 5, 4};
        InsertionSort.sort(arr);
        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, arr);
    }
}
