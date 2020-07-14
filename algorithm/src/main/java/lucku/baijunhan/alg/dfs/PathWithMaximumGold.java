package lucku.baijunhan.alg.dfs;

/**
 * 1219. 黄金矿工
 * 你要开发一座金矿，地质勘测学家已经探明了这座金矿中的资源分布，并用大小为 m * n 的网格 grid 进行了标注。每个单元格中的整数就表示这一单元格中的黄金数量；如果该单元格是空的，那么就是 0。
 *
 * 为了使收益最大化，矿工需要按以下规则来开采黄金：
 *
 * 每当矿工进入一个单元，就会收集该单元格中的所有黄金。
 * 矿工每次可以从当前位置向上下左右四个方向走。
 * 每个单元格只能被开采（进入）一次。
 * 不得开采（进入）黄金数目为 0 的单元格。
 * 矿工可以从网格中 任意一个 有黄金的单元格出发或者是停止。
 *
 *
 * 示例 1：
 *
 * 输入：grid = [[0,6,0],[5,8,7],[0,9,0]]
 * 输出：24
 * 解释：
 * [[0,6,0],
 *  [5,8,7],
 *  [0,9,0]]
 * 一种收集最多黄金的路线是：9 -> 8 -> 7。
 * 示例 2：
 *
 * 输入：grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
 * 输出：28
 * 解释：
 * [[1,0,7],
 *  [2,0,6],
 *  [3,4,5],
 *  [0,3,0],
 *  [9,0,20]]
 * 一种收集最多黄金的路线是：1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7。
 *
 *
 * 提示：
 *
 * 1 <= grid.length, grid[i].length <= 15
 * 0 <= grid[i][j] <= 100
 * 最多 25 个单元格中有黄金。
 */
public class PathWithMaximumGold {


    static class Solution {
        public int getMaximumGold(int[][] grid) {
            if(grid.length == 0) return 0;
            return recursive(grid);
            //return bfs(grid);
        }

        int recursive(int[][] g){
            int max = 0;
            boolean[][] vis = new boolean[g.length][g[0].length];
            for(int i = 0; i < g.length; i ++){
                for(int j = 0; j < g[0].length; j ++){
                    if(g[i][j] != 0)
                        max = max(recursive(g, i, j, vis), max);
                }
            }
            return max;
        }

        int recursive(int[][] g, int i, int j, boolean[][] v){
            int res = 0;
            if(i >= 0 && i < g.length && j >= 0 && j < g[0].length
               && g[i][j] != 0 && !v[i][j]){
                v[i][j] = true;
                res = g[i][j] + max(
                        recursive(g, i + 1, j, v),
                        recursive(g, i - 1, j, v),
                        recursive(g, i, j - 1, v),
                        recursive(g, i, j + 1, v)
                );
                v[i][j] = false;
            }
            return res;
        }

        // int bfs(int[][] grid){
        //     Queue<int[]> queue = new LinkedList<>();
        //     int result = 0;
        //     int[][] pos = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        //     int[][] vis = new int[grid.length][grid[0].length];
        //     for(int i = 0; i < grid.length; i ++){
        //         for(int j = 0; j < grid[0].length; j++){
        //             if(grid[i][j] == 0) continue;
        //             queue.offer(new int[]{i, j});
        //             vis = new int[grid.length][grid[0].length];
        //             vis[i][j] = grid[i][j];
        //             while(!queue.isEmpty()){
        //                 int len = queue.size();
        //                 while(len-- > 0){
        //                     int[] current = queue.poll();
        //                     int m = current[0], n = current[1];
        //                     if(m == grid.length - 1 && n == grid[0].length-1){
        //                         result = max(vis[m-1][n], vis[m][n-1]) + grid[m][n];
        //                     }
        //                     for(int[] p : pos){
        //                         int x = p[0] + m, y = p[1] + n;
        //                         if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length &&
        //                             grid[x][y] != 0){
        //                             int val = grid[x][y];
        //                             if(vis[x][y] != 0 && vis[x][y] >= val + vis[m][n]) continue;
        //                             vis[x][y] = vis[m][n] + val;
        //                             queue.offer(new int[]{x,y});
        //                         }
        //                     }
        //                 }
        //             }
        //         }
        //     }
        //     return result;
        // }

        static int max(int...ns){
            int max = ns[0];
            for(int n : ns) if(max < n) max = n;
            return max;
        }
    }
}
