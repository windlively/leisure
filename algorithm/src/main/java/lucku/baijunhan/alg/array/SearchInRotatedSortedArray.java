package lucku.baijunhan.alg.array;

/**
 * 33. 搜索旋转排序数组
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 * 你可以假设数组中不存在重复的元素。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 */
public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        System.out.println(new Solution().search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
    }

    static class Solution {
        public int search(int[] nums, int target) {
            if(nums == null || nums.length == 0) return -1;
            // 二分查找的左右边界
            int l = 0, r = nums.length - 1;
            // 数组中的最后一个数
            // 给定一个数i, i比这个数(k)大则说明i在旋转数组的前半部分, i < k则说明i在旋转数组的后半部分
            int k = nums[nums.length-1];
            // 首先判断target是否等于k, 如果是直接返回
            if(target == k) return nums.length - 1;
            while(l < r){
                int i = (l + r) >> 1;
                // 找到目标值
                if(nums[i] == target) return i;
                // 目标值比k大, 说明目标值在前半部分
                if(target > k){
                    if(nums[i] > target){
                        // 当前值比target大, 则i一定在前半部分, 缩小右边界
                        r = i;
                    }else{
                        // 否则, i的位置需要进一步确定
                        if(nums[i] > k)
                            l = i + 1; // 当前值大于k, 说明i在前半部分, 缩小左边界
                        else
                            r = i; // 当前值小于等于k, 说明i在后半部分, 缩小右边界使得r向前半部分靠近
                    }
                }
                // 目标值比k小, 说明目标值在后半部分
                if(target < k){
                    if(nums[i] > target){
                        // 当前值比target大, i的位置需要进一步确定
                        if(nums[i] > k)
                            l = i + 1; // 当前值大于k, i在前半部分, 缩小左边界使得l向后半部分靠近
                        else
                            r = i; // 当前值小于等于k, i在后半部分, 直接缩小右边界
                    }else{
                        // 否则, i在后半部分, 缩小左边界
                        l = i + 1;
                    }
                }
            }
            // 未找到
            return -1;
        }
    }

}
