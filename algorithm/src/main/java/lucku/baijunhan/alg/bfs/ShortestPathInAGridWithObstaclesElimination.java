package lucku.baijunhan.alg.bfs;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 1293. 网格中的最短路径
 * 给你一个 m * n 的网格，其中每个单元格不是 0（空）就是 1（障碍物）。每一步，您都可以在空白单元格中上、下、左、右移动。
 *
 * 如果您 最多 可以消除 k 个障碍物，请找出从左上角 (0, 0) 到右下角 (m-1, n-1) 的最短路径，并返回通过该路径所需的步数。如果找不到这样的路径，则返回 -1。
 *
 *
 *
 * 示例 1：
 *
 * 输入：
 * grid =
 * [[0,0,0],
 *  [1,1,0],
 *  [0,0,0],
 *  [0,1,1],
 *  [0,0,0]],
 * k = 1
 * 输出：6
 * 解释：
 * 不消除任何障碍的最短路径是 10。
 * 消除位置 (3,2) 处的障碍后，最短路径是 6 。该路径是 (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
 *
 *
 * 示例 2：
 *
 * 输入：
 * grid =
 * [[0,1,1],
 *  [1,1,1],
 *  [1,0,0]],
 * k = 1
 * 输出：-1
 * 解释：
 * 我们至少需要消除两个障碍才能找到这样的路径。
 *
 *
 * 提示：
 *
 * grid.length == m
 * grid[0].length == n
 * 1 <= m, n <= 40
 * 1 <= k <= m*n
 * grid[i][j] == 0 or 1
 * grid[0][0] == grid[m-1][n-1] == 0
 */
public class ShortestPathInAGridWithObstaclesElimination {

    public static void main(String[] args) {
        System.out.println(new Solution().shortestPath(new int[][]
//                {{0, 0, 0},
//                {1, 1, 0},
//                {0, 0, 0},
//                {0, 1, 1},
//                {0, 0, 0}}
                {{0,1,1},{1,1,1},{1,0,0}}
                , 1));
    }
    static class Solution {

        int r = Integer.MAX_VALUE;
        boolean[][] visited;
        public int shortestPath(int[][] grid, int k) {
            // if(k >= grid.length + grid[0].length - 3)
            //     return grid.length + grid[0].length - 2;
            // visited = new boolean[grid.length][grid[0].length];
            // r = Integer.MAX_VALUE;
            // if(core(grid, 0, 0, k, 0))
            //     return r;
            // return -1;
            return bfs(grid,k);
        }

        // 递归超时
        public boolean core(int[][] g, int i, int j, int k, int cur){
            if(accessible(g, i, j, k)){
                if(i == g.length-1 && j == g[0].length-1){
                    r = Math.min(r, cur);
                    return true;
                }
                if(g[i][j] == 1) --k;
                visited[i][j] = true;
                boolean rs = core(g, i + 1, j, k, cur + 1) | core(g, i, j + 1,k, cur + 1) |
                             core(g, i, j - 1,k, cur + 1) | core(g, i-1, j,k, cur + 1);
                visited[i][j] = false;
                return rs;
            }
            return false;
        }

        int bfs(int[][] g, int k) {
            if (g.length == 0) return -1;
            int row = g.length - 1, col = g[0].length - 1;
            // 针对单个元素
            if (0 == row && 0 == col ) {
                return k >= g[0][0] ? 0 : -1;
            }
            int[][] visited = new int[row + 1][col + 1];
            Deque<int[]> queue = new LinkedList<>();
            int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            queue.offer(new int[]{0, 0, 0});
            for (int i = 0; i <= row; i++) {
                Arrays.fill(visited[i], -2);
            }
            visited[0][0] = k;
            int path = 1;
            while (!queue.isEmpty()) {
                int length = queue.size();
                while (length -- > 0) {
                    int[] pos = queue.poll();
                    int i = pos[0];
                    int j = pos[1];
                    int curK = pos[2];

                    for (int[] n : directions) {
                        int x = n[0] + i, y = n[1] + j;
                        if (x >= 0 && y >= 0 && x <= row && y <= col ) {
                            if (x == row && y == col) {
                                return path;
                            }
                            int nowK = g[x][y] == 1 ? curK + 1 : curK;

                            if(nowK > k) continue;

                            if (visited[x][y] != -2 && visited[x][y] >= k - nowK)
                                continue;
                            visited[x][y] = k - nowK;
                            queue.offer(new int[]{x, y, nowK});
                        }
                    }
                }
                path++;
            }
            return -1;
        }


        public boolean accessible(int[][] grid, int i, int j, int k){
            return i >=0 && j >= 0 && i < grid.length && j < grid[0].length && (k >= 0 ) && !visited[i][j];
        }
    }
}
