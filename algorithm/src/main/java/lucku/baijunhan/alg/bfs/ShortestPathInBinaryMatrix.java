package lucku.baijunhan.alg.bfs;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 1091. 二进制矩阵中的最短路径
 * 在一个 N × N 的方形网格中，每个单元格有两种状态：空（0）或者阻塞（1）。
 *
 * 一条从左上角到右下角、长度为 k 的畅通路径，由满足下述条件的单元格 C_1, C_2, ..., C_k 组成：
 *
 * 相邻单元格 C_i 和 C_{i+1} 在八个方向之一上连通（此时，C_i 和 C_{i+1} 不同且共享边或角）
 * C_1 位于 (0, 0)（即，值为 grid[0][0]）
 * C_k 位于 (N-1, N-1)（即，值为 grid[N-1][N-1]）
 * 如果 C_i 位于 (r, c)，则 grid[r][c] 为空（即，grid[r][c] == 0）
 * 返回这条从左上角到右下角的最短畅通路径的长度。如果不存在这样的路径，返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[[0,1],[1,0]]
 *
 * 输出：2
 *
 * 示例 2：
 *
 * 输入：[[0,0,0],[1,1,0],[1,1,0]]
 *
 * 输出：4
 *
 *
 *
 * 提示：
 *
 * 1 <= grid.length == grid[0].length <= 100
 * grid[i][j] 为 0 或 1
 */
public class ShortestPathInBinaryMatrix {
    class Solution {
        public int shortestPathBinaryMatrix(int[][] grid) {
            // int rs = minPath(grid, 0, 0, 0, new boolean[grid.length][grid[0].length]);
            // return rs == Integer.MAX_VALUE ? -1 : rs;
            return bfs(grid);
        }

        // 递归 超时
        int minPath(int[][] g, int i, int j, int sum, boolean[][] visited){
            if(accessible(g, i, j, visited)){
                if(i == g.length -1 && j == g[0].length -1){
                    return sum + 1;
                }
                sum ++;
                visited[i][j] = true;
                int min = min(minPath(g, i + 1, j, sum, visited),
                        minPath(g, i, j + 1, sum, visited),
                        minPath(g, i - 1, j, sum, visited),
                        minPath(g, i, j - 1, sum, visited),
                        minPath(g, i + 1, j + 1, sum, visited),
                        minPath(g, i + 1, j - 1, sum, visited),
                        minPath(g, i - 1, j + 1, sum, visited),
                        minPath(g, i - 1, j - 1, sum, visited)
                );
                visited[i][j] = false;
                return min;
            }
            return Integer.MAX_VALUE;
        }

        int bfs(int[][] g){
            Deque<int[]> queue = new LinkedList<int[]>();
            if(g.length <= 0) return -1;
            int row = g.length-1;
            int col = g[0].length - 1;
            if(g[0][0] == 1 || g[row][col] == 1) return -1;
            boolean[][] visited = new boolean[row + 1][col + 1];
            queue.offer(new int[]{0,0});

            int[][] direction = new int[][]{{0,1},{1,0},{-1,0},{0,-1},{-1,1},{1,-1},{1,1},{-1,-1}};
            int path = 0;
            while(!queue.isEmpty()){
                int length = queue.size();
                while(length -- > 0){
                    int[] pos = queue.poll();
                    int i = pos[0], j = pos[1];
                    if(i == row && j == col)
                        return path + 1;

                    if((i >= 0 && j >= 0 && i <= row && j <= col && !visited[i][j] && g[i][j] == 0)){

                        visited[i][j] = true;
                        for(int[] d : direction){
                            queue.offer(new int[]{i + d[0], j + d[1]});
                        }
                    }
                }
                path++;
            }
            return -1;
        }

        int min(int...ns){
            int min = ns[0];
            for(int n:ns) if(n < min) min = n;
            return min;
        }

        boolean accessible(int[][] g, int i, int j, boolean[][] visited){
            return i >= 0 && j >= 0 && i < g.length && j < g[0].length && g[i][j] != 1 && !visited[i][j];
        }
    }
}
