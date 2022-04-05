package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class RedBlackTreeTest {
    RedBlackTree<Integer, String> tree;

    @Before
    public void setUp() {
        tree = new RedBlackTree<>();
    }

    @Test
    public void putNullValueElementToTreeShouldReturnExeption() {
        boolean isCatched = false;
        try {
            tree.put(0, null);
        } catch (IllegalArgumentException e) {
            isCatched = true;
        }
        assertTrue(isCatched);
    }

    @Test
    public void putNullKeyElementToTreeShouldReturnExeption() {
        boolean isCatched = false;
        try {
            tree.put(null, "Something");
        } catch (IllegalArgumentException e) {
            isCatched = true;
        }
        assertTrue(isCatched);
    }

    @Test
    public void deleteFromEmptyTreeExeptionExpected() {
        boolean isCatched = false;
        try {
            tree.deleteMax();
        } catch (IllegalArgumentException e) {
            isCatched = true;
        }
        assertTrue(isCatched);
    }

    @Test
    public void putBothNullElementToTreeShouldReturnExeption() {
        boolean isCatched = false;
        try {
            tree.put(null, null);
        } catch (IllegalArgumentException e) {
            isCatched = true;
        }
        assertTrue(isCatched);
    }

    @Test
    public void getNullKeyElementExeptionExpected() {
        boolean isCatched = false;
        try {
            tree.get(null);
        } catch (IllegalArgumentException e) {
            isCatched = true;
        }
        assertTrue(isCatched);
    }

    @Test
    public void putElementToTree() {
        String expected = "0:Something";
        tree.put(0, "Something");
        String result = tree.getPreOrder();
        assertEquals(expected, result);
    }

    @Test
    public void putThenGetElementFromTree() {
        String expected = "Something";
        tree.put(0, "Something");
        String result = tree.get(0);
        assertEquals(expected, result);
    }

    @Test
    public void printEmptyTree() {
        String result = "";
        String expected1 = tree.getPreOrder();
        String expected2 = tree.getInOrder();
        String expected3 = tree.getPostOrder();
        assertEquals(expected1, result);
        assertEquals(expected2, result);
        assertEquals(expected3, result);
    }

    @Test
    public void putThenDeleteElementFromTree() {
        String expected = null;
        tree.put(0, "Something");
        tree.deleteMax();
        String result = tree.get(0);
        assertEquals(expected, result);
    }

    @Test
    public void putManyElementsAndGetThem() {
        String expected = "";
        String result = "";
        for (int i = 0; i < 20; i++) {
            tree.put(i, "Something" + i);
        }
        for (int i = 0; i < 20; i++) {
            expected = "Something" + i;
            result = tree.get(i);
            assertEquals(expected, result);
        }
    }

    @Test
    public void putManyElementsAndDeleteFiveOfThem() {
        String expected = null;
        String result;
        for (int i = 0; i < 20; i++) {
            tree.put(i, "Something" + i);
        }
        for (int i = 0; i < 5; i++) {
            tree.deleteMax();
        }
        for (int i = 19; i > 14; i--) {
            result = tree.get(i);
            assertEquals(expected, result);
        }
    }

    @Test
    public void putTwoElementsWithSameKeyShouldOverwrite() {
        String expected = "Something2";
        tree.put(0, "Something1");
        tree.put(0, "Something2");
        String restult = tree.get(0);
        assertEquals(expected, restult);
    }

    @Test
    public void putSixElemementsTestPreOrder() {
        String expected = "3:Something3 1:Something1 0:Something0 2:Something2 5:Something5 4:Something4";
        for (int i = 0; i < 6; i++) {
            tree.put(i, "Something" + i);
        }
        String result = tree.getPreOrder();
        assertEquals(expected, result);
    }

    @Test
    public void putSixElemementsTestInOrder() {
        String expected = "1:Something1 0:Something0 2:Something2 3:Something3 5:Something5 4:Something4";
        for (int i = 0; i < 6; i++) {
            tree.put(i, "Something" + i);
        }
        String result = tree.getInOrder();
        assertEquals(expected, result);
    }

    @Test
    public void putSixElemementsTestPostOrder() {
        String expected = "1:Something1 0:Something0 2:Something2 5:Something5 4:Something4 3:Something3";
        for (int i = 0; i < 6; i++) {
            tree.put(i, "Something" + i);
        }
        String result = tree.getPostOrder();
        assertEquals(expected, result);
    }

    @Test
    public void putThenGetRandomElements() {
        String expected;
        String result;
        Integer[] randomIntegers = new Integer[20];
        Random random = new Random(39);
        for (int i = 0; i < 20; i++) {
            randomIntegers[i] = random.nextInt(100);
            tree.put(randomIntegers[i], "Something" + randomIntegers[i]);
        }
        for (int i = 0; i < 20; i++) {
            expected = "Something" + randomIntegers[i];
            result = tree.get(randomIntegers[i]);
            assertEquals(expected, result);
        }

    }
}
