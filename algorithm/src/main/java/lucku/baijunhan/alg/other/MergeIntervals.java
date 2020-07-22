package lucku.baijunhan.alg.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 56. 合并区间
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * 
 */
public class MergeIntervals {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new Solution().merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}})));
    }
    
    static class Solution {
        public int[][] merge(int[][] intervals) {
            boolean[] flag = new boolean[intervals.length];
            Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
            List<int[]> list = new ArrayList<>(intervals.length);
            for(int i = 0 ; i < intervals.length; i ++){
                if(flag[i]) continue;
                flag[i] = true;
                int start = intervals[i][0], end = intervals[i][1];
                for(int j = i + 1; j < intervals.length; j ++){
                    if(flag[j]) continue;
                    if(intervals[j][1] >= end && intervals[j][0] <= end ){
                        end = intervals[j][1];
                        flag[j] = true;
                    }
                    if(intervals[j][1] >= start && intervals[j][1] <= end && intervals[j][0] <= start){
                        start = intervals[j][0];
                        flag[j] = true;
                    }
                    if(intervals[j][0] >= start && intervals[j][1] <= end) {
                        flag[j] = true;
                    }
                }
                list.add(new int[]{start, end});
            }
            int[][] result = new int[list.size()][2];
            for(int i = 0; i < result.length; i++)
                result[i] = list.get(i);
            return result;
        }
    }
}
