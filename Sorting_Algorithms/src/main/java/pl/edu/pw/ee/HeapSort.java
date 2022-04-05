package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class HeapSort implements Sorting {
    private Heap heap;

    public HeapSort() {
        heap = new Heap();
    }

    @Override
    public void sort(double[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Nums array cannot be null");
        }
        for (int i = 0; i < nums.length; i++) {
            heap.put(Double.valueOf(nums[i]));
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            nums[i] = heap.pop().doubleValue();
        }
    }

}
