package pl.edu.pw.ee;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import pl.edu.pw.ee.services.PatternSearch;

public class DeterministicFiniteAutomatonTextSearchTest {
    private PatternSearch ps;

    @Test
    public void shouldThrowExeptionWhenPatternIsNull() {
        boolean isCatched = false;
        try {
            ps = new DeterministicFiniteAutomatonTextSearch(null);
        } catch (IllegalArgumentException e) {
            isCatched = true;
        }
        assertTrue(isCatched);
    }

    @Test
    public void shouldThrowExeptionWhenPatternIsEmpty() {
        boolean isCatched = false;
        try {
            ps = new DeterministicFiniteAutomatonTextSearch("");
        } catch (IllegalArgumentException e) {
            isCatched = true;
        }
        assertTrue(isCatched);
    }

    @Test
    public void shouldThrowExeptionWhenTextIsNull_findFirst() {
        boolean isCatched = false;
        ps = new DeterministicFiniteAutomatonTextSearch("TEST");
        try {
            ps.findFirst(null);
        } catch (IllegalArgumentException e) {
            isCatched = true;
        }
        assertTrue(isCatched);
    }

    @Test
    public void shouldThrowExeptionWhenTextIsNull_findAll() {
        boolean isCatched = false;
        ps = new DeterministicFiniteAutomatonTextSearch("TEST");
        try {
            ps.findFirst(null);
        } catch (IllegalArgumentException e) {
            isCatched = true;
        }
        assertTrue(isCatched);
    }

    @Test
    public void shouldReturnNegativeOneWhenPatternLongerThanText_findFirst() {
        ps = new DeterministicFiniteAutomatonTextSearch("QPRS");
        int actual = ps.findFirst("ABC");
        int expected = -1;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyTableWhenPatternLongerThanText_findAll() {
        ps = new DeterministicFiniteAutomatonTextSearch("QPRS");
        int[] actual = ps.findAll("ABC");
        int[] expected = new int[0];
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnNegativeOneWhenTextDoesNotContainPattern() {
        ps = new DeterministicFiniteAutomatonTextSearch("QPRS");
        int actual = ps.findFirst("ABCCDBCCD");
        int expected = -1;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyTableWhenTextDoesNotContainPattern() {
        ps = new DeterministicFiniteAutomatonTextSearch("QPRS");
        int[] actual = ps.findAll("ABCCDBCCD");
        int[] expected = new int[0];
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnRightValue_findFirst_1() {
        ps = new DeterministicFiniteAutomatonTextSearch("BCCD");
        int actual = ps.findFirst("ABCCDBCCD");
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnRightValue_findAll_1() {
        ps = new DeterministicFiniteAutomatonTextSearch("BCCD");
        int[] actual = ps.findAll("ABCCDBCCD");
        int[] expected = { 1, 5 };
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnRightValue_findFirst_2() {
        ps = new DeterministicFiniteAutomatonTextSearch("ABA");
        int actual = ps.findFirst("ABABA");
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnRightValue_findAll_2_overlapping() {
        ps = new DeterministicFiniteAutomatonTextSearch("ABA");
        int[] actual = ps.findAll("ABABA");
        int[] expected = { 0, 2 };
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnRightValue_findFirst_3() {
        ps = new DeterministicFiniteAutomatonTextSearch("1");
        int actual = ps.findFirst("11111111");
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnRightValue_findAll_3_singleCharPattern() {
        ps = new DeterministicFiniteAutomatonTextSearch("1");
        int[] actual = ps.findAll("111111");
        int[] expected = { 0, 1, 2, 3, 4, 5 };
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnRightValue_findFirst_4() {
        ps = new DeterministicFiniteAutomatonTextSearch("1234");
        int actual = ps.findFirst("67123123456");
        int expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnRightValue_findAll_4() {
        ps = new DeterministicFiniteAutomatonTextSearch("1234");
        int[] actual = ps.findAll("67123123456");
        int[] expected = { 5 };
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnRightValue_findFirst_5() {
        ps = new DeterministicFiniteAutomatonTextSearch("12");
        int actual = ps.findFirst("431256127812912");
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnRightValue_findAll_5() {
        ps = new DeterministicFiniteAutomatonTextSearch("12");
        int[] actual = ps.findAll("431256127812912");
        int[] expected = { 2, 6, 10, 13 };
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnRightValue_findFirst_6() {
        ps = new DeterministicFiniteAutomatonTextSearch("1");
        int actual = ps.findFirst("1");
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnRightValue_findAll_6() {
        ps = new DeterministicFiniteAutomatonTextSearch("1");
        int[] actual = ps.findAll("1");
        int[] expected = { 0 };
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnRightValue_findFirst_7_SpecialCharacters() {
        int expectedNewLine = 2;
        int expectedTab = 4;
        int expectedReturn = 9;
        int expectedReturnNewLine = 11;

        String text = "AS\nA\tU\nAW\r\t\r\nN\r\n";
        ps = new DeterministicFiniteAutomatonTextSearch("\n");
        int actualNewLine = ps.findFirst(text);
        ps = new DeterministicFiniteAutomatonTextSearch("\t");
        int actualTab = ps.findFirst(text);
        ps = new DeterministicFiniteAutomatonTextSearch("\r");
        int actualReturn = ps.findFirst(text);
        ps = new DeterministicFiniteAutomatonTextSearch("\r\n");

        int actualReturnNewLine = ps.findFirst(text);
        assertEquals(expectedNewLine, actualNewLine);
        assertEquals(expectedTab, actualTab);
        assertEquals(expectedReturn, actualReturn);
        assertEquals(expectedReturnNewLine, actualReturnNewLine);
    }

    @Test
    public void shouldReturnRightValue_findAll_7() {
        int[] expectedNewLine = { 2, 6, 12, 15 };
        int[] expectedTab = { 4, 10 };
        int[] expectedReturn = { 9, 11, 14 };
        int[] expectedReturnNewLine = { 11, 14 };
        String text = "AS\nA\tU\nAW\r\t\r\nN\r\n";
        ps = new DeterministicFiniteAutomatonTextSearch("\n");
        int[] actualNewLine = ps.findAll(text);
        ps = new DeterministicFiniteAutomatonTextSearch("\t");
        int[] actualTab = ps.findAll(text);
        ps = new DeterministicFiniteAutomatonTextSearch("\r");
        int[] actualReturn = ps.findAll(text);
        ps = new DeterministicFiniteAutomatonTextSearch("\r\n");
        int[] actualReturnNewLine = ps.findAll(text);
        assertArrayEquals(expectedNewLine, actualNewLine);
        assertArrayEquals(expectedTab, actualTab);
        assertArrayEquals(expectedReturn, actualReturn);
        assertArrayEquals(expectedReturnNewLine, actualReturnNewLine);
    }

    @Test
    public void shouldReturnRightValue_findFirst_8() {
        ps = new DeterministicFiniteAutomatonTextSearch("84");
        int actual = ps.findFirst("912837912873129684");
        int expected = 16;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnRightValue_findAll_8() {
        ps = new DeterministicFiniteAutomatonTextSearch("124121");
        int[] actual = ps.findAll("124121");
        int[] expected = { 0 };
        assertArrayEquals(expected, actual);
    }
}
