package lucku.baijunhan.alg.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 695. 岛屿的最大面积
 * 给定一个包含了一些 0 和 1 的非空二维数组 grid 。
 *
 * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 *
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
 *
 *
 *
 * 示例 1:
 *
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。
 *
 * 示例 2:
 *
 * [[0,0,0,0,0,0,0,0]]
 * 对于上面这个给定的矩阵, 返回 0。
 *
 *
 *
 * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
 *
 */
public class MaxAreaOfIsland {

    static class Solution {
        private static int[][] direction = new int[][]{
                {0, 1, 0, -1},
                {1, 0, -1, 0}
        };


        public int maxAreaOfIsland(int[][] grid) {
            int maxArea = 0;
            Queue<int[]> queue = new LinkedList<>();
            for(int i = 0; i < grid.length; i++){
                for(int j = 0; j < grid[0].length; j ++){
                    if(grid[i][j] == 0) continue;
                    queue.offer(new int[]{i, j});
                    int area = 0;
                    grid[i][j] = 0;
                    while(!queue.isEmpty()){
                        int len = queue.size();
                        while(len -- > 0){
                            int[] position = queue.poll();
                            area ++;
                            for(int k = 0; k < 4; k ++){
                                int x = position[0] + direction[0][k];
                                int y = position[1] + direction[1][k];
                                if(x >= 0 && x < grid.length && y >= 0
                                   && y < grid[0].length && grid[x][y] == 1){
                                    grid[x][y] = 0;
                                    queue.offer(new int[]{x, y});
                                }
                            }
                        }
                    }
                    if(area > maxArea) maxArea = area;
                }
            }
            return maxArea;
        }
    }
}
