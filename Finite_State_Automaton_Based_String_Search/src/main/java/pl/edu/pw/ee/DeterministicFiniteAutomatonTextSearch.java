package pl.edu.pw.ee;

import static java.lang.Math.min;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import pl.edu.pw.ee.services.PatternSearch;

public class DeterministicFiniteAutomatonTextSearch implements PatternSearch {

    private class Key {
        private int state;
        private char sign;

        public Key(int state, char sign) {
            this.state = state;
            this.sign = sign;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof Key)) {
                return false;
            }
            Key key = (Key) o;
            return state == key.state && sign == key.sign;
        }

        @Override
        public int hashCode() {
            return Objects.hash(state, sign);
        }
    }

    private String pattern;
    private Map<Key, Integer> transMap;

    public DeterministicFiniteAutomatonTextSearch(String pattern) {
        validateInput(pattern);
        if (pattern.length() == 0) {
            throw new IllegalArgumentException("Pattern length should be at least 1");
        }
        this.pattern = pattern;
        buildTransitionMatrix();
    }

    @Override
    public int findFirst(String text) {
        validateInput(text);
        int n = text.length();
        int acceptedState = pattern.length();
        int result = -1;
        int state = 0;

        for (int i = 0; i < n; i++) {
            Key key = new Key(state, text.charAt(i));
            if (transMap.get(key) == null) {
                continue;
            }
            state = transMap.get(key);

            if (state == acceptedState) {
                result = i - acceptedState + 1;
                break;
            }
        }

        return result;
    }

    @Override
    public int[] findAll(String text) {
        validateInput(text);
        int n = text.length();
        int acceptedState = pattern.length();
        int[] result = new int[n];
        int howManyAdded = 0;
        int state = 0;

        for (int i = 0; i < n; i++) {
            Key key = new Key(state, text.charAt(i));
            if (transMap.get(key) == null) {
                continue;
            }
            state = transMap.get(key);

            if (state == acceptedState) {
                result[howManyAdded] = i - acceptedState + 1;
                if (acceptedState > 1) {
                    i = i - acceptedState + 2;
                    state = 0;
                }
                howManyAdded++;
            }

        }
        if (howManyAdded == 0) {
            return new int[0];
        }
        return Arrays.copyOfRange(result, 0, howManyAdded);
    }

    private void validateInput(String txt) {
        if (txt == null) {
            throw new IllegalArgumentException("Input text cannot be null!");
        }
    }

    private void buildTransitionMatrix() {
        transMap = new HashMap<>();

        int m = pattern.length();
        List<Character> alphabet = getAlphabetOfPattern();

        for (int q = 0; q <= m; q++) {
            for (char sign : alphabet) {

                int k = min(m + 1, q + 2);
                k--;

                while (k > 0 && !isSuffixOfPattern(k, q, sign)) {
                    k--;
                }
                transMap.put(new Key(q, sign), k);
            }
        }
    }

    private List<Character> getAlphabetOfPattern() {
        List<Character> signs = pattern.chars()
                .distinct()
                .mapToObj(c -> (char) c)
                .collect(toList());

        return signs;
    }

    private boolean isSuffixOfPattern(int kIndex, int qIndex, char sign) {
        boolean isSuffix = false;

        if (pattern.charAt(--kIndex) == sign) {
            isSuffix = true;

            while (kIndex > 0) {
                kIndex--;
                qIndex--;

                if (pattern.charAt(kIndex) != pattern.charAt(qIndex)) {
                    isSuffix = false;
                    break;
                }
            }
        }

        return isSuffix;
    }
}
