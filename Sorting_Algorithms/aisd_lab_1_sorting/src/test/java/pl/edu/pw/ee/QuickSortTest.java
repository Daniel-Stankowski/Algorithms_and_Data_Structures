package pl.edu.pw.ee;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.services.Sorting;

public class QuickSortTest {

    private Sorting sorting;
    private Random random;
    private final long seed = 2137;
    private final int howManyValues = 10;

    @Before
    public void SetUp() {
        sorting = new QuickSort();
        random = new Random(seed);

    }

    @Test
    public void sortEmptyArray() {

        // given
        double[] givenArray = {};
        // when
        sorting.sort(givenArray);
        // then
        double[] expectedArray = {};

        assertArrayEquals(expectedArray, givenArray, 0);
    }

    @Test
    public void sortOneElementArray() {

        // given
        double[] givenArray = { 3.4 };
        // when
        sorting.sort(givenArray);
        // then
        double[] expectedArray = { 3.4 };

        assertArrayEquals(expectedArray, givenArray, 0);
    }

    @Test
    public void sortTwoElementArray() {

        // given
        double[] givenArray = { 3.4, -12.5 };
        // when
        sorting.sort(givenArray);
        // then
        double[] expectedArray = { -12.5, 3.4 };

        assertArrayEquals(expectedArray, givenArray, 0);
    }

    @Test
    public void sortArrayIdenticalElements() {

        // given
        double[] givenArray = { 4.2, 4.2, 4.2, 4.2, 4.2, 4.2, 4.2, 4.2, };
        // when
        sorting.sort(givenArray);
        // then
        double[] expectedArray = { 4.2, 4.2, 4.2, 4.2, 4.2, 4.2, 4.2, 4.2, };

        assertArrayEquals(expectedArray, givenArray, 0);
    }

    @Test
    public void sortArrayNegativeOnly() {

        // given
        double[] givenArray = { -2.3, -13.3, -124.3, -3.2, -1.4 };
        // when
        sorting.sort(givenArray);
        // then
        double[] expectedArray = { -124.3, -13.3, -3.2, -2.3, -1.4 };

        assertArrayEquals(expectedArray, givenArray, 0);
    }

    @Test
    public void sortArrayUnorderedData1() {

        // given
        double[] givenArray = { 58.3, 19.1, 82.5, 74.7, 73.2, 68.8, 69.0, 52.2, 42.7, 47.1, 43.7, 34.9, 60.1, 17.4,
                14.5, 78.4, 44.4, 31.1, 7.1, 95.5 };
        // when
        sorting.sort(givenArray);
        // then
        double[] expectedArray = { 7.1, 14.5, 17.4, 19.1, 31.1, 34.9, 42.7, 43.7, 44.4, 47.1, 52.2, 58.3, 60.1, 68.8,
                69.0, 73.2, 74.7, 78.4, 82.5, 95.5 };

        assertArrayEquals(expectedArray, givenArray, 0);
    }

    @Test
    public void sortAlreadySortedArray() {
        // given
        double[] givenArray = { 3, 19, 20, 25, 28, 35, 42, 49, 52, 76 };

        // when
        sorting.sort(givenArray);

        // Then
        double[] expectedArray = { 3, 19, 20, 25, 28, 35, 42, 49, 52, 76 };

        assertArrayEquals(expectedArray, givenArray, 0);

    }

    @Test
    public void sortInverslySortedArray() {
        // given
        double[] givenArray = { 76, 52, 49, 42, 35, 28, 25, 20, 19, 3 };

        // when
        sorting.sort(givenArray);

        // Then
        double[] expectedArray = { 3, 19, 20, 25, 28, 35, 42, 49, 52, 76 };

        assertArrayEquals(expectedArray, givenArray, 0);

    }

    @Test
    public void sortPseudoRandomArrayWithSeed() {

        // given
        double[] givenArray = new double[howManyValues];
        double[] expectedArray = new double[howManyValues];

        for (int i = 0; i < howManyValues; i++) {
            givenArray[i] = random.nextDouble();
            expectedArray[i] = givenArray[i];
        }

        // when
        sorting.sort(givenArray);
        Arrays.sort(expectedArray);
        // then

        assertArrayEquals(expectedArray, givenArray, 0);
    }

    @Test
    public void shouldThrowIllegalArgumentExeption() {
        // given
        double[] givenArray = null;
        // when
        boolean isCatched = false;
        // then
        try {
            sorting.sort(givenArray);
        } catch (IllegalArgumentException e) {
            isCatched = true;
        }
        assertTrue(isCatched);
    }

}