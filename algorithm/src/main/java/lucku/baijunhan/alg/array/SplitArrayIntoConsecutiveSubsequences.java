package lucku.baijunhan.alg.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 659. 分割数组为连续子序列
 * 给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个子序列，其中每个子序列都由连续整数组成且长度至少为 3 。
 *
 * 如果可以完成上述分割，则返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入: [1,2,3,3,4,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3
 * 3, 4, 5
 *
 *
 * 示例 2：
 *
 * 输入: [1,2,3,3,4,4,5,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3, 4, 5
 * 3, 4, 5
 *
 *
 * 示例 3：
 *
 * 输入: [1,2,3,4,4,5]
 * 输出: False
 *
 *
 * 提示：
 *
 * 输入的数组长度范围为 [1, 10000]
 *
 */
public class SplitArrayIntoConsecutiveSubsequences {

    public static void main(String[] args) {
        System.out.println(new Solution().isPossible(new int[]{1, 2, 3, 3, 4, 5}));
    }

    static class Solution {
        public boolean isPossible(int[] nums) {
            Map<Integer, Integer> map = new HashMap<>();
            Map<Integer, Integer> tail = new HashMap<>();
            for(int n : nums) map.put(n, map.getOrDefault(n, 0) + 1);
            for(int n : nums){
                if(map.get(n) == 0)
                    continue;
                else if(tail.getOrDefault(n, 0) > 0){
                    tail.put(n, tail.get(n) - 1);
                    tail.put(n + 1, tail.getOrDefault(n + 1, 0) + 1);
                }else if(map.getOrDefault(n + 1, 0) > 0 && map.getOrDefault(n + 2, 0) > 0){
                    map.put(n + 1, map.get(n + 1) - 1);
                    map.put(n + 2, map.get(n + 2) - 1);
                    tail.put(n + 3, tail.getOrDefault(n + 3, 0) + 1);
                }else{
                    return false;
                }
                map.put(n , map.get(n) - 1);
            }
            return true;
        }
    }
}
