package lucku.baijunhan.alg.slide_window;

/**
 * 643. 子数组最大平均数 I
 * 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
 *
 *
 *
 * 示例：
 *
 * 输入：[1,12,-5,-6,50,3], k = 4
 * 输出：12.75
 * 解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 *
 *
 * 提示：
 *
 * 1 <= k <= n <= 30,000。
 * 所给数据范围 [-10,000，10,000]。
 */
public class MaximumAverageSubarrayI {

    static class Solution {
        public double findMaxAverage(int[] nums, int k) {
            int sum = 0;
            for (int i = 0; i < k; i++) {
                sum += nums[i];
            }
            double avg = (double) sum / (double) k;

            int l = 1, r = k;
            while (r < nums.length){
                sum += nums[r] - nums[l - 1];
                avg = Math.max((double)sum / (double) k, avg);
                l ++;
                r ++;
            }

            return avg;

        }
    }

}
