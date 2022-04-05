package pl.edu.pw.ee;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class HeapTest {

    @Test
    public void popEmptyHeap() {

        // given
        Heap heap = new Heap();
        boolean isEceptionCatched = false;
        // when
        try {
            heap.pop();
        } catch (ArrayIndexOutOfBoundsException e) {
            isEceptionCatched = true;
        }
        // then

        assertTrue(isEceptionCatched);

    }

    @Test
    public void addOneValueToHeap() {

        // given
        Heap heap = new Heap();

        // when

        heap.put(Double.valueOf(3));
        Double expected = Double.valueOf(3);
        Double result = heap.pop();

        // then

        assertEquals(result, expected);

    }

    @Test
    public void addMultipleValuesToHeap() {

        // given
        Heap heap = new Heap();

        // when

        heap.put(Double.valueOf(1.2));
        heap.put(Double.valueOf(2.3));
        heap.put(Double.valueOf(3.4));
        heap.put(Double.valueOf(4.5));
        heap.put(Double.valueOf(5.6));

        Double[] expected = { Double.valueOf(5.6), Double.valueOf(4.5), Double.valueOf(3.4), Double.valueOf(2.3),
                Double.valueOf(1.2) };
        Double[] result = new Double[5];

        for (int i = 0; i < 5; i++) {
            result[i] = heap.pop();
        }

        // then

        assertTrue(Arrays.equals(expected, result));

    }
}
