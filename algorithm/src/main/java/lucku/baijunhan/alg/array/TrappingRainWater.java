package lucku.baijunhan.alg.array;

/**
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *
 *
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 *
 * 示例:
 *
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 */
public class TrappingRainWater {

    public static void main(String[] args) {
        System.out.println(new Solution().trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    static class Solution {
        public int trap(int[] height) {
            int res = 0;
            int r = height.length-1;
            int lMax = 0, rMax = 0;
            for(int l = 0; l < r; ){
                if(height[l] < height[r]){
                    lMax = Math.max(lMax, height[l]);
                    res += lMax - height[l++];
                    continue;
                }
                rMax = Math.max(rMax, height[r]);
                res += rMax - height[r--];
            }
            return res;
        }
    }
}
