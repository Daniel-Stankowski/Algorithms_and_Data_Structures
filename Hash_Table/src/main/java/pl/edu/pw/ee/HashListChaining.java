package pl.edu.pw.ee;

import java.util.ArrayList;

import pl.edu.pw.ee.services.HashTable;

public class HashListChaining<T extends Comparable<T>> implements HashTable<T> {

    private final Elem nil = null;
    private ArrayList<Elem> hashElems;
    private int nElem = 0;

    private class Elem {
        private T value;
        private Elem next;

        Elem(T value, Elem nextElem) {
            this.value = value;
            this.next = nextElem;
        }
    }

    public HashListChaining(int size) {
        hashElems = new ArrayList<>();
        initializeHash(size);
    }

    @Override
    public void add(T value) {
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem oldElem = hashElems.get(hashId);
        while (oldElem != nil && !oldElem.value.equals(value)) {
            oldElem = oldElem.next;
        }
        if (oldElem != nil) {
            oldElem.value = value;
        } else {
            hashElems.set(hashId, new Elem(value, hashElems.get(hashId)));
            nElem++;
        }
    }

    @Override
    public T get(T value) {
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem elem = hashElems.get(hashId);

        while (elem != nil && !elem.value.equals(value)) {
            elem = elem.next;
        }

        return elem != nil ? elem.value : null;
    }

    public double countLoadFactor() {
        double size = hashElems.size();
        return nElem / size;
    }

    private void initializeHash(int size) {

        for (int i = 0; i < size; i++) {
            hashElems.add(i, nil);
        }
    }

    private int countHashId(int hashCode) {
        int n = hashElems.size();
        return Math.abs(hashCode) % n;
    }

    public String hashListToString() {
        String returnValue = "";
        for (int i = 0; i < hashElems.size(); i++) {
            Elem printElem = hashElems.get(i);
            while (printElem != nil) {
                returnValue += String.valueOf(printElem.value);
                returnValue += " ";

                printElem = printElem.next;
            }

            returnValue += "\n";

        }
        return returnValue;
    }

    @Override
    public void delete(T value) {
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem elemToDelete = hashElems.get(hashId);
        Elem elemBefore = elemToDelete;

        if (elemToDelete != nil && elemToDelete.value.equals(value)) {
            elemToDelete = elemToDelete.next;
            hashElems.set(hashId, elemToDelete);
            return;
        }
        while (elemToDelete != nil && !elemToDelete.value.equals(value)) {
            elemBefore = elemToDelete;
            elemToDelete = elemToDelete.next;
        }
        if (elemToDelete == nil) {
            throw new NullPointerException("Given value is not inside hash table");
        }
        elemBefore.next = elemToDelete.next;

    }

}