package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class SelectionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Nums array cannot be null");
        }
        int j;
        double min_val;
        int min_id;
        for (int i = 0; i < nums.length; i++) {
            min_val = nums[i];
            min_id = i;
            for (j = i + 1; j < nums.length; j++) {
                if (nums[j] < min_val) {
                    min_val = nums[j];
                    min_id = j;
                }
            }
            nums[min_id] = nums[i];
            nums[i] = min_val;
        }
    }

    public static void main(String[] args) {
        InsertionSort sorting = new InsertionSort();
        double[] nums = { 12, 5, 43, 14 };
        sorting.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }

    }

}
