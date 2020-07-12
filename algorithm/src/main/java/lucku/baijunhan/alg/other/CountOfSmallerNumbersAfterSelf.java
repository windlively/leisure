package lucku.baijunhan.alg.other;

import java.util.Arrays;
import java.util.List;

/**
 * 315. 计算右侧小于当前元素的个数
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 *
 * 示例:
 *
 * 输入: [5,2,6,1]
 * 输出: [2,1,1,0]
 * 解释:
 * 5 的右侧有 2 个更小的元素 (2 和 1).
 * 2 的右侧仅有 1 个更小的元素 (1).
 * 6 的右侧有 1 个更小的元素 (1).
 * 1 的右侧有 0 个更小的元素.
 *
 */
public class CountOfSmallerNumbersAfterSelf {

    public static void main(String[] args) {
        System.out.println(new Solution().countSmaller(new int[]{5, 2, 6, 1}));
    }

    static class Solution {

        private int[] temp;
        private Integer[] result;
        private int[] indexs;

        // 快速排序
        // public List<Integer> countSmaller(int[] nums) {
        //     List<Integer> r = new ArrayList<>(nums.length);
        //     if(nums.length == 0) return r;
        //     if(nums.length == 1) {
        //         r.add(0);
        //         return r;
        //     }

        //     int[] res = new int[nums.length];
        //     for(int i = nums.length - 2; i >=0; i --){
        //         res[i] = quickSort(nums,i, nums.length - 1) - i;
        //     }

        //     for(int n: res) r.add(n);
        //     return r;
        // }

        // private int quickSort(int[] nums, int l, int r){
        //     int n = nums[l];
        //     while(l < r){
        //         while(l < r && nums[r] >= n) r--;
        //         if(l < r) nums[l] = nums[r];
        //         while(l < r && nums[l] < n) l ++;
        //         if(l < r) nums[r] = nums[l];
        //     }
        //     nums[l] = n;
        //     return l;
        // }

        // 归并法
        public List<Integer> countSmaller(int[] nums) {
            indexs = new int[nums.length];
            for(int i = 0; i < nums.length; i++)
                indexs[i] = i;
            result = new Integer[nums.length];
            Arrays.fill(result, 0);
            temp = new int[nums.length];
            merge(nums,0,nums.length -1);
            return Arrays.asList(result);
        }

        void merge(int[] nums, int left, int right){
            if(left >= right) return;
            int mid = left + ((right - left) >> 1);
            merge(nums, left, mid);
            merge(nums, mid + 1, right);

            int l = left, r = mid + 1;
            int i = left;

            while(l <= mid && r <= right){
                if(nums[indexs[l]] > nums[indexs[r]]){
                    temp[i++] = indexs[r ++];
                }else{
                    result[indexs[l]] += r - mid - 1;
                    temp[i++] = indexs[l ++];
                }
            }

            while(l <= mid){
                result[indexs[l]] += r - mid - 1;
                temp[i++] = indexs[l++];
            }
            while(r <= right) temp[i++] = indexs[r++];

            for(i = left; i <= right; i++){
                indexs[i] = temp[i];
            }
        }

    }

}
