package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HeapInterface;

public class Heap implements HeapInterface<Double> {
    private Double[] heapArray;
    private int heapElements;

    public Heap() {
        heapArray = new Double[10];
        heapElements = 0;
    }

    @Override
    public void put(Double item) {
        if (heapElements == heapArray.length) {
            doubleSize();
        }
        heapArray[heapElements] = item;
        for (int i = heapElements++; i > 0; i = (i - 1) / 2) {
            if (heapArray[(i - 1) / 2] >= heapArray[i]) {
                return;
            }
            swap((i - 1) / 2, i);
        }

    }

    @Override
    public Double pop() {
        if (heapElements == 1) {
            return heapArray[--heapElements];
        } else if (heapElements <= 0) {
            throw new ArrayIndexOutOfBoundsException("The heap is empty!");
        }
        swap(0, --heapElements);
        checkHeapIntegrity();
        return heapArray[heapElements];
    }

    private void doubleSize() {
        Double[] newHeap = new Double[heapArray.length * 2];
        System.arraycopy(heapArray, 0, newHeap, 0, heapElements);
        heapArray = newHeap;
    }

    private void swap(int firstId, int secondId) {
        Double tmp = heapArray[firstId];
        heapArray[firstId] = heapArray[secondId];
        heapArray[secondId] = tmp;
    }

    public void printHeap() {
        for (int i = 0; i < heapElements; i++) {
            System.out.println(heapArray[i]);
        }
    }

    private void checkHeapIntegrity() {
        int n = 0;
        while (n < heapElements) {
            if (2 * n + 1 < heapElements && 2 * n + 2 < heapElements) {
                if (heapArray[2 * n + 1] > heapArray[n] && heapArray[2 * n + 1] >= heapArray[2 * n + 2]) {
                    swap(2 * n + 1, n);
                    n = 2 * n + 1;
                } else if (heapArray[2 * n + 2] > heapArray[n] && heapArray[2 * n + 2] >= heapArray[2 * n + 1]) {
                    swap(2 * n + 2, n);
                    n = 2 * n + 2;
                } else {
                    return;
                }
            } else if (2 * n + 1 < heapElements) {
                if (heapArray[n] < heapArray[2 * n + 1]) {
                    swap(2 * n + 1, n);
                } else {
                    return;
                }
            } else
                return;
        }
    }

}
