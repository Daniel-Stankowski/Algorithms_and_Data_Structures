package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.List;

import pl.edu.pw.ee.services.Sorting;

public class QuickSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        if (nums == null) { // Nieprawidlowa nazwa
            throw new IllegalArgumentException("Nums array cannot be null");
        }

        quicksort(nums);
    }

    private void quicksort(double[] data) {
        List<Integer> starts = new ArrayList<>();
        List<Integer> ends = new ArrayList<>();

        Integer left = 0;
        Integer right = data.length - 1;

        starts.add(left);
        ends.add(right);

        int n = 1;
        int pivot;

        if (left < right) {

            while (n > 0) {
                n--;
                left = starts.get(n);
                right = ends.get(n);
                pivot = splitData(data, left, right);
                starts.remove(n);
                ends.remove(n);

                if (pivot - 1 > left) {
                    starts.add(left);
                    ends.add(pivot - 1);
                    n++;
                }

                if (pivot + 1 < right) {
                    starts.add(pivot + 1);
                    ends.add(right);
                    n++;
                }
            }
        }
    }

    private int splitData(double[] data, int start, int end) {
        int left = start + 1;
        int right = end;

        while (left < right) {
            while (left < right && data[left] < data[start]) {
                left++;
            }

            while (left < right && data[right] >= data[start]) {
                right--;
            }

            swap(data, left, right);
        }

        if (data[left] >= data[start]) {
            left--;
        }

        swap(data, start, left);

        return left;
    }

    private void swap(double[] data, int firstId, int secondId) {
        if (firstId != secondId) {
            double firstValue = data[firstId];
            data[firstId] = data[secondId];
            data[secondId] = firstValue;
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        QuickSort sorting = new QuickSort();
        double[] nums = { 58.3, 19.1, 82.5, 74.7, 73.2, 68.8, 69.0, 52.2, 42.7, 47.1, 43.7, 34.9, 60.1, 17.4, 14.5,
                78.4, 44.4, 31.1, 7.1, 95.5 };
        sorting.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + ", ");
        }
        System.out.println((System.currentTimeMillis() - start));
    }
}
