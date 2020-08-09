package lucku.baijunhan.alg.dp;

/**
 * 410. 分割数组的最大值
 * 给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。
 *
 * 注意:
 * 数组长度 n 满足以下条件:
 *
 * 1 ≤ n ≤ 1000
 * 1 ≤ m ≤ min(50, n)
 * 示例:
 *
 * 输入:
 * nums = [7,2,5,10,8]
 * m = 2
 *
 * 输出:
 * 18
 *
 * 解释:
 * 一共有四种方法将nums分割为2个子数组。
 * 其中最好的方式是将其分为[7,2,5] 和 [10,8]，
 * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 *
 */
public class SplitArrayLargestSum {

    public static void main(String[] args) {
        System.out.println(new Solution().splitArray(new int[]{7, 2, 5, 10, 8}, 2));
    }

    static class Solution {
        public int splitArray(int[] nums, int m) {
            long l = 0, r = 0;
            for(int n : nums){
                if(n > l) l = n;
                r += n;
            }
            int sum, count;
            while(l < r){
                long mid = (l + r) >> 1;
                sum = 0;
                count = 1;
                for(int n: nums){
                    sum += n;
                    if(sum > mid){
                        sum = n;
                        count ++;
                    }
                }
                if(count > m){
                    l = mid + 1;
                }else{
                    r = mid;
                }
            }
            return (int)l;
        }
    }

}
