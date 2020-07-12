package lucku.baijunhan.alg.dp;

/**
 * 309. 最佳买卖股票时机含冷冻期
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 *
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 示例:
 *
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 *
 */
public class BestTimeToBuyAndSellStockWithCooldown {

    public static void main(String[] args) {
        System.out.println(new Solution().maxProfit(new int[]{1, 2, 3, 0, 2}));
    }

    static class Solution {

        public int maxProfit(int[] prices) {
            if(prices == null || prices.length == 0) return 0;

            // dp[i][0]: 手上持有股票的最大收益
            // dp[i][1]: 手上不持有股票，并且(i+1)处于冷冻期中的累计最大收益
            // dp[i][2]: 手上不持有股票，并且(i+1)不在冷冻期中的累计最大收益

            // int[][] dp = new int[prices.length][3];
            // dp[0][0] = -prices[0];
            // for(int i = 1; i < prices.length; i++){
            //     dp[i][0] = Math.max(dp[i-1][0], dp[i-1][2] - prices[i]);
            //     dp[i][1] = dp[i-1][0] + prices[i];
            //     dp[i][2] = Math.max(dp[i-1][2], dp[i-1][1]);
            // }
            // return Math.max(dp[prices.length - 1][2], dp[prices.length - 1][1]);

            // 空间复杂度优化
            int hold = -prices[0];
            int nonHoldWillFreeze = 0;
            int nonHoldNonFreeze = 0;

            for(int i = 1; i < prices.length; i++){
                int tHold = Math.max(hold, nonHoldNonFreeze - prices[i]);
                int tNonHoldWillFreeze = hold + prices[i];
                int tNonHoldNonFreeze = Math.max(nonHoldNonFreeze, nonHoldWillFreeze);

                hold = tHold;
                nonHoldNonFreeze = tNonHoldNonFreeze;
                nonHoldWillFreeze = tNonHoldWillFreeze;
            }

            return Math.max(nonHoldWillFreeze, nonHoldNonFreeze);

        }

    }
}
