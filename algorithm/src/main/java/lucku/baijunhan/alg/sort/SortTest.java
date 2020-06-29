package lucku.baijunhan.alg.sort;

import java.util.Arrays;

public class SortTest {

    public static void main(String[] args) {
        IntSort sort;
        int[] nums = new int[]{45,48,5,6,1,2,54,98,56,42,17,45,23,45,67,84,25};

        sort = new QuickSort();
        sort.sort(nums);
        System.out.println(Arrays.toString(nums));

        sort = new HeapSort();
        sort.sort(nums);
        System.out.println(Arrays.toString(nums));
    }




}
