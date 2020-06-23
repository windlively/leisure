package lucku.baijunhan.alg.array;

import java.util.Stack;

/**
 * 84. 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 *
 *
 *
 *
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 *
 *
 *
 *
 *
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 *
 *
 *
 * 示例:
 *
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 */
public class LargestRectangleInHistogram {

    public static void main(String[] args) {
        System.out.println(new Solution().largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
    }

    static class Solution {
        public int largestRectangleArea(int[] heights) {
            return f2(heights);
        }

        // 暴力解法
        int f1(int[] heights){
            int max = 0;
            for(int i = 0; i < heights.length; i ++){
                int l = i, r = i;
                while(l >= 0 && heights[l] >= heights[i]) l--;
                while(r < heights.length && heights[r] >= heights[i]) r++;
                l ++;
                r --;
                int h = Math.min(heights[l], heights[r]);
                h = Math.min(h, heights[i]);
                max = Math.max(max, (r - l + 1) * h );
            }
            return max;
        }

        // 单调栈
        int f2(int[] heights){
            Stack<Integer> stack = new Stack<>();
            int[] arr = new int[heights.length + 2];
            System.arraycopy(heights, 0, arr, 1, heights.length);
            int res = 0;
            for(int i = 0; i < arr.length; i++){
                while(!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                    int h = arr[stack.pop()];
                    res = Math.max(res, (i - stack.peek() - 1) * h );
                }
                stack.push(i);
            }
            return res;
        }
    }
}
