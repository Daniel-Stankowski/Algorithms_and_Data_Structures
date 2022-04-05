package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class RbtMapTest {
    private RbtMap<Integer, String> rbtMap;

    @Before
    public void setUp() {
        rbtMap = new RbtMap<>();
    }

    @Test
    public void setValueNullValueElementToRbtMapShouldReturnExeption() {
        boolean isCatched = false;
        try {
            rbtMap.setValue(0, null);
        } catch (IllegalArgumentException e) {
            isCatched = true;
        }
        assertTrue(isCatched);
    }

    @Test
    public void setValueNullKeyElementToRbtMapShouldReturnExeption() {
        boolean isCatched = false;
        try {
            rbtMap.setValue(null, "Something");
        } catch (IllegalArgumentException e) {
            isCatched = true;
        }
        assertTrue(isCatched);
    }

    @Test
    public void setValueBothNullElementToRbtMapShouldReturnExeption() {
        boolean isCatched = false;
        try {
            rbtMap.setValue(null, null);
        } catch (IllegalArgumentException e) {
            isCatched = true;
        }
        assertTrue(isCatched);
    }

    @Test
    public void getNullKeyElementExeptionExpected() {
        boolean isCatched = false;
        try {
            rbtMap.getValue(null);
        } catch (IllegalArgumentException e) {
            isCatched = true;
        }
        assertTrue(isCatched);
    }

    //
    @Test
    public void setValueThenGetValueElementFromRbtMapInteger() {
        String expected = "Something";
        rbtMap.setValue(0, "Something");
        String result = rbtMap.getValue(0);
        assertEquals(expected, result);
    }

    @Test
    public void setValueManyElementsAndGetValueValueThemInteger() {
        String expected = "";
        String result = "";
        for (int i = 0; i < 20; i++) {
            rbtMap.setValue(i, "Something" + i);
        }
        for (int i = 0; i < 20; i++) {
            expected = "Something" + i;
            result = rbtMap.getValue(i);
            assertEquals(expected, result);
        }
    }

    @Test
    public void setValueTwoElementsWithSameKeyShouldOverwriteInteger() {
        String expected = "Something2";
        rbtMap.setValue(0, "Something1");
        rbtMap.setValue(0, "Something2");
        String restult = rbtMap.getValue(0);
        assertEquals(expected, restult);
    }

    @Test
    public void setValueThenGetValueRandomElementsInteger() {
        String expected;
        String result;
        Integer[] randomIntegers = new Integer[20];
        Random random = new Random(43);
        for (int i = 0; i < 20; i++) {
            randomIntegers[i] = random.nextInt(100);
            rbtMap.setValue(randomIntegers[i], "Something" + randomIntegers[i]);
        }
        for (int i = 0; i < 20; i++) {
            expected = "Something" + randomIntegers[i];
            result = rbtMap.getValue(randomIntegers[i]);
            assertEquals(expected, result);
        }

    }

    //
    @Test
    public void setValueThenGetValueElementFromRbtMapDouble() {
        RbtMap<Double, String> rbtMapDouble = new RbtMap<>();
        String expected = "Something";
        rbtMapDouble.setValue(1.1, "Something");
        String result = rbtMapDouble.getValue(1.1);
        assertEquals(expected, result);
    }

    @Test
    public void setValueManyElementsAndGetValueValueThemDouble() {
        RbtMap<Double, String> rbtMapDouble = new RbtMap<>();
        String expected = "";
        String result = "";
        for (double i = 1.1; i < 20; i++) {
            rbtMapDouble.setValue(i, "Something" + i);
        }
        for (double i = 1.1; i < 20; i++) {
            expected = "Something" + i;
            result = rbtMapDouble.getValue(i);
            assertEquals(expected, result);
        }
    }

    @Test
    public void setValueTwoElementsWithSameKeyShouldOverwriteDouble() {
        RbtMap<Double, String> rbtMapDouble = new RbtMap<>();
        String expected = "Something2";
        rbtMapDouble.setValue(1.1, "Something1");
        rbtMapDouble.setValue(1.1, "Something2");
        String restult = rbtMapDouble.getValue(1.1);
        assertEquals(expected, restult);
    }

    @Test
    public void setValueThenGetValueRandomElementsDouble() {
        RbtMap<Double, String> rbtMapDouble = new RbtMap<>();
        String expected;
        String result;
        Double[] randomDoubles = new Double[20];
        Random random = new Random(43);
        for (int i = 0; i < 20; i++) {
            randomDoubles[i] = random.nextDouble();
            rbtMapDouble.setValue(randomDoubles[i], "Something" + randomDoubles[i]);
        }
        for (int i = 0; i < 20; i++) {
            expected = "Something" + randomDoubles[i];
            result = rbtMapDouble.getValue(randomDoubles[i]);
            assertEquals(expected, result);
        }

    }

    //
    @Test
    public void setValueThenGetValueElementFromRbtMapString() {
        RbtMap<String, String> rbtMapString = new RbtMap<>();
        String expected = "Something";
        rbtMapString.setValue("Something", "Something");
        String result = rbtMapString.getValue("Something");
        assertEquals(expected, result);
    }

    @Test
    public void setValueManyElementsAndGetValueValueThemString() {
        RbtMap<String, String> rbtMapString = new RbtMap<>();
        String expected = "";
        String result = "";
        for (int i = 0; i < 20; i++) {
            rbtMapString.setValue("Something" + i, "Something" + i);
        }
        for (int i = 0; i < 20; i++) {
            expected = "Something" + i;
            result = rbtMapString.getValue("Something" + i);
            assertEquals(expected, result);
        }
    }

    @Test
    public void setValueTwoElementsWithSameKeyShouldOverwriteString() {
        RbtMap<String, String> rbtMapString = new RbtMap<>();
        String expected = "Something2";
        rbtMapString.setValue("Something", "Something1");
        rbtMapString.setValue("Something", "Something2");
        String restult = rbtMapString.getValue("Something");
        assertEquals(expected, restult);
    }

    @Test
    public void setValueThenGetValueRandomElementsString() {
        RbtMap<String, String> rbtMapString = new RbtMap<>();
        String expected;
        String result;
        String[] randomStrings = new String[20];
        Random random = new Random(43);
        for (int i = 0; i < 20; i++) {
            randomStrings[i] = "Something" + random.nextInt(100);
            rbtMapString.setValue(randomStrings[i], randomStrings[i]);
        }
        for (int i = 0; i < 20; i++) {
            expected = randomStrings[i];
            result = rbtMapString.getValue(randomStrings[i]);
            assertEquals(expected, result);
        }

    }
}
