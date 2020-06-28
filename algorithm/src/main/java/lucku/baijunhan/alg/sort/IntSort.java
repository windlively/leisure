package lucku.baijunhan.alg.sort;

public interface IntSort {

    void sort(int[] nums);

    static void swap(int[] nums, int i, int j){
        int n = nums[i];
        nums[i] = nums[j];
        nums[j] = n;
    }

}
