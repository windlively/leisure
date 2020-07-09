package lucku.baijunhan.alg.array;

/**
 * 面试题 10.03. 搜索旋转数组
 * 搜索旋转数组。给定一个排序后的数组，包含n个整数，但这个数组已被旋转过很多次了，次数不详。请编写代码找出数组中的某个元素，假设数组元素原先是按升序排列的。若有多个相同元素，返回索引值最小的一个。
 *
 * 示例1:
 *
 *  输入: arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 5
 *  输出: 8（元素5在该数组中的索引）
 * 示例2:
 *
 *  输入：arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 11
 *  输出：-1 （没有找到）
 * 提示:
 *
 * arr 长度范围在[1, 1000000]之间
 */
public class SearchRotateArrayLcci {

    public static void main(String[] args) {
        System.out.println(new Solution().search(new int[]{2, 2, 0, 1, 2}, 0));
    }

    static class Solution {
        public int search(int[] nums, int target) {
            if(nums == null || nums.length == 0) return -1;
            int l = 0, r = nums.length - 1;
            int k = nums[0];
            if(target == k) return 0;
            while(l < r){
                while(nums[l] == nums[l + 1]) l ++;
                while(nums[r] == nums[r - 1]) r --;
                int i = (l + r) >> 1;
                if(nums[i] == target) {
                    while(i > 0 && nums[i - 1] == target) i--;
                    return i;
                };
                if(target > k){
                    if(nums[i] > target){
                        r = i;
                    }else{
                        if(nums[i] >= k)
                            l = i + 1;
                        else
                            r = i;
                    }
                }
                if(target < k){
                    if(nums[i] > target){
                        if(nums[i] >= k)
                            l = i + 1;
                        else
                            r = i;
                    }else{
                        l = i + 1;
                    }
                }
            }
            return nums[r] == target ? r : -1;
        }
    }
}
