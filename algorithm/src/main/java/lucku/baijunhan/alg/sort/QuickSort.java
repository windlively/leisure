package lucku.baijunhan.alg.sort;

public class QuickSort implements IntSort{

    @Override
    public void sort(int[] nums) {
        quickSort(nums,0, nums.length - 1);
    }

    private void quickSort(int[] nums, int left, int right){
        if(left >= right) return;
        int l = left, r = right;
        int current = nums[l];
        while (l < r){
            while (l < r && nums[r] >= current) r --;
            nums[l] = nums[r];
            while (l < r && nums[l] <= current) l ++;
            nums[r] = nums[l];
        }
        nums[l] = current;
        quickSort(nums, left, l - 1);
        quickSort(nums, l + 1, right);
    }

}
