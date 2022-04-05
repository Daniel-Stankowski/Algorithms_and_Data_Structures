package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.services.HashTable;

public class HashListChainingTest {
    HashTable<String> hashList;

    @Before
    public void setUp() {
        hashList = new HashListChaining<>(5);
    }

    @Test
    public void addStringElement() {
        // given
        hashList.add("Something");
        // when
        String returnedValue = hashList.get("Something");
        // then
        assertEquals("Something", returnedValue);

    }

    @Test
    public void getElementFromEmptyHashListShouldReturnNull() {
        // given
        String expectedString = null;
        String returnedString;
        // when
        returnedString = hashList.get("Something");
        // then
        assertEquals(expectedString, returnedString);
    }

    @Test
    public void deleteElementFromEmptyHashTableShouldThrowExeption() {
        // given
        boolean hasExeptionAppeared = false;
        // when
        try {
            hashList.delete("Something");
        } catch (NullPointerException e) {
            hasExeptionAppeared = true;
        }
        // then
        assertTrue(hasExeptionAppeared);
    }

    @Test
    public void deleteSameElementTwiceShouldThrowExeption() {
        // given
        boolean hasExeptionAppeared = false;
        // when
        hashList.add("Something");
        hashList.delete("Something");
        // then
        try {
            hashList.delete("Something");
        } catch (NullPointerException e) {
            hasExeptionAppeared = true;
        }

        assertTrue(hasExeptionAppeared);
    }

    @Test
    public void deleteFirstElementInListShouldReturnNull() {
        // given
        hashList.add("Something");
        hashList.delete("Something");
        // when
        String returnedString = hashList.get("Something");
        // then
        assertEquals(null, returnedString);
    }

    @Test
    public void addMultipleStrings() {
        // given
        hashList.add("Something1");
        hashList.add("Something2");
        hashList.add("Something3");
        // when
        String returnedValue1 = hashList.get("Something1");
        String returnedValue2 = hashList.get("Something2");
        String returnedValue3 = hashList.get("Something3");
        // then
        assertEquals(returnedValue1, "Something1");
        assertEquals(returnedValue2, "Something2");
        assertEquals(returnedValue3, "Something3");

    }

    @Test
    public void addDuplicateToHashList() {
        // given
        HashListChaining<String> hashListDuplicateTest = new HashListChaining<>(3);
        hashListDuplicateTest.add("Something1");
        hashListDuplicateTest.add("Something2");
        hashListDuplicateTest.add("Something3");
        hashListDuplicateTest.add("Something3");
        hashListDuplicateTest.add("Something4");
        hashListDuplicateTest.add("Something5");
        // when
        String returnedValue = hashListDuplicateTest.hashListToString();
        String expectedValue = "Something3 \nSomething4 Something1 \nSomething5 Something2 \n";
        // then
        assertEquals(expectedValue, returnedValue);
    }

    @Test
    public void testHashListStructureIntegerUsingPrintFunction() {
        // given
        HashListChaining<Integer> hashListInteger = new HashListChaining<>(3);
        hashListInteger.add(Integer.valueOf(1));
        hashListInteger.add(Integer.valueOf(2));
        hashListInteger.add(Integer.valueOf(3));
        hashListInteger.add(Integer.valueOf(4));
        hashListInteger.add(Integer.valueOf(5));
        // when
        String returnedValue = hashListInteger.hashListToString();
        String expectedValue = "3 \n4 1 \n5 2 \n";
        // then
        assertEquals(expectedValue, returnedValue);
    }

    @Test
    public void testHashListStructureDoubleUsingPrintFunction() {
        // given
        HashListChaining<Double> hashListDouble = new HashListChaining<>(3);
        hashListDouble.add(Double.valueOf(1.0));
        hashListDouble.add(Double.valueOf(2.0));
        hashListDouble.add(Double.valueOf(3.0));
        hashListDouble.add(Double.valueOf(4.0));
        hashListDouble.add(Double.valueOf(5.0));
        // when
        String returnedValue = hashListDouble.hashListToString();
        String expectedValue = "5.0 3.0 1.0 \n2.0 \n4.0 \n";
        // then
        assertEquals(expectedValue, returnedValue);
    }

    @Test
    public void testHashListStructureStringUsingPrintFunction() {
        // given
        HashListChaining<String> hashListString = new HashListChaining<>(3);
        hashListString.add("Something1");
        hashListString.add("Something2");
        hashListString.add("Something3");
        hashListString.add("Something4");
        hashListString.add("Something5");
        // when
        String returnedValue = hashListString.hashListToString();
        String expectedValue = "Something3 \nSomething4 Something1 \nSomething5 Something2 \n";
        // then
        assertEquals(expectedValue, returnedValue);
    }
}
