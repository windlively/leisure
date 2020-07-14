package lucku.baijunhan.alg.other;

import java.util.Arrays;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 *
 */
public class FindFirstAndLastPositionOfElementInSortedArray {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));
    }

    static class Solution {
        public int[] searchRange(int[] nums, int target) {
            int[] result = new int[]{-1, -1};
            if(nums.length == 0) return result;
            int l = 0, r = nums.length - 1, mid;

            // 查找左侧
            while(l <= r){
                mid = (l + r) >> 1;
                if(nums[mid] == target && l == r) {
                    result[0] = mid;
                    break;
                }
                if(l == r && nums[mid] != target) break;
                if(nums[mid] < target){
                    l = mid + 1;
                }else{
                    r = mid;
                }
            }

            // 查找右侧
            if(result[0] != -1){
                l = 0;
                r = nums.length;
                while(l < r){
                    mid = (l + r) >> 1;
                    if(nums[mid] <= target){
                        l = mid + 1;
                    }else{
                        r = mid;
                    }
                }
                result[1] = l - 1;
            }
            return result;
        }
    }

}
